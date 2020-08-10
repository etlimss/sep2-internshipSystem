package server.persistence;

import java.sql.Connection;
import java.sql.*;
import java.util.List;

public abstract class DAO<T> {

    private String tableName;

    protected final static String DBURL = "jdbc:postgresql://localhost/Sep2";
    protected final static String DBUSER = "postgres";
    protected final static String DBPASS = "smilte";

    public DAO (String tableName) {
        this.tableName = tableName;
    }

    protected Long insert(String sql) throws SQLException {
        Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        Statement stmt = conn.createStatement();

        Long id = null;

        try {
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();

            return rs.getLong(1);
        } finally {
            stmt.close();
            conn.close();
        }
    }
}
