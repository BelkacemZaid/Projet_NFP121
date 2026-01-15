package licence.projetnfp121.repository;

import licence.projetnfp121.data.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere,Long> {

    @Override
    <S extends Matiere> S save(S entity);

    @Override
    void delete(Matiere entity);


}

