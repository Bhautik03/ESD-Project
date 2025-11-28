package com.example.demo.security.dto;

import lombok.Data;

@Data
public class GoogleUserInfo {
    private String sub;
    private String email;
    private Boolean email_verified;
    private String name;
    private String picture;
    private String given_name;
    private String family_name;
}
