package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.persistence.CompanyDAO;
import server.persistence.DAO;
import server.persistence.StudentDAO;
import server.persistence.VacancyDAO;
import shared.domain.Company;
import shared.domain.Student;
import shared.domain.Vacancy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CompanyPersistenceTest {

    private Random rand = new Random();
    private CompanyDAO dao;

    @BeforeEach
    public void setup() {

        dao = new CompanyDAO(new VacancyDAO());
    }


    @Test
    public void testInsert() throws SQLException {
        Company c1 = new Company(randomString(), randomString(), randomString(), randomString());
        assertNull(c1.getId());


        dao.persists(c1);

        assertNotNull(c1.getId());
    }

    @Test
    public void testGet() throws SQLException {
        String email = randomString();
        Company cm = new Company(email, randomString(), randomString(), randomString());

        dao.persists(cm);

        assertNotNull(cm.getId());

        Company res = dao.getByEmail(email);
        assertEquals(cm.getId(), res.getId());
    }

    public String randomString() {
        return UUID.randomUUID().toString();
    }

    @Test
    public void testUpdate() throws SQLException {
        String email = randomString();

        Company cmp = new Company(email, randomString(), randomString(), randomString());

        dao.persists(cmp);

        cmp.setDescription(randomString());

        dao.update(cmp);

        Company res = dao.getByEmail(email);

        assertEquals(cmp, res);

    }

    @Test
    public void testOffers() throws SQLException {
        String email = randomString();

        Company cmp = new Company(email, randomString(), randomString(), randomString());


        for(int i = 0; i < 10; i++) {
            cmp.addOffer(new Vacancy(randomString(), randSalary()));
        }
        dao.persists(cmp);
        Company res = dao.getByEmail(email);

        assertEquals(cmp.getOffers(), res.getOffers());
    }
    public double randSalary() { return (int)(rand.nextDouble() * 100) / 100.0; }
}
