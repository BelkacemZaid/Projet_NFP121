package licence.projetnfp121.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "matiere")
public class Matiere {
    @Id
    @Size(max = 50)
    @Column(name = "idMat", nullable = false, length = 50)
    private String idMat;

    @Size(max = 50)
    @Column(name = "denomination", length = 50)
    private String denomination;

    @OneToMany(mappedBy = "matiere")
    private Set<Devoir> devoirs;

    public String getDenomination() {
        return denomination;
    }

    public String getIdMat() {
        return idMat;
    }

    public Set<Devoir> getDevoirs() {
        return devoirs;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public void setIdMat(String idMat) {
        this.idMat = idMat;
    }

    public void setDevoirs(Set<Devoir> devoirs) {
        this.devoirs = devoirs;
    }
}