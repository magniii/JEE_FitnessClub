package auth;

import entities.Person;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import session.PersonFacade;

/**
 *
 * @author magniii
 */
@ManagedBean
@SessionScoped
public abstract class AuthBean implements Serializable{

    @EJB
    private PersonFacade pf;
    
    protected String usertype = "";
    protected String login = "";
    protected String password = "";
    protected boolean loggedIn = false;
    protected Person currPerson;

    public AuthBean() {
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
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

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.loggedIn = isLoggedIn;
    }
    
    public boolean checkAuth(){
        return isLoggedIn();
    }
    
    public Person getCurrPerson() {
        return currPerson;
    }

    public void setCurrPerson(Person currPerson) {
        this.currPerson = currPerson;
    }
    
    public String doAuth(){
        currPerson = pf.getPerson(login, password);
        
        if(currPerson == null){
            FacesContext.getCurrentInstance().addMessage("authBlock", new FacesMessage("Incorrect login or password!"));
            return null;
        }else{
            return authorize(currPerson.getId());
        }
    }
    
    protected abstract String authorize(Integer personId);
}
