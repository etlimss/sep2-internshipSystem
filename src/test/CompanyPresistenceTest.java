package test;

import org.junit.jupiter.api.Test;
import server.persistence.CompanyDAO;
import server.persistence.StudentDAO;
import shared.domain.Company;
import shared.domain.Student;

import java.sql.SQLException;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CompanyPresistenceTest {

    private Random rand = new Random();

    @Test
    public void testInsert() throws SQLException {
        Company c1 = new Company(randomString(), randomString(), randomString(), randomString());
        assertNull(c1.getId());

        CompanyDAO cdao = new CompanyDAO();
        cdao.persists(c1);

        assertNotNull(c1.getId());
    }

    @Test
    public void testGet() throws SQLException {
        String email = randomString();
        Company cm = new Company(email, randomString(), randomString(), randomString());
        CompanyDAO dao = new CompanyDAO();
        dao.persists(cm);

        assertNotNull(cm.getId());

        Company res = dao.getByEmail(email);
        assertEquals(cm.getId(), res.getId());
    }

    public String randomString() {
        return UUID.randomUUID().toString();
    }

    @Test
    public void testUpdate() {

    }
}
