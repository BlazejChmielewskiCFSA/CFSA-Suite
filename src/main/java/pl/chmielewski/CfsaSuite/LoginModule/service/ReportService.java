package pl.chmielewski.CfsaSuite.LoginModule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.chmielewski.CfsaSuite.LoginModule.entity.Report;
import pl.chmielewski.CfsaSuite.LoginModule.entity.enums.Status;
import pl.chmielewski.CfsaSuite.LoginModule.repository.ReportRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    private ReportRepo reportRepo;
    private UserService userService;
    private List<Report> reportList = new ArrayList<>();

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

    public void addNewReport(Report newReport) {
        String header = newReport.getHeader().substring(0, 1).toUpperCase() + newReport.getHeader().substring(1);
        newReport.setOwner(userService.getLoggedUserHisUsername());
        newReport.setHeader(header);
        newReport.setStatus(Status.Oczekujacy);
        newReport.setCfsaUser(userService.getUserByUsername(userService.getLoggedUserHisUsername()));
        reportList.add(newReport);
        reportRepo.save(newReport);
    }
}
