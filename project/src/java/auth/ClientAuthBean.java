package auth;

import entities.Client;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import session.ClientFacade;

/**
 *
 * @author magniii
 */
@ManagedBean(name = "clientAuthBean")
@SessionScoped
public class ClientAuthBean extends AuthBean implements Serializable{
    
    @EJB
    ClientFacade cf;
    
    Client client;
    
    public ClientAuthBean() {
    }

    @Override
    protected String authorize(Integer personId) {
        client = cf.getClientByPersonId(personId);
        
        if(client == null){
            return null;
        }else{
            setLoggedIn(true);
            return "/client/client.xhtml?faces-redirect=true";
        }
    }
    
}
