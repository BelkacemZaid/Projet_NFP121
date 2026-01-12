package licence.projetnfp121.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "idEtud", nullable = false)
    private Etudiant idEtud;


}