package pl.chmielewski.CfsaSuite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.chmielewski.CfsaSuite.entity.CfsaUser;
import pl.chmielewski.CfsaSuite.service.UserService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    private UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/signup")
    public ModelAndView signup() {
        return new ModelAndView("registration", "user", new CfsaUser());
    }

    @RequestMapping("/register")
    public ModelAndView register(CfsaUser cfsaUser, HttpServletRequest request) throws MessagingException {
        userService.addNewUser(cfsaUser, request);
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping("/verify-token")
    public String register(@RequestParam String token) {
        return "";
    }
}