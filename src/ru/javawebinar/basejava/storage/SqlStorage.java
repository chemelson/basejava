package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SqlStorage implements Storage {

    private final SqlHelper helper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        helper = new SqlHelper(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        helper.request("DELETE FROM resume", PreparedStatement::execute);
    }

    @Override
    public void update(Resume resume) {
        helper.request("UPDATE resume SET full_name = ? WHERE uuid = ?", (SqlHelper.SqlExecutor<PreparedStatement, Void>) ps -> {
            ps.setString(1, resume.getFullName());
            ps.setString(2, resume.getUuid());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new NotExistStorageException(resume.getUuid());
            }
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        helper.request("INSERT INTO resume (uuid, full_name) VALUES (?, ?) ON CONFLICT (uuid) DO NOTHING",
                (SqlHelper.SqlExecutor<PreparedStatement, Void>) ps -> {
                    ps.setString(1, resume.getUuid());
                    ps.setString(2, resume.getFullName());
                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected == 0) {
                        throw new ExistStorageException(resume.getUuid());
                    }
                    return null;
                });
    }

    @Override
    public Resume get(String uuid) {
        return helper.request("SELECT * FROM resume r WHERE r.uuid = ?", ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        });
    }

    @Override
    public void delete(String uuid) {
        helper.request("DELETE FROM resume r WHERE r.uuid = ?", (SqlHelper.SqlExecutor<PreparedStatement, Void>) ps -> {
            ps.setString(1, uuid);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return helper.request("SELECT * FROM resume r ORDER BY uuid ASC", ps -> {
            ResultSet rs = ps.executeQuery();
            List<Resume> resumes = new ArrayList<>();
            while (rs.next()) {
                resumes.add(
                        new Resume(rs.getString("uuid").trim(),
                                rs.getString("full_name").trim()));
            }
            return Collections.unmodifiableList(resumes);
        });
    }

    @Override
    public int size() {
        return helper.request("SELECT COUNT(*) AS size FROM resume r", ps -> {
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("size");
        });
    }
}
