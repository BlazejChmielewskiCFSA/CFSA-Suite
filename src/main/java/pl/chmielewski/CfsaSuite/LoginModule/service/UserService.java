package pl.chmielewski.CfsaSuite.LoginModule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.chmielewski.CfsaSuite.LoginModule.entity.CfsaUser;
import pl.chmielewski.CfsaSuite.LoginModule.entity.VerificationToken;
import pl.chmielewski.CfsaSuite.LoginModule.entity.enums.Departments;
import pl.chmielewski.CfsaSuite.LoginModule.repository.CfsaUserRepo;
import pl.chmielewski.CfsaSuite.LoginModule.repository.VerificationTokenRepo;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public List<CfsaUser> findAllUsersById(List<Long> id){
        return cfsaUserRepo.findAllById(id);
    }

    public void verifyToken(String token) {
        CfsaUser cfsaUser = verificationTokenRepo.findByValue(token).getCfsaUser();
        cfsaUser.setEnable(true);
        cfsaUserRepo.save(cfsaUser);
    }

    public CfsaUser getLoggedUserHisUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return cfsaUserRepo.findUserByUsername(name);
    }

    public List<CfsaUser> getUsersByIds(List<Long> ids){
        return cfsaUserRepo.findAllById(ids);
    }

    public CfsaUser getUserByUsername(String username){
        return cfsaUserRepo.findAllByUsername(username);
    }

   public List<CfsaUser> getUsersByDepartment(Departments departments){
        return cfsaUserRepo.findAll().stream()
                .filter(n->n.getDepartments().equals(departments))
                .collect(Collectors.toList());
   }

   public List<String> getUsersNameByIds(List<Long> ids){
       return cfsaUserRepo.findUsernamesByIdIn(ids);
   }

}
