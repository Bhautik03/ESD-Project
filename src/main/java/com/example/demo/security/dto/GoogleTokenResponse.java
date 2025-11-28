package com.example.demo.security.dto;

import lombok.Data;

@Data
public class GoogleTokenResponse {
    private String access_token;
    private String id_token;
    private String refresh_token;
    private Integer expires_in;
    private String token_type;
    private String scope;
}
