package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author magniii
 */
@Entity
@Table(name = "APPLICATION")
@NamedQueries({
    @NamedQuery(name = "Application.findAll", query = "SELECT a FROM Application a"),
    @NamedQuery(name = "Application.findById", query = "SELECT a FROM Application a WHERE a.id = :id"),
    @NamedQuery(name = "Application.findByCreationDate", query = "SELECT a FROM Application a WHERE a.creationDate = :creationDate"),
    @NamedQuery(name = "Application.findByText", query = "SELECT a FROM Application a WHERE a.text = :text"),
    @NamedQuery(name = "Application.findByClientId", query = "SELECT a FROM Application a WHERE a.clientId = :clientId")})
public class Application implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_app")
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Size(max = 256)
    @Column(name = "TEXT")
    private String text;
    @JoinColumn(name = "STATE_ID", referencedColumnName = "ID")
    @ManyToOne
    private ApplicationState stateId;
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Client clientId;

    public Application() {
    }

    public Application(Integer id) {
        this.id = id;
    }

    public Application(Client clientId) {
        this.stateId = new ApplicationState(1);
        this.clientId = clientId;
        this.creationDate = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ApplicationState getStateId() {
        return stateId;
    }

    public void setStateId(ApplicationState stateId) {
        this.stateId = stateId;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
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
        if (!(object instanceof Application)) {
            return false;
        }
        Application other = (Application) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Application[ id=" + id + " ]";
    }
    
}
