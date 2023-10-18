package pl.chmielewski.CfsaSuite.LoginModule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.chmielewski.CfsaSuite.LoginModule.entity.CfsaUser;
import pl.chmielewski.CfsaSuite.LoginModule.entity.Report;
import pl.chmielewski.CfsaSuite.LoginModule.entity.enums.Departments;
import pl.chmielewski.CfsaSuite.LoginModule.entity.enums.Priority;
import pl.chmielewski.CfsaSuite.LoginModule.entity.enums.Status;
import pl.chmielewski.CfsaSuite.LoginModule.service.ReportService;
import pl.chmielewski.CfsaSuite.LoginModule.service.UserService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        model.addAttribute("newCfsaUser", cfsaUser);
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

    //Filtr
    @PostMapping("/addFilter")
    public String sendFilterPost(@RequestParam(name="statusValue", required = false, defaultValue = "false") Boolean statusValue,
                                 @RequestParam(name="priorityValue", required = false, defaultValue = "false") Boolean priorityValue,
                                 @RequestParam(name="departmentValue", required = false, defaultValue = "false") Boolean departmentValue,
                                 @RequestParam(name="status", required = false) Status status,
                                 @RequestParam(name="priority", required = false) Priority priority,
                                 @RequestParam(name="departments", required = false) Departments departments) {
        reportService.filtredList(statusValue, priorityValue, departmentValue, status, priority, departments);
        return "redirect:/reports";
    }

    //TWORZENIE NOWEGO ZGŁOSZENIA
    @GetMapping("/report-form")
    public String getReportFormView(Model model) {
        Report report = new Report();
        model.addAttribute("newReportForm", report);

        List<Departments> allDepartments = Arrays.asList(Departments.values()); // Lista wszystkich działów
        Map<Departments, List<CfsaUser>> departmentEmployeesMap = new HashMap<>();

        for (Departments department : allDepartments) {
            List<CfsaUser> employees = userService.getUsersByDepartment(department);
            departmentEmployeesMap.put(department, employees);
        }

        model.addAttribute("departmentEmployeesMap", departmentEmployeesMap);
        return "report-form";
    }

    @PostMapping("/addReport")
    public String sendReportFormPost(@RequestParam String header,
                                     @RequestParam String body,
                                     @RequestParam Priority priority,
                                     @RequestParam List<Long> assignedUsers) {
        List<CfsaUser> allUsersById = userService.findAllUsersById(assignedUsers);
        reportService.addNewReport(header, body, priority, allUsersById);
        return "redirect:/reports";
    }

    @GetMapping("/reports")
    public String forUser(Model model) {
        List<Report> ReportsListMadeByLoggedUser = reportService.getAllReports().stream()
                .filter(n -> n.getCreatedBy().equals(userService.getLoggedUserHisUsername()))
                .collect(Collectors.toList());
        model.addAttribute("departments", Departments.values());
        model.addAttribute("ReportsListMadeByLoggedUser", ReportsListMadeByLoggedUser);
        model.addAttribute("filtredList", reportService.getFiltredList());
        return "reports";
    }

    @GetMapping("/report/{id}")
    public String reportDetails(@PathVariable Long id, Model model) {
        Report report = reportService.getReportById(id);
        model.addAttribute("reportDetails", report);
        return "report-details";
    }

    @GetMapping("/aplications")
    public String getAplication(){
        return "aplications";
    }

    @GetMapping("/aplications/dlugi-info")
    public String getDlugiInfo(){
        return "aplications/dlugi-info";
    }
}
