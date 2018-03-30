package com.example.codexsstorm.finance.Entity;

/**
 * Created by ompra on 11/21/2017.
 */

public class StatusEntity {
    boolean code;
    String message;
    String other_message;

    public StatusEntity(boolean code, String message, String other_message) {
        this.code = code;
        this.message = message;
        this.other_message = other_message;
    }

    public boolean getCode() {
        return code;
    }

    public void setCode(boolean code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOther_message() {
        return other_message;
    }

    public void setOther_message(String other_message) {
        this.other_message = other_message;
    }
}
