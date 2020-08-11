package shared.domain;

import java.util.ArrayList;

public class Company {

    private Long id;

    private String email;
    private String password;
    private String compName;
    private String description;
    private ArrayList<Vacancy> offers;


    public Company(String email, String password, String compName, String description) {
        this.email = email;
        this.password = password;
        this.compName = compName;
        this.description = description;
        offers = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Vacancy> getOffers() {
        return offers;
    }

    public void addOffer(Vacancy vacancy) {
        offers.add(vacancy);
    }

    public void deleteOffer(Vacancy vacancy) {
        offers.remove(vacancy);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Company ){
            Company other = (Company) obj;
            return id == other.id
                    && email.equals(other.email)
                    && password.equals(other.password)
                    && compName.equals(other.compName)
                    && description.equals(other.description)
                    && offers.equals(other.offers);
        }
        else return false;
    }
}
