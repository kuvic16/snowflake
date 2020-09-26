package com.bracits.snowflake.security.auth.internal.adapter;

import org.springframework.security.core.GrantedAuthority;

/**
 * @Author Shaiful Islam Palash | kuvic16@gmail.com
 * @CreatedAt: 9/20/2020
 */
public class AuthorizationAdapter implements GrantedAuthority{

	private String code;
	
	public AuthorizationAdapter(String code) {
		this.code = code;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.GrantedAuthority#getAuthority()
	 */
	@Override
	public String getAuthority() {
		return code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
