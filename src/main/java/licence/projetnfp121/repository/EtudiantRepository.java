package licence.projetnfp121.repository;

import licence.projetnfp121.data.Classe;
import licence.projetnfp121.data.Devoir;
import licence.projetnfp121.data.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    List<Etudiant> findByIdClasse(Classe idClasse);

    List<Etudiant> findAllById(Integer id);

//    List<Etudiant> findAll();



//    @Override
//    <S extends Etudiant> S save(S entity);

    void deleteEtudiantById(Integer id);

//    List<Etudiant> id(Integer id);

    List<Etudiant> findByNom(String nom);

    Optional<Etudiant> findById(Integer id);


    List<Etudiant> findByIdClasseNull();

    List<Etudiant> getEtudiantsByIdClasse(Classe idClasse);

    List<Etudiant> findByIdClasse_Id(Integer idClasse);


}
