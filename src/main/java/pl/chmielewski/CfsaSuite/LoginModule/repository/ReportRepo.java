package pl.chmielewski.CfsaSuite.LoginModule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.chmielewski.CfsaSuite.LoginModule.entity.Report;

@Repository
public interface ReportRepo extends JpaRepository<Report, Long> {
    Report findAllById(Long id);

}
