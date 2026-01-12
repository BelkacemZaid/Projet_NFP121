package licence.projetnfp121.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "classe")
public class Classe {
    @Id
    @Column(name = "idClasse", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "denomination", length = 50)
    private String denomination;

    @NotNull
    @Column(name = "idEtud", nullable = false)
    private Integer idEtud;

    @OneToMany(mappedBy="classe") // la classe est composé d'UN ou PLUSIEURS etudiant c'est la clase Etudiant qui contient la cle etrangere
    private Set<Etudiant> etudiants; //liste d'Etudiant sans doublons

    @OneToMany(mappedBy="classe") // la classe est composé d'UN ou PLUSIEURS etudiant c'est la clase Devoir qui contient la cle etrangere
    private Set<Devoir> devoirs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public Integer getIdEtud() {
        return idEtud;
    }

    public void setIdEtud(Integer idEtud) {
        this.idEtud = idEtud;
    }

    public Set<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(Set<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public Set<Devoir> getDevoirs() {
        return devoirs;
    }

    public void setDevoirs(Set<Devoir> devoirs) {
        this.devoirs = devoirs;
    }
}