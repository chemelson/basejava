package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.StorageException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.function.Function;

public class SqlHelper {

    private final ConnectionFactory connectionFactory;

    public SqlHelper(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    private interface Modifier<T> {
        void modify(T statement);
    }

    private interface Retriever<T, R> {
        R retrieve(T statement);
    }

    public void modify(String query, Modifier<PreparedStatement> modifier) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
           modifier.modify(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    public <R> R retrieve(String query, Retriever<PreparedStatement, R> retriever) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            return retriever.retrieve(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}