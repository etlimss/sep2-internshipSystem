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
        Vacancy v1 = new Vacancy(randomString(), randSalary());
        Company c1 = new Company(randomString(), randomString(), randomString(), randomString());
        assertNull(v1.getId());

        cdao.persists(c1);
        dao.persists(v1, c1.getId());

        assertNotNull(v1.getId());
    }

    @Test
    public void testGet() throws SQLException {
        String email = randomString();
        Vacancy v1 = new Vacancy(randomString(), randSalary());
        Company c1 = new Company(randomString(), randomString(), randomString(), randomString());

        cdao.persists(c1);
        dao.persists(v1, c1.getId());

        assertNotNull(v1.getId());

        ArrayList<Vacancy> res = dao.getByCompId(c1.getId());
        assertEquals(res.size(), 1);
        
        assertEquals(v1, res.get(0));
    }

    @Test
    public void testUpdate() throws SQLException {

        Vacancy v1 = new Vacancy(randomString(), randSalary());
        Company c1 = new Company(randomString(), randomString(), randomString(), randomString());

        cdao.persists(c1);
        dao.persists(v1, c1.getId());

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
