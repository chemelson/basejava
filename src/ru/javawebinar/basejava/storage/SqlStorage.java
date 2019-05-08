package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            deleteSections(conn, resume);
            insertSections(conn, resume);
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
                    insertSections(conn, resume);
                    return null;
                }
        );
    }

    @Override
    public Resume get(String uuid) {
        return helper.execute("SELECT r.uuid AS uuid, r.full_name AS full_name," +
                " c.type AS contact_type, c.value AS contact_value, s.type AS section_type, s.value AS section_value FROM resume r" +
                " LEFT JOIN contact c ON r.uuid = c.resume_uuid" +
                " LEFT JOIN section s ON r.uuid = s.resume_uuid WHERE r.uuid = ?", ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            Resume resume = new Resume(uuid, rs.getString("full_name"));
            do {
                addContact(rs, resume);
                addSection(rs, resume);
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
        return helper.transactionalExecute(conn -> {
            List<Resume> allResumes = getResumes(conn);
            Map<String, Map<ContactType, String>> allContacts = getContacts(conn);
            Map<String, Map<SectionType, AbstractSection>> allSections = getSections(conn);
            allResumes.forEach(resume -> {
                String uuid = resume.getUuid();
                Map<ContactType, String> contacts = allContacts.get(uuid);
                if (contacts != null) {
                    resume.setContacts(contacts);
                }
                Map<SectionType, AbstractSection> sections = allSections.get(uuid);
                if (sections != null) {
                    resume.setSections(sections);
                }
            });
            return new ArrayList<>(allResumes);
        });
    }

    private List<Resume> getResumes(Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume r ORDER BY r.uuid ASC")) {
            List<Resume> resumes = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String uuid = rs.getString("uuid").trim();
                String fullName = rs.getString("full_name").trim();
                resumes.add(new Resume(uuid, fullName));
            }
            return resumes;
        }
    }

    private Map<String, Map<ContactType, String>> getContacts(Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact")) {
            Map<String, Map<ContactType, String>> allContacts = new HashMap<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String uuid = rs.getString("resume_uuid");
                String value = rs.getString("value");
                String type = rs.getString("type");
                Map<ContactType, String> contacts = allContacts.computeIfAbsent(
                        uuid, k -> new EnumMap<>(ContactType.class));
                contacts.put(ContactType.valueOf(type), value);
            }
            return allContacts;
        }
    }

    private Map<String, Map<SectionType, AbstractSection>> getSections(Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM section")) {
            Map<String, Map<SectionType, AbstractSection>> allSections = new HashMap<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String uuid = rs.getString("resume_uuid");
                String value = rs.getString("value");
                String type = rs.getString("type");
                SectionType sectionType = SectionType.valueOf(type);
                Map<SectionType, AbstractSection> sections = allSections.computeIfAbsent(
                        uuid, k -> new EnumMap<>(SectionType.class));
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        sections.put(sectionType, new TextSection(value));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        sections.put(sectionType, new ListSection(value.split("\n")));
                        break;
                    default:
                        throw new IllegalStateException();
                }
            }
            return allSections;
        }
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

    private void addContact(ResultSet rs, Resume resume) throws SQLException {
        String value = rs.getString("contact_value"); // BAD! Dependency with get method
        if (value != null) {
            resume.addContact(ContactType.valueOf(rs.getString("contact_type")), value); // BAD! Dependency with get method
        }
    }

    private void insertSections(Connection conn, Resume resume) throws SQLException, IllegalStateException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO section (resume_uuid, type, value)" +
                " VALUES (?, ?, ?)")) {
            for (Map.Entry<SectionType, AbstractSection> e : resume.getSections().entrySet()) {
                SectionType sectionType = e.getKey();
                AbstractSection section = e.getValue();
                ps.setString(1, resume.getUuid());
                ps.setString(2, sectionType.name());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        ps.setString(3, ((TextSection) section).getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        ps.setString(3, String.join("\n", ((ListSection) section).getItems()));
                        break;
                    default:
                        throw new IllegalStateException();
                }
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void addSection(ResultSet rs, Resume resume) throws SQLException {
        String value = rs.getString("section_value"); // BAD! Dependency with get method
        if (value != null) {
            String type = rs.getString("section_type"); // BAD! Dependency with get method
            SectionType sectionType = SectionType.valueOf(type);
            switch (sectionType) {
                case PERSONAL:
                case OBJECTIVE:
                    resume.addSection(sectionType, new TextSection(value));
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    resume.addSection(sectionType, new ListSection(value.split("\n")));
                    break;
                default:
                    throw new IllegalStateException();
            }
        }
    }

    private void deleteAttributes(Connection conn, Resume resume, String query) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, resume.getUuid());
            ps.execute();
        }
    }

    private void deleteContacts(Connection conn, Resume resume) throws SQLException {
        deleteAttributes(conn, resume, "DELETE FROM contact WHERE resume_uuid = ?");
    }

    private void deleteSections(Connection conn, Resume resume) throws SQLException {
        deleteAttributes(conn, resume, "DELETE FROM section WHERE resume_uuid = ?");
    }


}
