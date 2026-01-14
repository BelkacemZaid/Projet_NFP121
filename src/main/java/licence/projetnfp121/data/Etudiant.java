package licence.projetnfp121.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
<<<<<<< Updated upstream
=======
@Table(name = "Etudiant")
>>>>>>> Stashed changes
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEtud", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "nom", length = 50, nullable = true)
    private String nom;

    @Size(max = 50)
    @Column(name = "prenom", length = 50, nullable = true)
    private String prenom;

<<<<<<< Updated upstream
    @Size(max = 50)
    @Column(name = "photo", length = 50, nullable = true)
    private String photo;
=======
    @Column(name = "photo")
    private Boolean photo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idClasse", nullable = false)
    private Classe idClasse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classe")
    private Classe classe;
>>>>>>> Stashed changes

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idClasse" , nullable = false)
    @JsonProperty("classe") // je l'utilise pcq sur swagger il veux une classe alors que dans ma base de données c'est idClasse et pas classe
    private Classe idClasse;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Classe getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Classe idClasse) {
        this.idClasse = idClasse;
    }
}