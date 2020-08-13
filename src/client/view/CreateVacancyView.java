package client.view;

import client.viewmodel.CreateVacancyVM;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;


public class CreateVacancyView {
    @FXML
    private TextArea description;
    @FXML private TextField salary;

    private CreateVacancyVM cvm;

    private ViewFactory viewFactory;

    public CreateVacancyView(CreateVacancyVM cvm, ViewFactory viewFactory) {
        this.cvm = cvm;
        this.viewFactory = viewFactory;
    }

    @FXML
    public void initialize(){

        cvm.descriptionProperty().bindBidirectional(description.textProperty());
        Bindings.bindBidirectional(salary.textProperty(),cvm.salaryProperty(), new NumberStringConverter());

    }

    public void uploadVacancy(){
        cvm.uploadVacancy();
    }

}
