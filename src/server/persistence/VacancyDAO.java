package server.persistence;


import shared.domain.Vacancy;

import java.sql.*;

public class VacancyDAO extends DAO<Vacancy> {

    public VacancyDAO() {
        super("vacancy");
    }

    public Long persists(Vacancy vacancy) throws SQLException {
        String sql = String.format("insert into vacancy(description, salary) " +
                        "values('%s', %d) RETURNING company_id",
                vacancy.getDescription(),
                vacancy.getSalary()
        );
        Long id = insert(sql);

        vacancy.setId(id);
        return id;
    }


    //WTF to do w this block here? how do we get the vacancy? By what?
//    public Company getByEmail(String email) throws SQLException {
//        Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
//        Statement stmt = conn.createStatement();
//
//        String sql = String.format("SELECT * FROM company WHERE email = '%s'", email);
//
//        try {
//            ResultSet rs = stmt.executeQuery(sql);
//
//            Company cmp;
//            if(rs.next()) {
//                cmp = new Company(
//                        rs.getString(2),
//                        rs.getString(3),
//                        rs.getString(4),
//                        rs.getInt(5),
//                        rs.getString(6).charAt(0),
//                        rs.getString(7),
//                        rs.getString(8),
//                        rs.getString(9),
//                        rs.getString(10)
//                );
//                cmp.setId(rs.getLong(1));
//            } else {
//                throw new IllegalArgumentException("No such student");
//            }
//            return cmp;
//        } finally {
//            stmt.close();
//            conn.close();
//        }
//    }

   /* public Vacancy update(Vacancy vc) throws SQLException {
        Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

        String sql = "UPDATE vacancy SET email = ?" + //wtf to do here?
                ", description = ?" +
                ", salary = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, vc.getEmail()); //need this??
        stmt.setString(5, vc.getDescription());
        stmt.setInt(5, vc.getSalary());


        try {
            stmt.executeUpdate();

            return vc;
        } finally {
            stmt.close();
            conn.close();
        }
    }
*/}