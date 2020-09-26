package com.bracits.snowflake.security.auth.external;

/**
 * @Author Shaiful Islam Palash | kuvic16@gmail.com
 * @CreatedAt: 9/20/2020
 */
public interface Transform {
    public String encrypt(String payload, String key);
    public String decrypt(String multipass, String key);
}
