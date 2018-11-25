package com.notificationappservice.contactservice.contactservice.exceptions;

import java.util.Date;


/**
 * User: edward <br/>
 * Date: 11/25/18 7:50 AM <br/>
 */
public class ErrorMessage {
    private String message;
    private String path;
    private Date date;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
