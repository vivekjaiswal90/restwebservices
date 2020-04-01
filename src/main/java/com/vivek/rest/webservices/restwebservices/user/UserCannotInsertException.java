package com.vivek.rest.webservices.restwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserCannotInsertException extends RuntimeException{

    public UserCannotInsertException(String message){
        super(message);
    }
}
