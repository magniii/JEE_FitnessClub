package entities;

import java.io.Serializable;
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

/**
 *
 * @author magniii
 */
@Entity
@Table(name = "COACHCLIENTS")
@NamedQueries({
    @NamedQuery(name = "Coachclients.findAll", query = "SELECT c FROM Coachclients c"),
    @NamedQuery(name = "Coachclients.findById", query = "SELECT c FROM Coachclients c WHERE c.id = :id"),
    @NamedQuery(name = "Coachclients.findCoachesClients", query = "SELECT c.clientId FROM Coachclients c WHERE c.coachId.id = :coachId"),
    @NamedQuery(name = "Coachclients.findCoachByClient", query = "SELECT c.coachId FROM Coachclients c WHERE c.clientId.id = :clientId")})
public class Coachclients implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_coachclients")
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Client clientId;
    @JoinColumn(name = "COACH_ID", referencedColumnName = "ID")
    @ManyToOne
    private Coach coachId;

    public Coachclients() {
    }

    public Coachclients(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public Coach getCoachId() {
        return coachId;
    }

    public void setCoachId(Coach coachId) {
        this.coachId = coachId;
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
        if (!(object instanceof Coachclients)) {
            return false;
        }
        Coachclients other = (Coachclients) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Coachclients[ id=" + id + " ]";
    }
    
}
