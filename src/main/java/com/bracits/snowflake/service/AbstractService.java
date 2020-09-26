package com.bracits.snowflake.service;

import com.bracits.snowflake.exception.ServiceException;
import org.springframework.data.domain.Page;

/**
 * @Author Shaiful Islam Palash | kuvic16@gmail.com
 * @CreatedAt: 9/24/2020
 */
public interface AbstractService<T> {
    public T create(T t) throws ServiceException;
    public T update(long id,T t) throws ServiceException;
    public boolean delete(long id) throws ServiceException;
    public T findById(long id);
    public Page<T> findAll(int page, int size);
}
