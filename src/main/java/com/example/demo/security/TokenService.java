package com.example.demo.security;

import com.example.demo.security.dto.GoogleTokenResponse;
import com.example.demo.security.dto.GoogleUserInfo;
import com.google.gson.Gson;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class TokenService {

    @Value("${google.oauth.client-id}")
    private String clientId;

    @Value("${google.oauth.client-secret}")
    private String clientSecret;

    @Value("${google.oauth.redirect-uri}")
    private String redirectUri;

    private final Gson gson = new Gson();
    private final RestTemplate restTemplate = new RestTemplate();

    //Exchange authorization code for tokens
    public GoogleTokenResponse exchangeCode(String code) {
        String tokenEndpoint = "https://oauth2.googleapis.com/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("grant_type", "authorization_code");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                tokenEndpoint,
                request,
                String.class);

        return gson.fromJson(response.getBody(), GoogleTokenResponse.class);
    }

    // Validate ID token with Google
    public boolean validateIdToken(String idToken) {
        try {
            String tokenInfoUrl = "https://oauth2.googleapis.com/tokeninfo?id_token=" + idToken;
            ResponseEntity<String> response = restTemplate.getForEntity(tokenInfoUrl, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                @SuppressWarnings("unchecked")
                Map<String, Object> tokenInfo = gson.fromJson(response.getBody(), Map.class);

                // Verify the token belongs to our client
                String aud = (String) tokenInfo.get("aud");
                return clientId.equals(aud);
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    //Validate access token with Google
    public boolean validateAccessToken(String accessToken) {
        try {
            String tokenInfoUrl = "https://oauth2.googleapis.com/tokeninfo?access_token=" + accessToken;
            ResponseEntity<String> response = restTemplate.getForEntity(tokenInfoUrl, String.class);
            return response.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            return false;
        }
    }

    //Get user info from ID token
    public GoogleUserInfo getUserInfoFromIdToken(String idToken) {
        try {
            String tokenInfoUrl = "https://oauth2.googleapis.com/tokeninfo?id_token=" + idToken;
            ResponseEntity<String> response = restTemplate.getForEntity(tokenInfoUrl, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return gson.fromJson(response.getBody(), GoogleUserInfo.class);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Store tokens in session
     * Note: ID token is stored in HTTP-only cookie, not in session
     */
    public void storeTokensInSession(HttpSession session, GoogleTokenResponse tokens) {
        session.setAttribute("access_token", tokens.getAccess_token());
        // ID token is stored in HTTP-only cookie, not in session
        if (tokens.getRefresh_token() != null) {
            session.setAttribute("refresh_token", tokens.getRefresh_token());
        }
    }

    //Get ID token from session
    public String getIdTokenFromSession(HttpSession session) {
        return (String) session.getAttribute("id_token");
    }

    //Clear tokens from session
    public void clearTokensFromSession(HttpSession session) {
        session.removeAttribute("access_token");
        session.removeAttribute("id_token");
        session.removeAttribute("refresh_token");
    }

    //Get ID token from HTTP-only cookie
    public String getIdTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("id_token".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    //Get access token from session
    public String getAccessTokenFromSession(HttpSession session) {
        return (String) session.getAttribute("access_token");
    }
}
