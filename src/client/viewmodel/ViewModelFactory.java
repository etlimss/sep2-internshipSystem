package client.viewmodel;

import shared.mediator.InternshipMediator;

public class ViewModelFactory {

    private InternshipMediator internmed;

    public ViewModelFactory(InternshipMediator internmed) {
        this.internmed = internmed;
    }

    public LoginVM getLoginVM() {
        return new LoginVM(internmed);

    }


}
