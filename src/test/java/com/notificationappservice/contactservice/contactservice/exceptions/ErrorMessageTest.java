package com.notificationappservice.contactservice.contactservice.exceptions;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;


public class ErrorMessageTest {

    private String message = "An error occured";
    private String  path = "users/hello";
    private Date date = new Date();

    @Test
    public void test_getters_and_setters(){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(message);
        errorMessage.setPath(path);
        errorMessage.setDate(date);

        assertEquals(errorMessage.getMessage(),message);
        assertEquals(errorMessage.getPath(),path);
        assertEquals(errorMessage.getDate(),date);
    }
}
