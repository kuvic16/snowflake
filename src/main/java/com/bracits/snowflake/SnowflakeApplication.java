package com.bracits.snowflake;

import com.bracits.snowflake.entity.Role;
import com.bracits.snowflake.entity.User;
import com.bracits.snowflake.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.core.env.Environment;

/**
 * @Author Shaiful Islam Palash | kuvic16@gmail.com
 * @CreatedAt: 9/20/2020
 */
@SpringBootApplication
@EntityScan(basePackageClasses=User.class)
public class SnowflakeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnowflakeApplication.class, args);
	}

}
