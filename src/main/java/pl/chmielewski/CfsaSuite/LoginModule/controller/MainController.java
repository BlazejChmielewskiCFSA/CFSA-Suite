package pl.chmielewski.CfsaSuite.LoginModule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.chmielewski.CfsaSuite.LoginModule.entity.CfsaUser;
import pl.chmielewski.CfsaSuite.LoginModule.entity.Report;
import pl.chmielewski.CfsaSuite.LoginModule.entity.enums.Departments;
import pl.chmielewski.CfsaSuite.LoginModule.service.ReportService;
import pl.chmielewski.CfsaSuite.LoginModule.service.UserService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

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
    public String getHomeView(Model model) {
        model.addAttribute("reportsList", reportService.getAllReports());
        return "home";
    }

    @GetMapping("/login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("/token-info")
    public String tokenInfoView() {
        return "token-info";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        CfsaUser cfsaUser = new CfsaUser();
        model.addAttribute("departamentsList", Departments.values());
        model.addAttribute("newCfsaUser",cfsaUser);
        return "registration";
    }

    @PostMapping("/register")
    public String registerPost(CfsaUser cfsaUser, HttpServletRequest request) throws MessagingException {
        userService.addNewUser(cfsaUser, request);
        return "redirect:/token-info";
    }

    @GetMapping("/verify-token")
    public String verifyTokenRedirect(@RequestParam String token) {
        userService.verifyToken(token);
        return "redirect:/login";
    }


    //TWORZENIE NOWEGO ZGŁOSZENIA
    @GetMapping("/report-form")
    public String getReportFormView(Model model) {
        Report report = new Report();
        model.addAttribute("newReportForm", report);
        return "report-form";
    }

    @PostMapping("/addReport")
    public String sendReportFormPost(@ModelAttribute Report report) {
        reportService.addNewReport(report);
        return "redirect:/reports";
    }

    @GetMapping("/reports")
    public String forUser(Model model) {
        List<Report> ReportsListMadeByLoggedUser = reportService.getAllReports().stream()
                .filter(n -> n.getOwner().equals(userService.getLoggedUserHisUsername()))
                .collect(Collectors.toList());
        model.addAttribute("ReportsListMadeByLoggedUser", ReportsListMadeByLoggedUser);
        return "reports";
    }

    @GetMapping("/report/{id}")
    public String reportDetails(@PathVariable Long id, Model model){
        Report report = reportService.getReportById(id);
        model.addAttribute("report", report);
        return "report-details";
    }

}
