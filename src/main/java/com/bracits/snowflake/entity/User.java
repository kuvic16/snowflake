package com.bracits.snowflake.entity;

import com.bracits.snowflake.entity.common.CommonColumn;

import javax.persistence.*;

/**
 * @Author Shaiful Islam Palash | kuvic16@gmail.com
 * @CreatedAt: 9/24/2020
 */
@Entity
@Table(name = "users")
public class User extends CommonColumn{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @Column(name = "pin", length = 8, nullable = false, unique=true)
    private String pin;

    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @Column(name = "project_id", length = 3)
    private String projectId;

    @Column(name = "email", length = 150)
    private String email;

    @Column(name = "last_visited_page", length = 50)
    private String lastVisitedPage;

    @Column(name = "active")
    private boolean active;

    public User() {

    }

    public User(String pin, String name, String projectId, String email, String lastVisitedPage,
                boolean active, Profile profile, Role role) {
        this.pin = pin;
        this.name = name;
        this.projectId = projectId;
        this.email = email;
        this.lastVisitedPage = lastVisitedPage;
        this.active = active;
        this.profile = profile;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastVisitedPage() {
        return lastVisitedPage;
    }

    public void setLastVisitedPage(String lastVisitedPage) {
        this.lastVisitedPage = lastVisitedPage;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User [id=" + id
                + ", pin=" + pin
                + ", name=" + name
                + ", email=" + email
                + "]";
    }
}
