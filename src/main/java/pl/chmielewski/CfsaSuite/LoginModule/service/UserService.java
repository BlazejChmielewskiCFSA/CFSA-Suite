package pl.chmielewski.CfsaSuite.LoginModule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.chmielewski.CfsaSuite.LoginModule.entity.CfsaUser;
import pl.chmielewski.CfsaSuite.LoginModule.entity.VerificationToken;
import pl.chmielewski.CfsaSuite.LoginModule.repository.CfsaUserRepo;
import pl.chmielewski.CfsaSuite.LoginModule.repository.VerificationTokenRepo;

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
                request.getServerName() + ":" +
                request.getServerPort() +
                request.getContextPath() +
                "/verify-token?token=" + token;

        mailSenderService.sendMail(cfsaUser.getUsername(), "Verification Token", url, false);
    }

    public void verifyToken(String token) {
        CfsaUser cfsaUser = verificationTokenRepo.findByValue(token).getCfsaUser();
        cfsaUser.setEnable(true);
        cfsaUserRepo.save(cfsaUser);
    }

    public String getLoggedUserHisUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public CfsaUser getUserByUsername(String username){
        return cfsaUserRepo.findAllByUsername(username);
    }

}
