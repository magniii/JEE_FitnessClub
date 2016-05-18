package managed;

import auth.DoctorAuthBean;
import entities.Application;
import entities.Client;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import session.ApplicationFacade;
import session.ApplicationStateFacade;
import session.ClientFacade;
import session.DoctorFacade;
import session.DoctorclientsFacade;

/**
 *
 * @author magniii
 */
@ManagedBean(name = "doctorIndexBean")
@RequestScoped
public class DoctorIndexBean {

    @EJB
    DoctorFacade df;
    
    @EJB
    ClientFacade cf;
    
    @EJB
    ApplicationFacade af;
    
    @EJB
    ApplicationStateFacade asf;
    
    @EJB
    DoctorclientsFacade dcf;
    
    @ManagedProperty(value = "#{doctorAuthBean}")
    DoctorAuthBean dab;
    
    List<Client> clients;
    
    public DoctorIndexBean() {
    }
    
    @PostConstruct
    public void init() {
        clients = dcf.getDoctorsClients(dab.getDoctor().getId());
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public DoctorAuthBean getDab() {
        return dab;
    }

    public void setDab(DoctorAuthBean dab) {
        this.dab = dab;
    }

    public List<Client> getDoctorsClients() {
        return clients;
    }

    public void setDoctorsClients(List<Client> clients) {
        this.clients = clients;
    }
    
    public String modifyAppStatus(){
        Map<String,String> params;
        params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	Integer clientId = Integer.parseInt(params.get("modifyappstate"));
        
        Application app = cf.find(clientId).getApplicationList().get(0);
        
        int stateId = app.getStateId().getId();
        switch(stateId){
            case 3: //doctor_assigned
                app.setStateId(asf.find(4));
                af.edit(app);
                break;
            default:
                break;
        }

        return "doctor.xhtml?faces-redirect=true";
    }
}
