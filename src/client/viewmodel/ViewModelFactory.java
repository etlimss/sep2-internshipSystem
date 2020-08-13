package client.viewmodel;

import client.InternshipClient;
import shared.domain.Vacancy;
import shared.mediator.InternshipMediator;

public class ViewModelFactory {

    private InternshipMediator internmed;

    private LoginVM loginvm;
    private InternshipClient client;
    private CreateVacancyVM  cvvm;

    public ViewModelFactory(InternshipMediator internmed, InternshipClient client) {
        this.internmed = internmed;
        this.client = client;
    }

    public LoginVM getLoginVM() {
        if(loginvm == null)
            loginvm = new LoginVM(internmed, client);

        return loginvm;
    }


    public VacancyListVM getVacanciesVM(){
        return new VacancyListVM(internmed, client);
    }

    public RegistrationStudentVM getStudRegVM(){
        return new RegistrationStudentVM(internmed);
    }

    public CreateVacancyVM getCreateVacancyVM(){
        if (cvvm == null)
            cvvm = new CreateVacancyVM(internmed,client);

        return cvvm;
    }
    public RegistrationCompanyVM getCompRegVM(){
        return new RegistrationCompanyVM(internmed);
    }
    public VacancyVM getVacancyVM(Vacancy v ){
        return new VacancyVM(internmed,client,v);
    }
}
