package licence.projetnfp121.repository;

import licence.projetnfp121.data.Classe;
import licence.projetnfp121.data.Devoir;
import licence.projetnfp121.data.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    List<Etudiant> findByIdClasse(Classe idClasse);

    List<Devoir> findById(Integer id);



    @Override
    <S extends Etudiant> S save(S entity);

    void deleteEtudiantById(Integer id);
}
