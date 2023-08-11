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

        //Template tworzenia nowego admina
        CfsaUser cfsaUser = new CfsaUser();
        cfsaUser.setUsername("blazej");
        cfsaUser.setPassword(passwordEncoder.encode("blazej"));
        cfsaUser.setEnable(true);
        cfsaUser.setAdmin(true);
        cfsaUserRepo.save(cfsaUser);
    }
}
