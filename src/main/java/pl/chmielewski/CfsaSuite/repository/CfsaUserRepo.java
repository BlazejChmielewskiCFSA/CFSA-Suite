package pl.chmielewski.CfsaSuite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.chmielewski.CfsaSuite.entity.CfsaUser;

public interface CfsaUserRepo extends JpaRepository<CfsaUser, Long> {

}
