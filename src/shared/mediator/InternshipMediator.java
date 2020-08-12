package shared.mediator;

import shared.domain.Company;
import shared.domain.Student;
import shared.domain.Vacancy;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface InternshipMediator extends Remote {

    Company registerCompany (String email, String password, String compName, String description) throws RemoteException;
    Student registerStudent (String email, String password, String fName, int age, char gender, String education, String workingEx, String personalStat,String contInfo) throws RemoteException;
    Vacancy createVacancy (String description, double salary, Long id) throws RemoteException;
    ArrayList<Vacancy> getAllVacancies () throws RemoteException;
    void applyForVacancy(Long sId, Long vId) throws RemoteException;
    Student loginStudent(String email, String pass) throws RemoteException;
    Company loginCompany(String email, String pass) throws RemoteException;
    Student updateStudent(Student s) throws RemoteException;
    Company updateCompany(Company c) throws RemoteException;
    void removeVacancy(Vacancy v) throws RemoteException;
    ArrayList<Student> getApplicants(Long vId) throws RemoteException;
}
