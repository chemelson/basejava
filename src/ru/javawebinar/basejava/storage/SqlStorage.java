package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.*;

public class SqlStorage implements Storage {

    private final SqlHelper helper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        helper = new SqlHelper(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        helper.execute("DELETE FROM resume");
    }

    @Override
    public void update(Resume resume) {
        helper.transactionalExecute(conn -> {
            String uuid = resume.getUuid();
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, uuid);
                if (ps.executeUpdate() != 1) {
                    throw new NotExistStorageException(uuid);
                }
            }

            deleteContacts(conn, resume);
            insertContacts(conn, resume);
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        helper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                        ps.setString(1, resume.getUuid());
                        ps.setString(2, resume.getFullName());
                        ps.execute();
                    }

                    insertContacts(conn, resume);
                    return null;
                }
        );
    }

    @Override
    public Resume get(String uuid) {
        return helper.execute("SELECT * FROM resume r" +
                " LEFT JOIN contact c ON r.uuid = c.resume_uuid WHERE r.uuid = ?", ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            Resume resume = new Resume(uuid, rs.getString("full_name"));
            do {
                addContact(rs, resume);
            } while (rs.next());
            return resume;
        });
    }

    @Override
    public void delete(String uuid) {
        helper.execute("DELETE FROM resume r WHERE r.uuid = ?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return helper.execute("SELECT * FROM resume r" +
                " LEFT JOIN contact c ON r.uuid = c.resume_uuid ORDER BY r.uuid ASC", ps -> {
            ResultSet rs = ps.executeQuery();
            Map<String, Resume> resumeMap = new TreeMap<>();
            while (rs.next()) {
                String uuid = rs.getString("uuid").trim();
                String fullName = rs.getString("full_name").trim();
                resumeMap.computeIfAbsent(uuid, resume -> new Resume(uuid, fullName));
                addContact(rs, resumeMap.get(uuid));
            }
            return new ArrayList<>(resumeMap.values());
        });
    }

    @Override
    public int size() {
        return helper.execute("SELECT COUNT(*) AS size FROM resume r", ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt("size") : 0;
        });
    }

    private void insertContacts(Connection conn, Resume resume) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value)" +
                " VALUES (?, ?, ?)")) {
            for (Map.Entry<ContactType, String> e : resume.getContacts().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void deleteContacts(Connection conn, Resume resume) {
        helper.execute("DELETE FROM contact WHERE resume_uuid=?", ps -> {
            ps.setString(1, resume.getUuid());
            ps.execute();
            return null;
        });
    }

    private void addContact(ResultSet rs, Resume resume) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            resume.addContact(ContactType.valueOf(rs.getString("type")), value);
        }
    }
}
