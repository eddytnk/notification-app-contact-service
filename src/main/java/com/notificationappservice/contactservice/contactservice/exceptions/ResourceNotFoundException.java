package com.notificationappservice.contactservice.contactservice.exceptions;


/**
 * User: edward <br/>
 * Date: 11/25/18 7:40 AM <br/>
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
