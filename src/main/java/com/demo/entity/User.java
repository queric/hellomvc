package com.demo.entity;
import javax.persistence.*;
import java.util.Set;

/**
 * Created by Queric on 2016/7/14.
 */
@Entity
public class User {
    private int id;
    private String username;
    private String password;
    private Set<Role> roles;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    @OneToMany(targetEntity = User.class)
    @JoinColumn(name = "")
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
