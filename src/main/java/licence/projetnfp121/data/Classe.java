package licence.projetnfp121.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

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


}