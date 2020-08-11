package server.persistence;


import shared.domain.Company;
import shared.domain.Student;
import shared.domain.Vacancy;

import java.sql.*;

public class StudentDAO extends DAO<Student> {

    private VacancyDAO vdao;

    public StudentDAO(VacancyDAO vdao) {

        super("student");
        this.vdao = vdao;
    }

    public Long persists(Student student) throws SQLException {
        Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        Statement stmt = conn.createStatement();
        PreparedStatement prs = conn.prepareStatement("INSERT INTO student_vacancy VALUES(?, ?)");


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

        try {
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();

            student.setId(rs.getLong(1));

            for (Vacancy v: student.getVacanciesApplied()) {
                prs.setLong(1, student.getId());
                prs.setLong(2, v.getId());
                prs.executeUpdate();
            }

        } finally {
            prs.close();
            stmt.close();
            conn.close();
        }



        return student.getId();
    }

    public Student getByEmail(String email) throws SQLException {
        Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        Statement stmt = conn.createStatement();
        PreparedStatement prs = conn.prepareStatement("SELECT vacancy_id FROM student_vacancy WHERE student_id = ?");

        String sql = String.format("SELECT * FROM student WHERE email = '%s'", email);

        try {
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next()) {
                Student st = new Student(
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
                prs.setLong(1,st.getId());
                ResultSet vrs = prs.executeQuery();
                while (vrs.next()) {
                    Vacancy v = vdao.getById(vrs.getLong(1));

                    st.addVacancy(v);
                }
                return st;
            } else {
                throw new IllegalArgumentException("No such student");
            }
        } finally {
            prs.close();
            stmt.close();
            conn.close();
        }
    }

    public Student update(Student st) throws SQLException {
        Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

        String sql = "UPDATE student SET email = ?" +
                ", pass = ?" +
                ", fullname = ?" +
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