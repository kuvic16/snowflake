package com.bracits.snowflake.repository;

import com.bracits.snowflake.entity.Right;
import com.bracits.snowflake.service.AbstractService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @Author Shaiful Islam Palash | kuvic16@gmail.com
 * @CreatedAt: 9/24/2020
 */
public interface RightRepository extends JpaRepository<Right, Long> {
	Optional<Right> findByName(String name);
}
