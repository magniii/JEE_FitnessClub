package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author magniii
 */
@Entity
@Table(name = "APPLICATION_STATE")
@NamedQueries({
    @NamedQuery(name = "ApplicationState.findAll", query = "SELECT a FROM ApplicationState a"),
    @NamedQuery(name = "ApplicationState.findById", query = "SELECT a FROM ApplicationState a WHERE a.id = :id"),
    @NamedQuery(name = "ApplicationState.findByAppText", query = "SELECT a FROM ApplicationState a WHERE a.appText = :appText")})
public class ApplicationState implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 20)
    @Column(name = "APP_TEXT")
    private String appText;
    @OneToMany(mappedBy = "stateId")
    private List<Application> applicationList;

    public ApplicationState() {
    }

    public ApplicationState(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppText() {
        return appText;
    }

    public void setAppText(String appText) {
        this.appText = appText;
    }

    public List<Application> getApplicationList() {
        return applicationList;
    }

    public void setApplicationList(List<Application> applicationList) {
        this.applicationList = applicationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApplicationState)) {
            return false;
        }
        ApplicationState other = (ApplicationState) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ApplicationState[ id=" + id + " ]";
    }
    
}
