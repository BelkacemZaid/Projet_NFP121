package licence.projetnfp121.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    @Size(max = 50)
    @NotNull
    @Column(name = "idMat", nullable = false, length = 50)
    private String idMat;


}