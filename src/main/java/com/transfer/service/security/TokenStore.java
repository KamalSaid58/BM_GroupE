package com.transfer.service.security;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenStore {
    private ConcurrentHashMap<String, Boolean> invalidatedTokens = new ConcurrentHashMap<>();

    public void invalidateToken(String token) {
        invalidatedTokens.put(token, true);
    }

    public boolean isTokenInvalidated(String token) {
        return invalidatedTokens.containsKey(token);
    }
}
