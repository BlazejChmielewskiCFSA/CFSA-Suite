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
        CfsaUser cfsaUser1 = new CfsaUser();
        cfsaUser1.setUsername("blazej.chmielewski@cfsa.pl");
        cfsaUser1.setPassword(passwordEncoder.encode("blazej.chmielewski@cfsa.pl"));
        cfsaUser1.setEnable(true);
        cfsaUser1.setAdmin(true);
        cfsaUserRepo.save(cfsaUser1);

        CfsaUser cfsaUser2 = new CfsaUser();
        cfsaUser2.setUsername("arkadiusz.drezek@cfsa.pl");
        cfsaUser2.setPassword(passwordEncoder.encode("arkadiusz.drezek@cfsa.pl"));
        cfsaUser2.setEnable(true);
        cfsaUser2.setAdmin(true);
        cfsaUserRepo.save(cfsaUser2);

        CfsaUser cfsaUser3 = new CfsaUser();
        cfsaUser3.setUsername("ilona.stronkowska@cfsa.pl");
        cfsaUser3.setPassword(passwordEncoder.encode("ilona.stronkowska@cfsa.pl"));
        cfsaUser3.setEnable(true);
        cfsaUser3.setAdmin(true);
        cfsaUserRepo.save(cfsaUser3);
    }
}
