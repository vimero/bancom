package com.bancom.api.user.application.exception;

public class RuleViolatedException extends RuntimeException{

    public RuleViolatedException(String message) {
        super(message);
    }

}
