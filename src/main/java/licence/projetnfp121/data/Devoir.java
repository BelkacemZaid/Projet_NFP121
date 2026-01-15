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
public class Devoir {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDev", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "description", length = 50,nullable = true)
    private String description;

    @Size(max = 50)
    @Column(name = "categorie", length = 50,nullable = true)
    private String categorie;

    @Column(name = "date_crea",nullable = true)
    private LocalDate dateCrea;

    @Column(name = "coef", precision = 15, scale = 2,nullable = true)
    private BigDecimal coef;

    @Column(name = "note", precision = 15, scale = 2,nullable = true)
    private BigDecimal note;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idClasse", nullable = false)
    private Classe idClasse;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idMat", nullable = false)
    private Matiere idMat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idEtudiant")
    private Etudiant etudiant;


}