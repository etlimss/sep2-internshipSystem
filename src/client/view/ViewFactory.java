package client.view;

import client.viewmodel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;

public class ViewFactory {

    private ViewModelFactory vmf;

    public ViewFactory(ViewModelFactory vmf) {
        this.vmf = vmf;
    }

    public Parent loadView(Object view, String path) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(path));
            loader.setController(view);
            return loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public LoginView getLoginView() {
       return new LoginView(vmf.getLoginVM());

    }

}
