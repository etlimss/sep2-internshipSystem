package client.view;

import client.viewmodel.LoginVM;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class LoginView {

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private RadioButton student;

    @FXML
    private RadioButton company;

    private LoginVM lvm;

    public LoginView(LoginVM lvm) {
        this.lvm = lvm;
    }

    @FXML
    public void logIn() {
        lvm.login();
    }

    @FXML
    private void initialize() {
        lvm.emailProperty().bindBidirectional(email.textProperty());
        lvm.passwordProperty().bindBidirectional(password.textProperty());
        lvm.isStudentProperty().bindBidirectional(student.selectedProperty());
        lvm.isCompanyProperty().bindBidirectional(student.selectedProperty());
    }




}
