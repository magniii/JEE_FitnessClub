package managed;

import entities.Application;
import entities.Client;
import entities.Person;
import java.util.Arrays;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import session.ApplicationFacade;
import session.ClientFacade;
import session.PersonFacade;

/**
 *
 * @author magniii
 */
@ManagedBean(name="registerBean")
@RequestScoped
public class RegisterBean {

    @EJB
    PersonFacade pf;
    @EJB
    ClientFacade cf;
    @EJB
    ApplicationFacade af;
    
    private String login;
    private String password;
    private String forname;
    private String surname;
    
    public RegisterBean() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getForname() {
        return forname;
    }

    public void setForname(String forname) {
        this.forname = forname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String registerClient(){
        if(login.isEmpty() || password.isEmpty() || forname.isEmpty() || surname.isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empty fields are not acceptable"));
            return null;
        }
        
        if(pf.getPerson(login, password) != null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User with login " + login + " already exists"));
            return null;
        }
        
        Client c = new Client();
        Person p = new Person(forname, surname, login, password);
        c.setPersonId(p);
        p.setClientList(Arrays.asList(c));
        Application a = new Application(c);
        c.setApplicationList(Arrays.asList(a));
        cf.create(c);
        
        return "auth.xhtml?faces-redirect=true";
    }
}
