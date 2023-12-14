package org.zerock.myapp.exception;

public class UserNotLoggedInException extends RuntimeException{

    public UserNotLoggedInException(String message) {
        super(message);
    } // Constructor

} // end class
