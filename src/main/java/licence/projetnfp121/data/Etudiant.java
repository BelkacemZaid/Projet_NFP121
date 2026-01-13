package licence.projetnfp121.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Data
public class Etudiant {
    @Id
    @Column(name = "idEtud", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "nom", length = 50)
    private String nom;

    @Size(max = 50)
    @Column(name = "prenom", length = 50)
    private String prenom;

    @Column(name = "photo")
    private Boolean photo;

    @ManyToOne(fetch = FetchType.EAGER)
    private Classe classe;


}