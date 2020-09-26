package com.bracits.snowflake.security;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @File SpringSecurityUtil.java
 * @author shaiful islam palash | kuvic16@gmail.com
 */
public class SpringSecurityUtil {
	
	/**
	 * Get current logged in user model
	 * @return UserModel
	 */
	public static AuthUser getLoggedUser() {
		AuthUser uModel = null;
		try {
			if(SecurityContextHolder.getContext() != null && 
			   SecurityContextHolder.getContext().getAuthentication() != null && 
			   SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null &&
			   SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof AuthUser){
				uModel = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return uModel;
	}

	/**
	 * Get current logged in user id
	 * @return String
	 */
    public static long getLoggedUserId() {
        long userId = 0;
        try {
			AuthUser uModel = getLoggedUser();
			userId = uModel != null ? uModel.getId() : 0;
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return userId;
    }
    
    public static boolean isAuthenticated() {
    	try {
    		return getLoggedUser() != null;
		} catch (Throwable t) {
			t.printStackTrace();
		}
        return false;
    }
}
