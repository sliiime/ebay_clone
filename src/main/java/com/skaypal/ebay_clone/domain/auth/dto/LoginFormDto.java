package com.skaypal.ebay_clone.domain.auth.dto;

public class LoginFormDto {

    private String username;
    private String password;

    public String getUsername(){return this.username;}
    public String getPassword(){return this.password;}

    public void setUsername(String username){this.username = username;}
    public void setPassword(String password){this.password = password;}

    public LoginFormDto(){}

    public LoginFormDto(String username,String password){
        this.username = username;
        this.password = password;
    }
}
