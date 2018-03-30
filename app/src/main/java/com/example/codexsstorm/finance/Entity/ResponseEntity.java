package com.example.codexsstorm.finance.Entity;

/**
 * Created by ompra on 11/21/2017.
 */

public class ResponseEntity {
    StatusEntity status;
    String token;


    public ResponseEntity(StatusEntity status, String token) {
        this.status = status;
        this.token = token;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
