package licence.projetnfp121.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "matiere")
public class Matiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMat", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "denomination", length = 50)
    private String denomination;

}