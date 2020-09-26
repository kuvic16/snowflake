package com.bracits.snowflake.security;

import com.bracits.snowflake.entity.Right;
import com.bracits.snowflake.entity.User;
import com.bracits.snowflake.security.auth.internal.adapter.AuthorizationAdapter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author Shaiful Islam Palash | kuvic16@gmail.com
 * @CreatedAt: 9/20/2020
 */
public class AuthUser {

    private long id;
    private String pin;
    private String email;
    private String password;
    private String role;
    private List<String> rights;

    public AuthUser() {
    }
    public AuthUser(long id, String pin, String email, String password, String role, List<String> rights) {
        this.setId(id);
        this.setPin(pin);
        this.setEmail(email);
        this.setRole(role);
        this.setPassword(password);
        this.setRights(rights);
    }

    public AuthUser(User user) {
        this.setId(user.getId());
        this.setPin(user.getPin());
        this.setEmail(user.getEmail());
        this.setRole(user.getRole().getName());
        this.setPassword("");
        List<String> rights = new ArrayList<>();
        for(Right right : user.getRole().getRights()) {
            rights.add(right.getName());
        }
        this.setRights(rights);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRights() {
        return rights;
    }

    public void setRights(List<String> rights) {
        this.rights = rights;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
        // set role name
        if(StringUtils.isNotEmpty(this.getRole())) {
            authorityList.add(new AuthorizationAdapter(this.getRole()));
        }

        // set right name
        for(String right : this.getRights()) {
            authorityList.add(new AuthorizationAdapter(right));
        }
        return authorityList;
    }
}
