package pl.chmielewski.CfsaSuite.LoginModule.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.chmielewski.CfsaSuite.LoginModule.entity.enums.Departments;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "cfsa_users")
public class CfsaUser implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private Departments departments;
    private boolean isEnable;
    private boolean isAdmin = false;

    @OneToMany(mappedBy = "createdBy")
    private List<Report> reports = new ArrayList<>();

    @ManyToMany(mappedBy = "assignedUsers")
    private List<Report> assignedReports = new ArrayList<>();

    public CfsaUser() {
    }

    public List<Report> getAssignedReports() {
        return assignedReports;
    }

    public void setAssignedReports(List<Report> assignedReports) {
        this.assignedReports = assignedReports;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public Departments getDepartments() {
        return departments;
    }

    public void setDepartments(Departments departments) {
        this.departments = departments;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnable;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (isAdmin == false)
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
        else {
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
