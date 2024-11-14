package com.example.appchat.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    USER_EXISTED(1001,"User Existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1002,"User not found", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1003,"Unauthenticated!!",HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1004,"You do not have permission!!",HttpStatus.FORBIDDEN),
    EMAIL_NOT_EXISTS(1004,"Email not exists!!",HttpStatus.BAD_REQUEST),
    EMAIL_OR_PHONE_EXISTS(1005,"Email or phone already existed!!",HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED(1006,"Email is already existed!!",HttpStatus.BAD_REQUEST),
    PHONE_EXISTED(1007,"Phone is already existed!!",HttpStatus.BAD_REQUEST),
    CONTACT_NOT_FOUND(1007,"Contact not found!!",HttpStatus.BAD_REQUEST)
    ;

    private final int errorCode;

    private final String message;

    private final HttpStatusCode statusCode;

    ErrorCode(int errorCode, String message, HttpStatusCode statusCode){
        this.errorCode = errorCode;
        this.message = message;
        this.statusCode = statusCode;
    }


}
