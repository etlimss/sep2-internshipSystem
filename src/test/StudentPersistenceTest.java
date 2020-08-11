package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.persistence.CompanyDAO;
import server.persistence.StudentDAO;
import server.persistence.VacancyDAO;
import shared.domain.Company;
import shared.domain.Student;
import shared.domain.Vacancy;

import java.sql.SQLException;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class StudentPersistenceTest {
    private Random rand = new Random();
    private StudentDAO dao;
    private CompanyDAO cdao;

    @BeforeEach
    public void setup() {
        VacancyDAO vdao = new VacancyDAO();
        dao = new StudentDAO(vdao);
        cdao = new CompanyDAO(vdao);
    }

    @Test
    public void testInsert() throws SQLException {
        String email = randomString();
        Student s1 = new Student(email, randomString(), randomString(), rand.nextInt(), ((char) (rand.nextInt('Z' - 'A') + 'A')), randomString(),randomString(), randomString(), randomString());
        assertNull(s1.getId());
        Company c1 = new Company(randomString(), randomString(), randomString(), randomString());

        for(int i = 0; i < 10; i++) {
            Vacancy v = new Vacancy(randomString(), randSalary());
            c1.addOffer(v);
            s1.addVacancy(v);
        }
        cdao.persists(c1);

        dao.persists(s1);

        assertNotNull(s1.getId());

        Student res = dao.getByEmail(email);

        assertEquals(res.getVacanciesApplied(), c1.getOffers());
    }
    public double randSalary() { return (int)(rand.nextDouble() * 100) / 100.0; }


    @Test
    public void testGet() throws SQLException {
        String email = randomString();
        Student st = new Student(email, randomString(), randomString(), rand.nextInt(), ((char) (rand.nextInt('Z' - 'A') + 'A')), randomString(),randomString(), randomString(), randomString());

        dao.persists(st);

        assertNotNull(st.getId());

        Student res = dao.getByEmail(email);
        assertEquals(st.getId(), res.getId());
    }

    public String randomString() {
        return UUID.randomUUID().toString();
    }

    @Test
    public void testUpdate() throws SQLException {
        String email = randomString();

        Student s1 =  new Student(email, randomString(), randomString(), rand.nextInt(), ((char) (rand.nextInt('Z' - 'A') + 'A')), randomString(),randomString(), randomString(), randomString());

        dao.persists(s1);

        s1.setAge(15);

        dao.update(s1);

        Student res = dao.getByEmail(email);

        assertEquals(s1,res);
    }

}
