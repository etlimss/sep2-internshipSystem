package server.persistence;


import shared.domain.Vacancy;

import java.sql.*;
import java.util.ArrayList;

public class VacancyDAO extends DAO<Vacancy> {

    public VacancyDAO() {
        super("vacancy");
    }

    public Long persists(Vacancy vacancy, Long company_id) throws SQLException {
        String sql = String.format("insert into vacancy(description, salary, company_id) " +
                        "values('%s', %f, %d) RETURNING vacancy_id",
                vacancy.getDescription(),
                vacancy.getSalary() , company_id
        );
        Long id = insert(sql);

        vacancy.setId(id);
        return id;
    }

    public ArrayList<Vacancy> getByCompId(Long id) throws SQLException {
        Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        Statement stmt = conn.createStatement();

        String sql = String.format("SELECT * FROM vacancy WHERE company_id = %d", id);

        try {
            ResultSet rs = stmt.executeQuery(sql);

            ArrayList<Vacancy> vac = new ArrayList<>();
            while(rs.next()) {
                Vacancy v = new Vacancy(
                        rs.getString(2),
                        rs.getDouble(3)
                );
                v.setId(rs.getLong(1));
                vac.add(v);
            }
            return vac;
        } finally {
            stmt.close();
            conn.close();
        }
    }


   public Vacancy update(Vacancy vc) throws SQLException {
        Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

        String sql = "UPDATE vacancy SET description = ?" +
                ", salary = ? WHERE vacancy_id = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, vc.getDescription());
        stmt.setDouble(2, vc.getSalary());
        stmt.setLong(3, vc.getId());


        try {
            stmt.executeUpdate();

            return vc;
        } finally {
            stmt.close();
            conn.close();
        }
    }

    public void remove(Long id) throws SQLException {
        Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

        String sql = "DELETE FROM vacancy WHERE vacancy_id = ?";

        PreparedStatement stp = conn.prepareStatement(sql);

        stp.setLong(1, id);

        try {
            stp.executeUpdate();
        } finally {
            stp.close();
            conn.close();
        }
    }

    public Vacancy getById (Long id) throws SQLException {
        Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        Statement stmt = conn.createStatement();

        String sql = String.format("SELECT * FROM vacancy WHERE vacancy_id = %d", id);

        try {
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next()) {
                Vacancy v = new Vacancy(
                        rs.getString(2),
                        rs.getDouble(3)
                );
                v.setId(rs.getLong(1));
                return v;
            } else {
                throw new IllegalArgumentException("No such vacancy");
            }

        } finally {
            stmt.close();
            conn.close();
        }
    }
}