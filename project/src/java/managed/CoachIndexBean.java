package managed;

import auth.CoachAuthBean;
import entities.Application;
import entities.Client;
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
import session.CoachclientsFacade;

/**
 *
 * @author magniii
 */
@ManagedBean(name = "coachIndexBean")
@RequestScoped
public class CoachIndexBean {
    
    @EJB
    CoachclientsFacade ccf;
    
    @EJB
    ApplicationFacade af;
    
    @EJB
    ClientFacade cf;
    
    @EJB
    ApplicationStateFacade asf;
    
    @ManagedProperty(value = "#{coachAuthBean}")
    CoachAuthBean cab;
    
    List<Client> clients;
    String tmpText;

    public CoachIndexBean() {
    }
    
    @PostConstruct
    public void init(){
        clients = ccf.getCoachesClients(cab.getCoach().getId());
        tmpText = "";
    }

    public String getTmpText() {
        return tmpText;
    }

    public void setTmpText(String tmpText) {
        this.tmpText = tmpText;
    }

    public CoachAuthBean getCab() {
        return cab;
    }

    public void setCab(CoachAuthBean cab) {
        this.cab = cab;
    }

    public List<Client> getClients() {
        return clients;
    }
    
    public String modifyAppStatus(){
        Map<String,String> params;
        params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	Integer clientId = Integer.parseInt(params.get("clientId"));
        
        Application app = cf.find(clientId).getApplicationList().get(0);
        int stateId = app.getStateId().getId();
        switch(stateId){
            case 8: // send form
                app.setText(tmpText);
                app.setStateId(asf.find(9));
                af.edit(app);
                break;
            case 10: // send program
                app.setText(tmpText);
                app.setStateId(asf.find(11));
                af.edit(app);
                break;
            default:
                break;
        }
        
        return "coach.xhtml?faces-redirect=true";
    }
}
