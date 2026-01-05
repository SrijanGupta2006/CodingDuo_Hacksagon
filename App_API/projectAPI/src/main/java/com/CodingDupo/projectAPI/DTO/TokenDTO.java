package com.CodingDupo.projectAPI.DTO;

public class TokenDTO {
    private String accessToken;
    private String refreshToken;
    public TokenDTO(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
    public String getAccessToken() {
        return accessToken;
    }
    public String getRefreshToken() {
        return refreshToken;
    }
}
