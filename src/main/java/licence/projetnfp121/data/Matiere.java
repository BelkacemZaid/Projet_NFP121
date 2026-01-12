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
@Table(name = "matiere")
public class Matiere {
    @Id
    @Size(max = 50)
    @Column(name = "idMat", nullable = false, length = 50)
    private String idMat;

    @Size(max = 50)
    @Column(name = "denomination", length = 50)
    private String denomination;


}