package server;

import server.mediator.InternshipMediatorIMPL;
import server.persistence.CompanyDAO;
import server.persistence.StudentDAO;
import server.persistence.VacancyDAO;
import shared.domain.Student;
import shared.mediator.InternshipMediator;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class InternshipServer {
    public static final int PORT = 8866;
    public static final String mediatorName = "internshipMediator";

    public static void main(String[] args) throws RemoteException, SQLException {

        Registry reg = LocateRegistry.createRegistry(PORT);

        VacancyDAO vdao = new VacancyDAO();
        StudentDAO sdao = new StudentDAO(vdao);

        Student s = new Student("g@gmail.com","0000","ggg",12,'m',"","","","" );
        sdao.create(s);

        InternshipMediator mediator = new InternshipMediatorIMPL(sdao, new CompanyDAO(vdao), vdao);

        InternshipMediator proxy = (InternshipMediator) UnicastRemoteObject.exportObject(mediator, 0);
        reg.rebind(mediatorName, mediator);

    }
}
