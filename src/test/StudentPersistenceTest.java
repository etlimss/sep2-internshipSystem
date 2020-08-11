package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.persistence.StudentDAO;
import shared.domain.Student;

import java.sql.SQLException;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class StudentPersistenceTest {
    private Random rand = new Random();
    private StudentDAO dao;

    @BeforeEach
    public void setup() {
        dao = new StudentDAO();
    }

    @Test
    public void testInsert() throws SQLException {
        Student s1 = new Student(randomString(), randomString(), randomString(), rand.nextInt(), ((char) (rand.nextInt('Z' - 'A') + 'A')), randomString(),randomString(), randomString(), randomString());
        assertNull(s1.getId());

        dao.persists(s1);

        assertNotNull(s1.getId());
    }

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
