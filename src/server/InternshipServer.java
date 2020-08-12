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

public class InternshipServer {
    public static final int PORT = 1001;
    public static final String mediatorName = "internshipMediator";

    public static void main(String[] args) throws RemoteException {

        Registry reg = LocateRegistry.createRegistry(PORT);

        VacancyDAO vdao = new VacancyDAO();
        InternshipMediator mediator = new InternshipMediatorIMPL(new StudentDAO(vdao), new CompanyDAO(vdao), vdao);

        InternshipMediator proxy = (InternshipMediator) UnicastRemoteObject.exportObject(mediator, 0);
        reg.rebind(mediatorName, proxy);

    }
}
