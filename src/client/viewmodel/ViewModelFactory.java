package client.viewmodel;

import client.InternshipClient;
import shared.domain.Vacancy;
import shared.mediator.InternshipMediator;

public class ViewModelFactory {

    private InternshipMediator internmed;

    private LoginVM loginvm;
    private InternshipClient client;
    private CreateVacancyVM  cvvm;
    private VacancyListVM vlvm;
    private RegistrationStudentVM rsvm;
    private RegistrationCompanyVM rcvm;
    private VacancyVM vvm;

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
        if(vlvm == null) {
          vlvm = new VacancyListVM(internmed, client);
        }
        return vlvm;
    }

    public RegistrationStudentVM getStudRegVM(){
      if(rsvm == null) {
          rsvm = new RegistrationStudentVM(internmed);
      }
      return rsvm;
    }

    public CreateVacancyVM getCreateVacancyVM(){
        if (cvvm == null)
            cvvm = new CreateVacancyVM(internmed,client);

        return cvvm;
    }
    public RegistrationCompanyVM getCompRegVM(){
       if(rcvm == null){
           rcvm = new RegistrationCompanyVM(internmed);
       }
       return rcvm;
    }
    public VacancyVM getVacancyVM(Vacancy v ){
        if (vvm == null){
            vvm = new VacancyVM(internmed,client,v);
        }

        return vvm;
    }
}
