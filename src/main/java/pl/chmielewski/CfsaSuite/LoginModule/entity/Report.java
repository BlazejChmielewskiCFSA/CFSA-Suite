package pl.chmielewski.CfsaSuite.LoginModule.entity;

import pl.chmielewski.CfsaSuite.LoginModule.entity.enums.Priority;
import pl.chmielewski.CfsaSuite.LoginModule.entity.enums.Status;

import javax.persistence.*;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String header;
    private String body;
    private String owner;
    private Priority priority;
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cfsauser_id")
    private CfsaUser cfsaUser;

    public Report() {
    }

    public Report(Long id, String header, String body, String owner, Priority priority, Status status, CfsaUser cfsaUser) {
        this.id = id;
        this.header = header;
        this.body = body;
        this.owner = owner;
        this.priority = priority;
        this.status = status;
        this.cfsaUser = cfsaUser;
    }

    public CfsaUser getCfsaUser() {
        return cfsaUser;
    }

    public void setCfsaUser(CfsaUser cfsaUser) {
        this.cfsaUser = cfsaUser;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

