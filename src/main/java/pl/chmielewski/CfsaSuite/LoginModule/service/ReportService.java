package pl.chmielewski.CfsaSuite.LoginModule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.chmielewski.CfsaSuite.LoginModule.entity.CfsaUser;
import pl.chmielewski.CfsaSuite.LoginModule.entity.Report;
import pl.chmielewski.CfsaSuite.LoginModule.entity.enums.Status;
import pl.chmielewski.CfsaSuite.LoginModule.repository.CfsaUserRepo;
import pl.chmielewski.CfsaSuite.LoginModule.repository.ReportRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

    public Report getReportById(Long id){
        return reportRepo.findAllById(id);
    }

    public void addNewReport(Report newReport) {
        newReport.setOwner(userService.getUserName());
        newReport.setStatus(Status.Oczekujacy);
        String header = newReport.getHeader().substring(0, 1).toUpperCase() + newReport.getHeader().substring(1);
        newReport.setHeader(header);
        reportList.add(newReport);
        reportRepo.save(newReport);
    }
}
