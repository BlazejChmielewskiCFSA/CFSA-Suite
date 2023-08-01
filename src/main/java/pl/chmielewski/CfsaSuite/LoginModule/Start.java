package pl.chmielewski.CfsaSuite.LoginModule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.chmielewski.CfsaSuite.LoginModule.entity.CfsaUser;
import pl.chmielewski.CfsaSuite.LoginModule.repository.CfsaUserRepo;

@Component
public class Start {

    private PasswordEncoder passwordEncoder;
    private CfsaUserRepo cfsaUserRepo;

    @Autowired
    public Start(PasswordEncoder passwordEncoder, CfsaUserRepo cfsaUserRepo) {
        this.passwordEncoder = passwordEncoder;
        this.cfsaUserRepo = cfsaUserRepo;

        CfsaUser cfsaUser = new CfsaUser();
        cfsaUser.setUsername("Przemek");
        cfsaUser.setPassword(passwordEncoder.encode("Przemek123"));
        cfsaUser.setEnable(true);
        cfsaUserRepo.save(cfsaUser);
    }
}
