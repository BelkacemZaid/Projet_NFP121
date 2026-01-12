package licence.projetnfp121.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "devoir")
public class Devoir {
    @Id
    @Column(name = "idDev", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "description", length = 50)
    private String description;

    @Size(max = 50)
    @Column(name = "categorie", length = 50)
    private String categorie;

    @Column(name = "date_crea")
    private LocalDate dateCrea;

    @Column(name = "coef", precision = 15, scale = 2)
    private BigDecimal coef;

    @NotNull
    @Column(name = "idClasse", nullable = false)
    private Integer idClasse;

//    @Size(max = 50)
//    @NotNull
//    @Column(name = "idMat", nullable = false, length = 50)
//    private String idMat;

    @ManyToOne
    @JoinColumn(name="idMat")
    private Matiere matieres;

    @ManyToOne
    @JoinColumn(name="idClasse")
    private Classe classes;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public LocalDate getDateCrea() {
        return dateCrea;
    }

    public void setDateCrea(LocalDate dateCrea) {
        this.dateCrea = dateCrea;
    }

    public BigDecimal getCoef() {
        return coef;
    }

    public void setCoef(BigDecimal coef) {
        this.coef = coef;
    }

    public Matiere getMatieres() {
        return matieres;
    }

    public void setMatieres(Matiere matieres) {
        this.matieres = matieres;
    }

    public Integer getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Integer idClasse) {
        this.idClasse = idClasse;
    }

    public Classe getClasses() {
        return classes;
    }

    public void setClasses(Classe classes) {
        this.classes = classes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}