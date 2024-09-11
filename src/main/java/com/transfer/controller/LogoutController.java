package com.transfer.controller;

import com.transfer.service.security.BlackListToken;
import com.transfer.service.security.JwtUtils;
import com.transfer.service.security.TokenStore;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class LogoutController {

    private final JwtUtils jwtUtils;
    private final TokenStore tokenStore;

    @Autowired
    private BlackListToken blackListToken;

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }
        blackListToken.blackListToken(token);
        return "Successfully logged out";
    }
}