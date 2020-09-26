package com.bracits.snowflake.service.user;

import com.bracits.snowflake.entity.User;
import com.bracits.snowflake.security.auth.external.SSOUser;
import com.bracits.snowflake.service.AbstractService;

/**
 * @Author Shaiful Islam Palash | kuvic16@gmail.com
 * @CreatedAt: 9/20/2020
 */
public interface UserService extends AbstractService<User> {
    public User findByPin(String pin);
    public User findByEmail(String email);
    public User findByUsername(String username);
    public User createSSOUser(SSOUser ssoUser);
}
