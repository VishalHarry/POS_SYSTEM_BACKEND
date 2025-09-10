package com.posSystem.Exception;

public class UserException extends RuntimeException {

    public UserException(String message) {
        super(message);  // parent constructor ko call karna chahiye
    }
}
