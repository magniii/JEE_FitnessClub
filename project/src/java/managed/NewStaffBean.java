package managed;

import auth.ManagerAuthBean;
import entities.Coach;
import entities.Doctor;
import entities.Manager;
import entities.Person;
import java.io.Serializable;
import java.util.Arrays;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import session.CoachFacade;
import session.DoctorFacade;
import session.ManagerFacade;
import session.PersonFacade;

/**
 *
 * @author magniii
 */
@ManagedBean(name = "newStaffBean")
@RequestScoped
public class NewStaffBean {

    @EJB
    PersonFacade pf;
    
    @EJB
    CoachFacade cf;
    
    @EJB
    DoctorFacade df;
    
    @EJB
    ManagerFacade mf;
    
    @ManagedProperty(value = "#{managerAuthBean}")
    ManagerAuthBean mab;
    
    String login = "";
    String password = "";
    String forname = "";
    String surname = "";
    
    public NewStaffBean() {
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

    public ManagerAuthBean getMab() {
        return mab;
    }

    public void setMab(ManagerAuthBean mab) {
        this.mab = mab;
    }
    
    public String createManager(){
        if(login.isEmpty() || password.isEmpty() || forname.isEmpty() || surname.isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empty fields are not acceptable"));
            return null;
        }
        
        if(pf.getPerson(login, password) != null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User with login " + login + " already exists"));
            return null;
        }
        
        Manager m = new Manager();
        Person p = new Person(forname, surname, login, password);
        m.setPersonId(p);
        p.setManagerList(Arrays.asList(m));
        mf.create(m);
        
        return "manager.xhtml?faces-redirect=true";
    }
    
    public String createCoach(){
        if(login.isEmpty() || password.isEmpty() || forname.isEmpty() || surname.isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empty fields are not acceptable"));
            return null;
        }
        
        if(pf.getPerson(login, password) != null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User with login " + login + " already exists"));
            return null;
        }
        
        Coach c = new Coach();
        Person p = new Person(forname, surname, login, password);
        c.setPersonId(p);
        p.setCoachList(Arrays.asList(c));
        cf.create(c);
        
        return "manager.xhtml?faces-redirect=true";
    }
    
    public String createDoctor(){
        if(login.isEmpty() || password.isEmpty() || forname.isEmpty() || surname.isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empty fields are not acceptable"));
            return null;
        }
        
        if(pf.getPerson(login, password) != null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User with login " + login + " already exists"));
            return null;
        }
        
        Doctor d = new Doctor();
        Person p = new Person(forname, surname, login, password);
        d.setPersonId(p);
        p.setDoctorList(Arrays.asList(d));
        df.create(d);
        
        return "manager.xhtml?faces-redirect=true";
    }
}
