package com.transfer.controller;

import com.transfer.service.security.JwtUtils;
import com.transfer.service.security.TokenStore;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class LogoutController {

    private final JwtUtils jwtUtils;
    private final TokenStore tokenStore;

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String jwt = parseJwt(request);
        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
            // Optionally add logic to invalidate the token in the store
            // tokenStore.invalidateToken(jwt);
            return ResponseEntity.ok("Logout successful");
        }
        return ResponseEntity.status(401).body("Invalid or expired token");
    }

    private String parseJwt(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}