package client.view;

import client.viewmodel.VacancyVM;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;
import shared.domain.Student;

public class VacancyView {
    @FXML private Label description;
    @FXML private Label salary;
    @FXML private TableView<Student> candidateList;
    @FXML private TableColumn<Student, String> fName;
    @FXML private TableColumn<Student, String> education;
    @FXML private TableColumn<Student, String> workingEx;
    @FXML private TableColumn<Student, String> contact;
    @FXML private Button applyVacancy;
    @FXML private VBox root;
    private ViewFactory vf;
    private VacancyVM vvm;


    public VacancyView(ViewFactory vf, VacancyVM vvm) {
        this.vf = vf;
        this.vvm = vvm;
    }

    @FXML
    private void initialize(){
        description.textProperty().bindBidirectional(vvm.descriptionProperty());
        Bindings.bindBidirectional(salary.textProperty(),vvm.salaryProperty(), new NumberStringConverter());


        fName.setCellValueFactory(new PropertyValueFactory<Student, String>("fName"));
        education.setCellValueFactory(new PropertyValueFactory<Student, String>("education"));
        workingEx.setCellValueFactory(new PropertyValueFactory<Student, String>("workingEx"));
        contact.setCellValueFactory(new PropertyValueFactory<Student,String>("contInfo"));
        candidateList.setItems(vvm.getStudentList());
        if(!vvm.isCompany()) {
            root.getChildren().remove( candidateList);
        }

        if(!vvm.isStudent()) {
            root.getChildren().remove(applyVacancy);
        }

    }

    @FXML
    public void applyForVacancy(){
        vvm.applyForVacancy();
    }
}
