package licence.projetnfp121.repository;

import licence.projetnfp121.data.Devoir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevoirRepository extends JpaRepository<Devoir,Long> {

    @Override
    <S extends Devoir> S save(S entity);

    @Override
    void delete(Devoir entity);
}
