package com.transfer.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenStore {
    @Autowired
    private JwtUtils jwtUtils;

    public String getUserNameFromJwtToken(String token) {
        return jwtUtils.getUserNameFromJwtToken(token);
    }

    @Autowired
    private BlackListToken blackListToken;

    public boolean validateJwtToken(String authToken) {
        return jwtUtils.validateJwtToken(authToken);
    }

    public Authentication getAuthentication(String username) {
        // Here you should implement a way to retrieve user details by username
        UserDetails userDetails = null; // Retrieve user details from your service
        return new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    private ConcurrentHashMap<String, Boolean> invalidatedTokens = new ConcurrentHashMap<>();

    public void invalidateToken(String token) {
        blackListToken.blackListToken(token);
        invalidatedTokens.put(token, true);
    }

    public boolean isTokenInvalidated(String token) {
        if(blackListToken.isBlackListed(token))
            return true;
        return invalidatedTokens.containsKey(token);
    }
}
