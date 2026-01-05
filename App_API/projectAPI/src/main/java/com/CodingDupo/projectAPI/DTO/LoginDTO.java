package com.CodingDupo.projectAPI.DTO;

public class LoginDTO {
    private String username;
    private String password;
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
}
