package com.siy.siyresource.security.service;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
public class CustomTokenExtractor implements TokenExtractor {

    private static final String TOKEN_KEY_JWT = "Authorization";

    @Override
    public Authentication extract(HttpServletRequest request) {
        String result = getTokenFromRequest(request);
        return new PreAuthenticatedAuthenticationToken(result.substring(6), "");
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(TOKEN_KEY_JWT))
                .findFirst()
                .map(Cookie::getValue).orElse(null);
    }

}
