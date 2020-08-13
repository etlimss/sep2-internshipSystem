package server.mediator;

import server.persistence.CompanyDAO;
import server.persistence.StudentDAO;
import server.persistence.VacancyDAO;
import shared.domain.Company;
import shared.domain.Student;
import shared.domain.Vacancy;
import shared.mediator.InternshipMediator;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class InternshipMediatorIMPL implements InternshipMediator {



    private StudentDAO sdao;
    private CompanyDAO cdao;
    private VacancyDAO vdao;


    public InternshipMediatorIMPL(StudentDAO sdao, CompanyDAO cdao, VacancyDAO vdao) {
        this.sdao = sdao;
        this.cdao = cdao;
        this.vdao = vdao;
    }


    @Override
    public Company registerCompany(String email, String password, String compName, String description) throws RemoteException {
        Company cmp = new Company( email,  password,  compName,  description);

        try{
            cdao.persists(cmp);
        }
        catch (SQLException e) {
            throw new RuntimeException();
        }

        return cmp;
    }

    @Override
    public Student registerStudent(String email, String password, String fName, int age, char gender, String education, String workingEx, String personalStat, String contInfo) throws RemoteException {
        Student student = new Student(email, password, fName, age, gender, education, workingEx, personalStat, contInfo);

        try {
            sdao.persists(student);
        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return student;
    }

    @Override
    public Vacancy createVacancy(String description, double salary, Long id) throws RemoteException {
        Vacancy vacancy = new Vacancy(description, salary);

        try {
            vdao.persists(vacancy, id);
        } catch (SQLException e) {
            throw new RuntimeException();//Runtime cuz itis unchecked and compiler dont give a fuk about it
        }


        return vacancy;
    }

    @Override
    public ArrayList<Vacancy> getAllVacancies() throws RemoteException {


        try {
            return vdao.getAllVacancies();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void applyForVacancy(Long sId, Long vId) throws RemoteException {
        try {
            sdao.applyForVacancy(sId,vId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student loginStudent(String email, String pass) throws RemoteException {
        try {
            Student s = sdao.getByEmail(email);

            if (s.getPassword().equals(pass)){
                return s;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (IllegalArgumentException e){
            return null;
        }
        return null;
    }

    @Override
    public Company loginCompany(String email, String pass) throws RemoteException {

        try {

            Company c = cdao.getByEmail(email);

            if (c.getPassword().equals(pass)){

                return c;

            }

        }
        catch (SQLException e) {
            throw new RuntimeException();
        }
        catch (IllegalArgumentException e){
             return null;
        }

        return null;
    }

    @Override
    public Student updateStudent(Student s) throws RemoteException {

        try {
            sdao.update(s);
        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return s;
    }

    @Override
    public Company updateCompany(Company c) throws RemoteException {

        try {
            cdao.update(c);
        } catch (SQLException e) {
            throw new RuntimeException();
        }


        return c;
    }

    @Override
    public void removeVacancy(Vacancy v) throws RemoteException {
        try {
            vdao.remove(v.getId());
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public ArrayList<Student> getApplicants(Long vId) throws RemoteException {
        try {
            ArrayList<Student> applicants = sdao.getVacApplicants(vId);
            System.out.println(applicants);
            return applicants;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
