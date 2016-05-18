package managed;

import entities.Client;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import session.ClientFacade;

/**
 *
 * @author magniii
 */
@ManagedBean(name = "brokerForManagerBean")
@RequestScoped
public class BrokerForManagerBean {

    @ManagedProperty(value = "#{managerIndexBean}")
    ManagerIndexBean mib;
    
    @EJB
    ClientFacade cf;
    
    List<Client> clients;
    
    public BrokerForManagerBean() {
    }
    
    @PostConstruct
    public void init(){
        clients = cf.getClients();
    }

    public ManagerIndexBean getMib() {
        return mib;
    }

    public void setMib(ManagerIndexBean mib) {
        this.mib = mib;
    }
    
    public List<Client> getClients() {
        return clients;
    }
    
    
    
}
