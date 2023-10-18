package pl.chmielewski.CfsaSuite.LoginModule.entity;

import pl.chmielewski.CfsaSuite.LoginModule.entity.enums.Priority;
import pl.chmielewski.CfsaSuite.LoginModule.entity.enums.Status;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String header;
    private String body;
    private Priority priority;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "created_by_user_id")
    private CfsaUser createdBy;

    @ManyToMany
    @JoinTable(
            name = "Report_Users",
            joinColumns = @JoinColumn(name = "report_id"),
            inverseJoinColumns = @JoinColumn(name = "cfsa_user_id")
    )
    private List<CfsaUser> assignedUsers = new ArrayList<>();


    public Report() {
    }

    public Report(String header, String body, Priority priority, List<CfsaUser> assignedUsers) {
        this.header = header;
        this.body = body;
        this.priority = priority;
        this.assignedUsers = assignedUsers;
    }

    public CfsaUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(CfsaUser createdBy) {
        this.createdBy = createdBy;
    }

    public List<CfsaUser> getAssignedUsers() {
        return assignedUsers;
    }

    public void setAssignedUsers(List<CfsaUser> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

