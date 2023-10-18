package pl.chmielewski.CfsaSuite.LoginModule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.chmielewski.CfsaSuite.LoginModule.entity.CfsaUser;

import java.util.List;

@Repository
public interface CfsaUserRepo extends JpaRepository<CfsaUser, Long> {

    CfsaUser findAllByUsername(String username);
    List<String> findUsernamesByIdIn(List<Long> ids);

    CfsaUser findUserByUsername(String username);

}
