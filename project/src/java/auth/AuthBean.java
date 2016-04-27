package auth;

import entities.Person;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import session.ClientFacade;
import session.CoachFacade;
import session.DoctorFacade;
import session.ManagerFacade;
import session.PersonFacade;

/**
 *
 * @author magniii
 */
@SessionScoped
public class AuthBean implements Serializable{

    @EJB
    private PersonFacade pf;
    @EJB
    private ManagerFacade mf;
    @EJB
    private DoctorFacade df;
    @EJB
    private CoachFacade cof;
    @EJB
    private ClientFacade cf;
    
    private String login = "";
    private String password = "";
    private boolean loggedIn = false;

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

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.loggedIn = isLoggedIn;
    }
    
    public boolean checkAuth(){
        if(!isLoggedIn()){
            return (authorize() != null);
        }else{
            return true;
        }
    }
    
    public Object authorize(){
        Person p = pf.getPerson(getLogin(), getPassword());
        Object result;
        
        if(p == null){
            return null;
        }else{
            result = cf.find(p.getId());
            if(result != null){
                return result;
            }
            
            result = mf.find(p.getId());
            if(result != null){
                return result;
            }
            
            result = cof.find(p.getId());
            if(result != null){
                return result;
            }
            
            result = df.find(p.getId());
            if(result != null){
                return result;
            }
            
            return null;
        }
    }
}
