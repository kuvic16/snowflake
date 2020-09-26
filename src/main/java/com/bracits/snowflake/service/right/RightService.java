package com.bracits.snowflake.service.right;

import com.bracits.snowflake.entity.Right;
import com.bracits.snowflake.service.AbstractService;
import java.util.List;

/**
 * @Author Shaiful Islam Palash | kuvic16@gmail.com
 * @CreatedAt: 9/24/2020
 */
public interface RightService extends AbstractService<Right>{
    public Right findByName(String name);
    // please don't use this because it's for an example. You can use your own way
    public List<Right> findByJdbcTemplate(long id);
    public List<Right> findByJPA(int page, int size, long id);
}
