package glavni.dao;

import glavni.domain.Gradovi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradoviRepository extends JpaRepository<Gradovi,Long> {
    Iterable<Gradovi>  findAllByDrzavaEquals(String drz);
    Gradovi findByIdEquals(long id);
}
