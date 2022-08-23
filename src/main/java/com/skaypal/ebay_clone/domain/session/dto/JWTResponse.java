package com.skaypal.ebay_clone.domain.session.dto;

import org.springframework.http.ResponseEntity;

public class JWTResponse  {

    String token;

    public JWTResponse(String token) {
        this.token = token;
    }

    public String getToken(){
        return token;
    }

    public void setToken(String token){
        this.token = token;
    }
}
