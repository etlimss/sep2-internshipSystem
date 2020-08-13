package client.view;

import client.viewmodel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {

    private ViewModelFactory vmf;
    private Stage stage;

    public ViewFactory(ViewModelFactory vmf, Stage stage) {
        this.vmf = vmf;
        this.stage = stage;
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

    public void open(Object view, String path) {
        stage.getScene().setRoot(loadView(view, path));
    }

    public void openInNewWindow(Object view, String path) {
        Stage s = new Stage();
        s.setScene(new Scene(loadView(view, path)));
        s.show();
    }

    public LoginView getLoginView() {
       return new LoginView(vmf.getLoginVM(), this);
    }

    public VacancyListView getVacanciesView(){
        return new VacancyListView(vmf.getVacanciesVM(),this);
    }

    public RegistrationStudentView getStudRegView(){
        return new RegistrationStudentView(vmf.getStudRegVM(),this);
    }

    public CreateVacancyView getCreateVacancyView(){ return new CreateVacancyView(vmf.getCreateVacancyVM(),this);}

    public RegistrationCompanyView getCompRegView(){
        return new RegistrationCompanyView(vmf.getCompRegVM(),this);
    }

}
