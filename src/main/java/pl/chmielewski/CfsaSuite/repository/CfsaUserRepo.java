package pl.chmielewski.CfsaSuite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.chmielewski.CfsaSuite.entity.CfsaUser;

@Repository
public interface CfsaUserRepo extends JpaRepository<CfsaUser, Long> {

    CfsaUser findAllByUsername(String username);
}
