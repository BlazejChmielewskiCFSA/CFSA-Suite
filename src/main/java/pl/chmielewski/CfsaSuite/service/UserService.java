package pl.chmielewski.CfsaSuite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.chmielewski.CfsaSuite.entity.CfsaUser;
import pl.chmielewski.CfsaSuite.entity.VerificationToken;
import pl.chmielewski.CfsaSuite.repository.CfsaUserRepo;
import pl.chmielewski.CfsaSuite.repository.VerificationTokenRepo;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class UserService {

    private CfsaUserRepo cfsaUserRepo;
    private PasswordEncoder passwordEncoder;
    private VerificationTokenRepo verificationTokenRepo;
    private MailSenderService mailSenderService;

    @Autowired
    public UserService(CfsaUserRepo cfsaUserRepo, PasswordEncoder passwordEncoder, VerificationTokenRepo verificationTokenRepo, MailSenderService mailSenderService) {
        this.cfsaUserRepo = cfsaUserRepo;
        this.passwordEncoder = passwordEncoder;
        this.verificationTokenRepo = verificationTokenRepo;
        this.mailSenderService = mailSenderService;
    }

    public void addNewUser(CfsaUser cfsaUser, HttpServletRequest request) throws MessagingException {
        cfsaUser.setPassword(passwordEncoder.encode(cfsaUser.getPassword()));
        cfsaUserRepo.save(cfsaUser);

        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(cfsaUser, token);
        verificationTokenRepo.save(verificationToken);

        String url = "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath() +
                "verify-token?token=" + token;

        mailSenderService.sendMail(cfsaUser.getUsername(), "Verification Token", url, false);
    }
}
