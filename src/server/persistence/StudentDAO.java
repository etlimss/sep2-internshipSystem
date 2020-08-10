package server.persistence;


import shared.domain.Student;

import java.sql.*;

public class StudentDAO extends DAO<Student> {

    public StudentDAO() {
        super("student");
    }

    public Long persists(Student student) throws SQLException {
        String sql = String.format("insert into student(email, pass, fullname, age, gender, education, working_ex, personal_stat, contact_info) " +
                        "values('%s', '%s', '%s', %d, '%c', '%s', '%s', '%s', '%s') RETURNING student_id",
                student.getEmail(),
                student.getPassword(),
                student.getfName(),
                student.getAge(),
                student.getGender(),
                student.getEducation(),
                student.getWorkingEx(),
                student.getPersonalStat(),
                student.getContInfo()
        );
        Long id = insert(sql);

        student.setId(id);
        return id;
    }

    public Student getByEmail(String email) throws SQLException {
        Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        Statement stmt = conn.createStatement();

        String sql = String.format("SELECT * FROM student WHERE email = '%s'", email);

        try {
            ResultSet rs = stmt.executeQuery(sql);

            Student st;
            if(rs.next()) {
                st = new Student(
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
                st.setId(rs.getLong(1));
            } else {
                throw new IllegalArgumentException("No such student");
            }
            return st;
        } finally {
            stmt.close();
            conn.close();
        }
    }

    public Student update(Student st) throws SQLException {
        Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

        String sql = "UPDATE student SET email = ?" +
                ", password = ?" +
                ", fName = ?" +
                ", age = ?" +
                ", gender = ?" +
                ", education = ?" +
                ", working_ex = ?" +
                ", personal_stat = ?" +
                ", contact_info = ?" +
                " WHERE student_id = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, st.getEmail());
        stmt.setString(2, st.getPassword());
        stmt.setString(3, st.getfName());
        stmt.setInt(4, st.getAge());
        stmt.setString(5, "" + st.getGender());
        stmt.setString(6, st.getEducation());
        stmt.setString(7, st.getWorkingEx());
        stmt.setString(8, st.getPersonalStat());
        stmt.setString(9, st.getContInfo());
        stmt.setLong(10, st.getId());

        try {
            stmt.executeUpdate();

            return st;
        } finally {
            stmt.close();
            conn.close();
        }
    }
}