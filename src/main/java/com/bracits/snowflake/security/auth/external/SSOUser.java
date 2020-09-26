package com.bracits.snowflake.security.auth.external;

import com.bracits.snowflake.entity.Right;
import com.bracits.snowflake.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Shaiful Islam Palash | kuvic16@gmail.com
 * @CreatedAt: 9/25/2020
 */
public class SSOUser {

    private String pin;
    private String password;
    private String fullname;
    private boolean authenticated;
    private boolean authorization;
    private boolean login;
    private String platform;
    private String project;
    private String designation;
    private String joblevel;
    private String mobile;
    private String request;
    private String ref;

    public SSOUser() {
    }

    public SSOUser(String payload) {
        for(String prop : payload.split("\\|")) {
            String[] propvalue = prop.split(":");
            if(propvalue.length == 2) {
                String key = propvalue[0];
                String value = propvalue[1];

                if(key.equalsIgnoreCase("name")) {
                    this.setPin(value);
                } else if(key.equalsIgnoreCase("fullname")) {
                    this.setFullname(value);
                } else if(key.equalsIgnoreCase("authenticated")) {
                    this.setAuthenticated(Boolean.parseBoolean(value));
                } else if(key.equalsIgnoreCase("authorization")) {
                    this.setAuthorization(Boolean.parseBoolean(value));
                } else if(key.equalsIgnoreCase("login")) {
                    this.setLogin(Boolean.parseBoolean(value));
                } else if(key.equalsIgnoreCase("platform")) {
                    this.setPlatform(value);
                } else if(key.equalsIgnoreCase("project")) {
                    this.setProject(value);
                } else if(key.equalsIgnoreCase("designation")) {
                    this.setDesignation(value);
                } else if(key.equalsIgnoreCase("joblevel")) {
                    this.setJoblevel(value);
                } else if(key.equalsIgnoreCase("mobile")) {
                    this.setMobile(value);
                } else if(key.equalsIgnoreCase("request")) {
                    this.setRequest(value);
                } else if(key.equalsIgnoreCase("ref")) {
                    this.setRef(value);
                }
            }
        }
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public boolean isAuthorization() {
        return authorization;
    }

    public void setAuthorization(boolean authorization) {
        this.authorization = authorization;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getJoblevel() {
        return joblevel;
    }

    public void setJoblevel(String joblevel) {
        this.joblevel = joblevel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
