package server.persistence;


import shared.domain.Company;

import java.sql.*;

public class CompanyDAO extends DAO<Company> {

    public CompanyDAO() {
        super("company");
    }

    public Long persists(Company company) throws SQLException {
        String sql = String.format("insert into company(email, pass, compName, description) " +
                        "values('%s', '%s', '%s', '%s') RETURNING company_id",
                company.getEmail(),
                company.getPassword(),
                company.getCompName(),
                company.getDescription()
        );
        Long id = insert(sql);

        company.setId(id);
        return id;
    }

    public Company getByEmail(String email) throws SQLException {
        Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        Statement stmt = conn.createStatement();

        String sql = String.format("SELECT * FROM company WHERE email = '%s'", email);

        try {
            ResultSet rs = stmt.executeQuery(sql);

            Company cmp;
            if(rs.next()) {
                cmp = new Company(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6).charAt(0),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)
                );
                cmp.setId(rs.getLong(1));
            } else {
                throw new IllegalArgumentException("No such student");
            }
            return cmp;
        } finally {
            stmt.close();
            conn.close();
        }
    }

    public Company update(Company cmp) throws SQLException {
        Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

        String sql = "UPDATE company SET email = ?" +
                ", password = ?" +
                ", compName = ?" +
                ", description = ?";    // WHERE !!!

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, cmp.getEmail());
        stmt.setString(2, cmp.getPassword());
        stmt.setString(3, cmp.getCompName());
        stmt.setString(5, cmp.getDescription());


        try {
            stmt.executeUpdate();

            return cmp;
        } finally {
            stmt.close();
            conn.close();
        }
    }
}