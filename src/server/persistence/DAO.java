package server.persistence;

import java.sql.Connection;
import java.sql.*;
import java.util.List;

public abstract class DAO<T> {

    protected final static String DBURL = "jdbc:postgresql://localhost/Sep2";
    protected final static String DBUSER = "postgres";
    protected final static String DBPASS = "smilte";

    public abstract T create(T t) throws SQLException;

    public abstract T getById(Long id) throws SQLException;

    public abstract T update(T t) throws SQLException;

    public abstract void delete(Long id) throws SQLException;
}
