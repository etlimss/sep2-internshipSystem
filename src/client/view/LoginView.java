package client.view;

import client.viewmodel.LoginVM;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import shared.domain.Student;


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
    private ViewFactory vf;

    public LoginView(LoginVM lvm, ViewFactory vf) {
        this.lvm = lvm;
        this.vf = vf;
    }

    @FXML
    public void logIn() {
        if(student.isSelected()) {
            if(lvm.loginStudent()) {
                vf.open(vf.getVacanciesView(), "VacancyListView.fxml");
            }
        } else if(company.isSelected()) {
            if(lvm.loginCompany())
                vf.open(vf.getVacanciesView(), "VacancyListView.fxml");
        }
    }

    @FXML
    private void initialize() {
        lvm.emailProperty().bindBidirectional(email.textProperty());
        lvm.passwordProperty().bindBidirectional(password.textProperty());

        ToggleGroup tg = new ToggleGroup();
        student.setToggleGroup(tg);
        company.setToggleGroup(tg);
        lvm.isStudentProperty().bindBidirectional(student.selectedProperty());
        lvm.isCompanyProperty().bindBidirectional(company.selectedProperty());
    }

    @FXML
    public void registerStudent(){
        vf.openInNewWindow(vf.getStudRegView(),"RegistrationStudent.fxml");
    }
    @FXML
    public void registerCompany(){
        vf.openInNewWindow(vf.getCompRegView(),"RegistrationCompany.fxml");
    }

}
