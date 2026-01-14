package licence.projetnfp121.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idClasse", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "denomination", length = 50)
    private String denomination;

}