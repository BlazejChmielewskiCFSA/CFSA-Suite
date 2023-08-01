package pl.chmielewski.CfsaSuite.LoginModule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.chmielewski.CfsaSuite.LoginModule.entity.CfsaUser;

@Repository
public interface CfsaUserRepo extends JpaRepository<CfsaUser, Long> {

    CfsaUser findAllByUsername(String username);
}
