package licence.projetnfp121.data;

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
    @Column(name = "nom", length = 50)
    private String nom;

    @Size(max = 50)
    @Column(name = "prenom", length = 50)
    private String prenom;

<<<<<<< Updated upstream
    @Size(max = 50)
    @Column(name = "photo", length = 50)
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
    @JoinColumn(name = "idClasse", nullable = false)
    private Classe idClasse;

}