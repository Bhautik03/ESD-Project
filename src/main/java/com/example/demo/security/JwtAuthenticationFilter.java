package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // Check if a valid session exists
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("authenticated") == null) {
            // Session doesn't exist or wasn't authenticated
            filterChain.doFilter(request, response);
            return;
        }
        
        // Check if server was restarted by comparing startup timestamps
        Long sessionStartupTime = (Long) session.getAttribute("serverStartupTime");
        long currentServerStartupTime = com.example.demo.config.StartupListener.getServerStartupTime();
        
        if (sessionStartupTime == null || sessionStartupTime.longValue() != currentServerStartupTime) {
            // Server was restarted - invalidate session
            session.invalidate();
            filterChain.doFilter(request, response);
            return;
        }
        
        // Extract ID token from cookie (preferred) or header, then fallback to session
        String idToken = tokenService.getIdTokenFromCookie(request);
        
        // If not in cookie, try Authorization header
        if (idToken == null) {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                idToken = authHeader.substring(7);
            }
        }
        
        // If still not found, try session (for backward compatibility)
        if (idToken == null) {
            if (session != null) {
                idToken = tokenService.getIdTokenFromSession(session);
            }
        }

        // If token exists, validate it with Google
        if (idToken != null && tokenService.validateIdToken(idToken)) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    idToken,
                    null,
                    new ArrayList<>());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
