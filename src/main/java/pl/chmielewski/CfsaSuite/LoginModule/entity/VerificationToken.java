package pl.chmielewski.CfsaSuite.LoginModule.entity;

import javax.persistence.*;

@Entity
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;
    @OneToOne
    private CfsaUser cfsaUser;

    public VerificationToken(CfsaUser cfsaUser, String value) {
        this.value = value;
        this.cfsaUser = cfsaUser;
    }

    public VerificationToken() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CfsaUser getCfsaUser() {
        return cfsaUser;
    }

    public void setCfsaUser(CfsaUser cfsaUser) {
        this.cfsaUser = cfsaUser;
    }
}
