package pl.chmielewski.CfsaSuite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.chmielewski.CfsaSuite.entity.CfsaUser;
import pl.chmielewski.CfsaSuite.repository.CfsaUserRepo;

@Component
public class Start {

    private BCryptPasswordEncoder passwordEncoder;
    private CfsaUserRepo cfsaUserRepo;

    @Autowired
    public Start(BCryptPasswordEncoder passwordEncoder, CfsaUserRepo cfsaUserRepo) {
        this.passwordEncoder = passwordEncoder;
        this.cfsaUserRepo = cfsaUserRepo;

        CfsaUser cfsaUser = new CfsaUser();
        cfsaUser.setUsername("Przemek");
        cfsaUser.setPassword(passwordEncoder.encode("Przemek123"));
        cfsaUserRepo.save(cfsaUser);
    }
}
