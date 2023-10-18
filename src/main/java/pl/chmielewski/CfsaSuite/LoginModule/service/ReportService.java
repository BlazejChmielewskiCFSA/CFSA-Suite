package pl.chmielewski.CfsaSuite.LoginModule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.chmielewski.CfsaSuite.LoginModule.entity.CfsaUser;
import pl.chmielewski.CfsaSuite.LoginModule.entity.Report;
import pl.chmielewski.CfsaSuite.LoginModule.entity.enums.Departments;
import pl.chmielewski.CfsaSuite.LoginModule.entity.enums.Priority;
import pl.chmielewski.CfsaSuite.LoginModule.entity.enums.Status;
import pl.chmielewski.CfsaSuite.LoginModule.repository.ReportRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private ReportRepo reportRepo;
    private UserService userService;
    private List<Report> reportList = new ArrayList<>();
    private List<Report> reportListFiltered = new ArrayList<>();

    @Autowired
    public ReportService(ReportRepo reportRepo, UserService userService) {
        this.reportRepo = reportRepo;
        this.userService = userService;
    }

    public List<Report> getAllReports() {
        return reportRepo.findAll();
    }

    public Report getReportById(Long id) {
        return reportRepo.findAllById(id);
    }

    public void addNewReport(String header, String body, Priority priority,List<CfsaUser> userList) {
        Report report = new Report(header, body, priority, userList);
        report.setHeader(header.substring(0, 1).toUpperCase() + header.substring(1));
        report.setBody(body);
        report.setPriority(priority);
        report.setStatus(Status.Oczekujacy);
        report.setCreatedBy(userService.getLoggedUserHisUsername());
        report.setAssignedUsers(userList);
        reportRepo.save(report);
    }

    public List<Report> filtredList(Boolean statusValue,
                                    Boolean priorityValue,
                                    Boolean departmentValue,
                                    Status status,
                                    Priority priority,
                                    Departments departments) {
        reportListFiltered = reportRepo.findAll();
        reportListFiltered = filterStatus(reportListFiltered, statusValue, status);
        reportListFiltered = filterPriority(reportListFiltered, priorityValue, priority);
        reportListFiltered = filterDepartment(reportListFiltered, departmentValue, departments);
        return reportListFiltered;
    }

    public List<Report> getFiltredList() {
        return reportListFiltered;
    }

    public List<Report> filterStatus(List<Report> list, Boolean statusValue, Status status) {
        if (statusValue) {
            return list.stream()
                    .filter(n -> n.getStatus().equals(status))
                    .collect(Collectors.toList());
        } else return list;
    }

    public List<Report> filterPriority(List<Report> list, Boolean priorityValue, Priority priority) {
        if (priorityValue) {
            return list.stream()
                    .filter(n -> n.getPriority().equals(priority))
                    .collect(Collectors.toList());
        } else return list;
    }

    public List<Report> filterDepartment(List<Report> list, Boolean departmentValue, Departments departments) {
        if (departmentValue) {
            return list.stream()
                    .filter(n -> n.getCreatedBy().getDepartments().equals(departments))
                    .collect(Collectors.toList());
        } else return list;
    }
}
