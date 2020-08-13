package client;

import client.view.ViewFactory;
import client.viewmodel.ViewModelFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.InternshipServer;
import shared.domain.Student;
import shared.mediator.InternshipMediator;

import java.nio.file.Paths;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class InternshipClient extends Application {

    public static final String host = "localhost";

    private Object loggedIn;

    @Override
    public void start(Stage stage) throws Exception {
        Registry reg;
        try {
            reg = LocateRegistry.getRegistry(host, InternshipServer.PORT);

            InternshipMediator proxy = (InternshipMediator) reg.lookup(InternshipServer.mediatorName); // SHOULD NOT USE INTERNSHIPSERVER

            ViewModelFactory vmf = new ViewModelFactory(proxy, this);
            ViewFactory vf = new ViewFactory(vmf, stage);

            stage.setScene(new Scene(vf.loadView(vf.getLoginView(), "Login.fxml")));
            stage.show();
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Could not connect to the RMI registry: ");
            throw new RuntimeException(e);
        }
    }

    public Object getLoggedIn() {
        return loggedIn;
    }

    public void setloggedIn(Object loggedIn) {
        this.loggedIn = loggedIn;
    }
}
