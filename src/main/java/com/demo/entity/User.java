package com.demo.entity;
import javax.persistence.*;
import java.util.Set;

/**
 * Created by Queric on 2016/7/14.
 */
@Entity
public class User {
    private int userId;
    private String username;
    private String password;
    private Set<Role> roles;
    private Set<Department> departs;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany(targetEntity = Role.class)
    @JoinTable(name = "userRolePk", joinColumns = {@JoinColumn(name = "userId")},inverseJoinColumns = {@JoinColumn(name = "roleId")})
    @OrderBy("roleId")
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @ManyToMany
    @JoinTable(name = "userDepartmentPk", joinColumns = {@JoinColumn(name = "userId")}, inverseJoinColumns = {@JoinColumn(name = "deptId")})
    public Set<Department> getDeparts() {
        return departs;
    }

    public void setDeparts(Set<Department> departs) {
        this.departs = departs;
    }
}
