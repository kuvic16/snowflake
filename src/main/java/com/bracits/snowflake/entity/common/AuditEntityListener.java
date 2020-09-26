package com.bracits.snowflake.entity.common;

import com.bracits.snowflake.security.SpringSecurityUtil;
import com.bracits.snowflake.util.AppDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * @Author Shaiful Islam Palash | kuvic16@gmail.com
 * @CreatedAt: 9/24/2020
 */
public class AuditEntityListener {
	private static final Logger log = (Logger) LoggerFactory.getLogger(AuditEntityListener.class);

	@PrePersist
	public void prePersist(CommonColumn e) {
		try {
			if (e.getCreatedBy() == 0) {
				e.setCreatedBy(SpringSecurityUtil.getLoggedUserId());
			}
			if (e.getCreatedAt() == null) {
				e.setCreatedAt(AppDate.now());
			}
		} catch (Exception ex) {
			log.error("Exception in Audit Entity Listener", ex);
		}
	}

	
	@PreUpdate
	public void preUpdate(CommonColumn e) {
		try {
			if (e.getUpdatedBy() == 0) {
				e.setUpdatedBy(SpringSecurityUtil.getLoggedUserId());
			}
			if (e.getUpdatedAt() == null) {
				e.setUpdatedAt(AppDate.now());
			}
		} catch (Exception ex) {
			log.error("Exception in Audit Entity Listener", ex);
		}
	}
}
