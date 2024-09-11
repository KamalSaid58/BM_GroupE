package com.transfer.service.security;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class BlackListToken {
    private Set<String> blackListToken = new HashSet<String>();

    public void blackListToken(String token) {
        blackListToken.add(token);
    }

    public boolean isBlackListed(String token) {
        return blackListToken.contains(token);
    }
}
