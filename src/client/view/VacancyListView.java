package client.view;

import client.InternshipClient;
import client.viewmodel.VacancyListVM;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import shared.domain.Company;
import shared.domain.Student;
import shared.domain.Vacancy;

public class VacancyListView {
    @FXML
    private TableView<Vacancy> vacancies;
    @FXML private TableColumn<Vacancy, String> description;
    @FXML private TableColumn<Vacancy, Double> salary;
    @FXML private Button createVacancy;
    @FXML private Button openVacancy;

    @FXML private VBox root;
    private ViewFactory vf;
    private VacancyListVM vlvm;

    public VacancyListView(VacancyListVM vlvm, ViewFactory vf) {
        this.vlvm = vlvm;
        this.vf = vf;
    }

    @FXML
    private void initialize(){
        vacancies.setItems(vlvm.getVacancyList());
        description.setCellValueFactory(new PropertyValueFactory<Vacancy, String>("description"));
        salary.setCellValueFactory(new PropertyValueFactory<Vacancy, Double>("salary"));

        if(!vlvm.isCompany()) {
            root.getChildren().remove(createVacancy);
        }
    }

    @FXML
    private void openVacancy(){
        vf.openInNewWindow(vf.getVacancyView(vacancies.getSelectionModel().getSelectedItem()),"VacancyView.fxml");
    }

    @FXML
    public void createVacancy(){
        vf.openInNewWindow(vf.getCreateVacancyView(),"CreateVacancy.fxml");
    }
}