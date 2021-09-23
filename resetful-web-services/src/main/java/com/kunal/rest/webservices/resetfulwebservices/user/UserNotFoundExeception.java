package com.kunal.rest.webservices.resetfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundExeception extends RuntimeException {

    public UserNotFoundExeception(String s) {
        super(s);
    }

    public UserNotFoundExeception() {
    }
}
