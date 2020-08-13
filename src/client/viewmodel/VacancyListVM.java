package client.viewmodel;

import client.InternshipClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.domain.Company;
import shared.domain.Vacancy;
import shared.mediator.InternshipMediator;

import java.rmi.RemoteException;

public class VacancyListVM {

    private ObservableList<Vacancy> olv;
    private InternshipMediator intrnMed;

    private InternshipClient client;


    public VacancyListVM(InternshipMediator intrnMed, InternshipClient client) {
        this.intrnMed = intrnMed;
        this.client = client;

        try {
            olv = FXCollections.observableList(intrnMed.getAllVacancies());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Vacancy> getVacancyList() {
        return olv;
    }

    public boolean isCompany() {
        return client.getLoggedIn() instanceof Company;
    }

}
