package com.jiaotangbing.wms.jwt.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author lwh
 * @create 2024-11-13 11:30
 */
public class UsernameOrPasswordNullException extends AuthenticationException {
    public UsernameOrPasswordNullException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UsernameOrPasswordNullException(String msg) {
        super(msg);
    }
}

