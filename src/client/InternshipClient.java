package client;

import server.InternshipServer;
import shared.mediator.InternshipMediator;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class InternshipClient {
    public static final String host = "localhost";

    public static void main(String[] args) {
        Registry reg;
        try {
            reg = LocateRegistry.getRegistry(host, InternshipServer.PORT);

            InternshipMediator proxy = (InternshipMediator) reg.lookup(InternshipServer.mediatorName); // SHOULD NOT USE INTERNSHIPSERVER

         } catch (RemoteException | NotBoundException e) {
            System.out.println("Could not connect to the RMI registry: ");
            throw new RuntimeException(e);
        }


    }
}
