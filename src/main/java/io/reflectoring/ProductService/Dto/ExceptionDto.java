package io.reflectoring.ProductService.Dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class ExceptionDto extends Exception{

    private HttpStatus errorCode;
    private String message;
    public ExceptionDto(HttpStatus httpStatus, String message){
        this.errorCode = httpStatus;
        this.message  = message;
    }
    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(HttpStatus errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}
