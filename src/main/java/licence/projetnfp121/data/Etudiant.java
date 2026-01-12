package licence.projetnfp121.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "etudiant")
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


}