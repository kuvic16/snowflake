package com.bracits.snowflake.service.role;

import com.bracits.snowflake.entity.Right;
import com.bracits.snowflake.entity.Role;
import com.bracits.snowflake.service.AbstractService;

/**
 * @Author Shaiful Islam Palash | kuvic16@gmail.com
 * @CreatedAt: 9/24/2020
 */
public interface RoleService extends AbstractService<Role>{
    public Role findByName(String name);
}
