package com.example.demo.controller;

import com.example.demo.security.TokenService;
import com.example.demo.security.dto.GoogleTokenResponse;
import com.example.demo.security.dto.GoogleUserInfo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequiredArgsConstructor
public class OAuthController {

    private final TokenService tokenService;

    @Value("${google.oauth.client-id}")
    private String clientId;

    @Value("${google.oauth.redirect-uri}")
    private String redirectUri;

    @Value("${google.oauth.allowed-email}")
    private String allowedEmail;

    @GetMapping("/login")
    public RedirectView login() throws UnsupportedEncodingException {
        String scope = "openid email profile";
        String authUrl = "https://accounts.google.com/o/oauth2/v2/auth" +
                "?client_id=" + clientId +
                "&redirect_uri=" + URLEncoder.encode(redirectUri, "UTF-8") +
                "&response_type=code" +
                "&scope=" + URLEncoder.encode(scope, "UTF-8") +
                "&access_type=offline" +
                "&prompt=consent";

        return new RedirectView(authUrl);
    }

    @GetMapping("/oauth2/callback")
    public RedirectView callback(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "error_description", required = false) String errorDescription,
            HttpServletRequest request,
            HttpServletResponse response) {
        
        // Handle OAuth errors (user cancelled, access denied, etc.)
        if (error != null) {
            String errorMsg = "access_denied".equals(error) ? "cancelled" : error;
            return new RedirectView("http://localhost:3000?error=" + errorMsg);
        }
        
        // Handle missing authorization code
        if (code == null || code.isEmpty()) {
            return new RedirectView("http://localhost:3000?error=no_code");
        }
        
        try {
            GoogleTokenResponse tokens = tokenService.exchangeCode(code);

            if (!tokenService.validateIdToken(tokens.getId_token())) {
                return new RedirectView("http://localhost:3000?error=invalid_token");
            }

            GoogleUserInfo userInfo = tokenService.getUserInfoFromIdToken(tokens.getId_token());

            if (userInfo == null || !allowedEmail.equals(userInfo.getEmail())) {
                return new RedirectView("http://localhost:3000?error=unauthorized");
            }

            // Get or create session
            HttpSession session = request.getSession(true);
            // Store access token and refresh token in session
            session.setAttribute("access_token", tokens.getAccess_token());
            if (tokens.getRefresh_token() != null) {
                session.setAttribute("refresh_token", tokens.getRefresh_token());
            }
            
            // Mark session as authenticated and store server startup time
            // This allows us to detect if server was restarted
            session.setAttribute("authenticated", true);
            session.setAttribute("serverStartupTime", com.example.demo.config.StartupListener.getServerStartupTime());

            // Store ID token in HTTP-only cookie
            Cookie idTokenCookie = new Cookie("id_token", tokens.getId_token());
            idTokenCookie.setHttpOnly(true);
            idTokenCookie.setSecure(false); // Set to true in production with HTTPS
            idTokenCookie.setPath("/");
            idTokenCookie.setMaxAge(60 * 60 * 24); // 24 hours
            response.addCookie(idTokenCookie);

            // Redirect to frontend - session will be maintained via JSESSIONID cookie
            return new RedirectView("http://localhost:3000?authenticated=true");
        } catch (Exception e) {
            e.printStackTrace();
            return new RedirectView("http://localhost:3000?error=auth_failed");
        }
    }

    @GetMapping("/signout")
    public RedirectView signout(HttpSession session, HttpServletResponse response) {
        // Clear tokens from session
        if (session != null) {
            tokenService.clearTokensFromSession(session);
            session.removeAttribute("authenticated");
            session.invalidate();
        }

        // Delete ID token cookie
        Cookie idTokenCookie = new Cookie("id_token", null);
        idTokenCookie.setHttpOnly(true);
        idTokenCookie.setSecure(false);
        idTokenCookie.setPath("/");
        idTokenCookie.setMaxAge(0); // Delete cookie
        response.addCookie(idTokenCookie);

        // Redirect to frontend login page instead of Google login
        return new RedirectView("http://localhost:3000");
    }

    @GetMapping("/api/auth/status")
    public boolean checkAuthStatus(HttpServletRequest request, HttpServletResponse response) {
        // Check if a valid session exists
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("authenticated") == null) {
            // Session doesn't exist or wasn't authenticated
            clearIdTokenCookie(request, response);
            return false;
        }
        
        // Check if server was restarted by comparing startup timestamps
        Long sessionStartupTime = (Long) session.getAttribute("serverStartupTime");
        long currentServerStartupTime = com.example.demo.config.StartupListener.getServerStartupTime();
        
        if (sessionStartupTime == null || sessionStartupTime.longValue() != currentServerStartupTime) {
            // Server was restarted - invalidate session and clear cookies
            session.invalidate();
            clearIdTokenCookie(request, response);
            return false;
        }
        
        // Session exists and is authenticated, now validate the ID token
        String idToken = tokenService.getIdTokenFromCookie(request);
        if (idToken == null) {
            // Try session as fallback
            idToken = tokenService.getIdTokenFromSession(session);
        }
        
        if (idToken == null) {
            return false;
        }
        
        // Validate token with Google
        return tokenService.validateIdToken(idToken);
    }
    
    private void clearIdTokenCookie(HttpServletRequest request, HttpServletResponse response) {
        String idToken = tokenService.getIdTokenFromCookie(request);
        if (idToken != null) {
            Cookie idTokenCookie = new Cookie("id_token", null);
            idTokenCookie.setHttpOnly(true);
            idTokenCookie.setSecure(false);
            idTokenCookie.setPath("/");
            idTokenCookie.setMaxAge(0); // Delete cookie
            response.addCookie(idTokenCookie);
        }
    }
}
