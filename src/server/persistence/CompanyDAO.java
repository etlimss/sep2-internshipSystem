package server.persistence;


import shared.domain.Company;
import shared.domain.Vacancy;

import java.sql.*;

public class CompanyDAO extends DAO<Company> {

    private VacancyDAO vdao;

    public CompanyDAO(VacancyDAO vdao) {
        super("company");
        this.vdao = vdao;
    }

    public Long persists(Company company) throws SQLException {
        String sql = String.format("insert into company(email, pass, company_name, description) " +
                        "values('%s', '%s', '%s', '%s') RETURNING company_id",
                company.getEmail(),
                company.getPassword(),
                company.getCompName(),
                company.getDescription()
        );
        Long id = insert(sql);

        company.setId(id);

        for (Vacancy v: company.getOffers()) {
            vdao.persists(v, company.getId());
        }
        return id;
    }

    public Company getByEmail(String email) throws SQLException {
        Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        Statement stmt = conn.createStatement();

        String sql = String.format("SELECT * FROM company WHERE email = '%s'", email);

        try {
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next()) {
                Company cmp = new Company(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
                cmp.setId(rs.getLong(1));

                for (Vacancy v: vdao.getByCompId(cmp.getId())) {
                    cmp.addOffer(v);
                }
                return cmp;
            } else {
                throw new IllegalArgumentException("No such company");
            }

        } finally {
            stmt.close();
            conn.close();
        }
    }

    public Company update(Company cmp) throws SQLException {
        Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

        String sql = "UPDATE company SET email = ?" +
                ", pass = ?" +
                ", company_name = ?" +
                ", description = ?" +
                " WHERE company_id = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, cmp.getEmail());
        stmt.setString(2, cmp.getPassword());
        stmt.setString(3, cmp.getCompName());
        stmt.setString(4, cmp.getDescription());
        stmt.setLong(5, cmp.getId());

        try {
            stmt.executeUpdate();

            return cmp;
        } finally {
            stmt.close();
            conn.close();
        }
    }
}