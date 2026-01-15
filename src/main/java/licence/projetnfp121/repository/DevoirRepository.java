package licence.projetnfp121.repository;

import licence.projetnfp121.data.Devoir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DevoirRepository extends JpaRepository<Devoir,Long> {

    @Override
    void delete(Devoir entity);

    List<Devoir> findByEtudiant_Id(Integer id);

    @Override
    List<Devoir> findAll();
}
