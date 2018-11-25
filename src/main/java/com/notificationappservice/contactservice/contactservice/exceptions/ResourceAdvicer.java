package com.notificationappservice.contactservice.contactservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * User: edward <br/>
 * Date: 11/25/18 7:40 AM <br/>
 */

@ControllerAdvice
public class ResourceAdvicer {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleResourceNotFound(Exception ex, HttpServletRequest request){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(ex.getMessage());
        errorMessage.setPath(request.getPathInfo());
        errorMessage.setDate(new Date());

        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NO_CONTENT);
    }
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException( Exception ex, HttpServletRequest request){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(ex.getMessage());
        errorMessage.setPath(request.getPathInfo());
        errorMessage.setDate(new Date());

        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleGeneralAdvice(Exception ex, HttpServletRequest request){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(ex.getMessage());
        errorMessage.setPath(request.getPathInfo());
        errorMessage.setDate(new Date());

        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
