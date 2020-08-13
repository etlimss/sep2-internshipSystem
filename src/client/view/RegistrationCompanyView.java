package client.view;

import client.viewmodel.RegistrationCompanyVM;
import client.viewmodel.RegistrationStudentVM;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.converter.NumberStringConverter;

public class RegistrationCompanyView {
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private TextField compName;
    @FXML
    private TextArea compInfo;

    private RegistrationCompanyVM rsvm;

    private ViewFactory viewFactory;

    public RegistrationCompanyView(RegistrationCompanyVM rsvm, ViewFactory viewFactory) {
        this.rsvm = rsvm;
        this.viewFactory = viewFactory;
    }

    @FXML
    public void initialize(){
        rsvm.emailProperty().bindBidirectional(email.textProperty());
        rsvm.passwordProperty().bindBidirectional(password.textProperty());
        rsvm.compNameProperty().bindBidirectional(compName.textProperty());
        rsvm.compInfoProperty().bindBidirectional(compInfo.textProperty());

    }
    @FXML
    public void register(){
        rsvm.register();
    }


}
