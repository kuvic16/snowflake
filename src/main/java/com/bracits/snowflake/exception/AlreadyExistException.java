package com.bracits.snowflake.exception;

/**
 * @Author Shaiful Islam Palash | kuvic16@gmail.com
 * @CreatedAt: 9/25/2020
 */
public class AlreadyExistException extends ServiceException{
    public AlreadyExistException(String message) {
        super(message);
    }
}
