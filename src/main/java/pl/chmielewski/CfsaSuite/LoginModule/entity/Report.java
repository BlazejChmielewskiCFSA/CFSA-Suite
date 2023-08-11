package pl.chmielewski.CfsaSuite.LoginModule.entity;

import pl.chmielewski.CfsaSuite.LoginModule.entity.enums.Priority;

import javax.persistence.*;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String header;
    private String body;
    private String owner;
    private Priority priority;

    public Report() {
    }

    public Report(String header, String body, String owner, Priority priority) {
        this.header = header;
        this.body = body;
        this.owner = owner;
        this.priority = priority;
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


}

