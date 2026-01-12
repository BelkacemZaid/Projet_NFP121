package licence.projetnfp121.repository;

import licence.projetnfp121.data.Classe;
import licence.projetnfp121.data.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    List<Etudiant> getEtudiantsByClasse_Id(Long classeId);
    
    List<Etudiant> getEtudiantsByClasse(Classe classe);
}
