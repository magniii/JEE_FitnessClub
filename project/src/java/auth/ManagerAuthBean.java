package auth;

import entities.Manager;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import session.ManagerFacade;

/**
 *
 * @author magniii
 */
@ManagedBean(name = "managerAuthBean")
@SessionScoped
public class ManagerAuthBean extends AuthBean implements Serializable{

    @EJB
    ManagerFacade mf;
    
    Manager manager;
    
    public ManagerAuthBean() {
    }

    public Manager getManager() {
        return manager;
    }

    @Override
    protected String authorize(Integer personId) {
        manager = mf.getManagerByPersonId(personId);
        
        if(manager == null){
            return null;
        }else{
            setLoggedIn(true);
            return "/manager/manager.xhtml?faces-redirect=true";
        }
    }
    
}
