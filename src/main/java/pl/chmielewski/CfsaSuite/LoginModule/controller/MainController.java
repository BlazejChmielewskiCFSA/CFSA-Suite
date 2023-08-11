package pl.chmielewski.CfsaSuite.LoginModule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.chmielewski.CfsaSuite.LoginModule.entity.CfsaUser;
import pl.chmielewski.CfsaSuite.LoginModule.entity.Report;
import pl.chmielewski.CfsaSuite.LoginModule.service.ReportService;
import pl.chmielewski.CfsaSuite.LoginModule.service.UserService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    private UserService userService;
    private ReportService reportService;

    @Autowired
    public MainController(UserService userService, ReportService reportService) {
        this.userService = userService;
        this.reportService = reportService;
    }

    @GetMapping("/home")
    public String getHome() {
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/token-info")
    public String tokenInfo() {
        return "token-info";
    }

    @RequestMapping("/signup")
    public ModelAndView signup() {
        return new ModelAndView("registration", "user", new CfsaUser());
    }

    @RequestMapping("/register")
    public ModelAndView register(CfsaUser cfsaUser, HttpServletRequest request) throws MessagingException {
        userService.addNewUser(cfsaUser, request);
        return new ModelAndView("redirect:/token-info");
    }

    @RequestMapping("/verify-token")
    public ModelAndView register(@RequestParam String token) {
        userService.verifyToken(token);
        return new ModelAndView("redirect:/login");
    }

    //TWORZENIE NOWEGO ZGŁOSZENIA
    @GetMapping("/report-form")
    public String getReportForm(Model model) {
        Report report = new Report();
        model.addAttribute("newReportForm", report);
        return "report-form";
    }

    @PostMapping("/addReport")
    public String sendReportForm(@ModelAttribute Report report) {
        reportService.addNewReport(report);
        return "redirect:/zgloszenia";
    }

    @GetMapping("/zgloszenia")
    public String forUser(Model model) {
        model.addAttribute("username", reportService.getReportOwner());
        model.addAttribute("reports", reportService.getAllReports());
        return "zgloszenia";
    }

}
