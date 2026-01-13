package licence.projetnfp121.repository;

import licence.projetnfp121.data.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {

    @Override
    <S extends Classe> S save(S entity);

    @Override
    void delete(Classe entity);



}
