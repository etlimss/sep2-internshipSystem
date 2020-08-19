package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.persistence.CompanyDAO;
import server.persistence.VacancyDAO;
import shared.domain.Company;
import shared.domain.Vacancy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VacancyPersistenceTest {

    private Random rand = new Random();
    private VacancyDAO dao;
    private CompanyDAO cdao;

    @BeforeEach
    public void setup() {
        dao = new VacancyDAO();
        cdao = new CompanyDAO(dao);
    }


    @Test
    public void testInsert() throws SQLException {
        Company c1 = new Company(randomString(), randomString(), randomString(), randomString());

        cdao.create(c1);

        Vacancy v1 = new Vacancy(randomString(), randSalary(), c1.getId());
        assertNull(v1.getId());

        dao.create(v1);

        assertNotNull(v1.getId());
    }

    @Test
    public void testGet() throws SQLException {
        String email = randomString();
        Company c1 = new Company(randomString(), randomString(), randomString(), randomString());

        cdao.create(c1);

        Vacancy v1 = new Vacancy(randomString(), randSalary(), c1.getId());
        dao.create(v1);

        assertNotNull(v1.getId());

        ArrayList<Vacancy> res = dao.getByCompId(c1.getId());
        assertEquals(res.size(), 1);
        
        assertEquals(v1, res.get(0));
    }

    @Test
    public void testUpdate() throws SQLException {
        Company c1 = new Company(randomString(), randomString(), randomString(), randomString());

        cdao.create(c1);

        Vacancy v1 = new Vacancy(randomString(), randSalary(), c1.getId());
        dao.create(v1);

        v1.setDescription(randomString());

        dao.update(v1);

        Vacancy res = dao.getByCompId(c1.getId()).get(0);

        assertEquals(v1, res);

    }

    public String randomString() {
        return UUID.randomUUID().toString();
    }

    public double randSalary() { return (int)(rand.nextDouble() * 100) / 100.0; }
}
