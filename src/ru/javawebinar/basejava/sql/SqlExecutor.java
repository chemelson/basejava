package ru.javawebinar.basejava.sql;

import java.sql.SQLException;

public interface SqlExecutor<T, R> {
    R execute(T statement) throws SQLException;
}