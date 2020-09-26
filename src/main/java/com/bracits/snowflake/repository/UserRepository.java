package com.bracits.snowflake.repository;

import com.bracits.snowflake.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @Author Shaiful Islam Palash | kuvic16@gmail.com
 * @CreatedAt: 9/25/2020
 */
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByPin(String pin);
	Optional<User> findByEmail(String email);
}
