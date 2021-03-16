package com.siy.KitMarket.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siy.KitMarket.domain.dto.AccountAuthDto;
import com.siy.KitMarket.security.token.AjaxAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private ObjectMapper objectMapper = new ObjectMapper();

    public AjaxLoginProcessingFilter() {
        super(new AntPathRequestMatcher("/api/login"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if(!isAjax(request)) {
            throw new IllegalStateException("Authentication not Supported");
        }

        AccountAuthDto accountAuthDto = objectMapper.readValue(request.getReader(), AccountAuthDto.class);
        if(!StringUtils.hasText(accountAuthDto.getUsername()) || !StringUtils.hasText(accountAuthDto.getPassword())) {
            throw new IllegalArgumentException("Username or Password is required");
        }

        AjaxAuthenticationToken ajaxAuthenticationToken = new AjaxAuthenticationToken(accountAuthDto.getUsername(), accountAuthDto.getPassword());

        return getAuthenticationManager().authenticate(ajaxAuthenticationToken);
    }

    private boolean isAjax(HttpServletRequest request) {
        String header = request.getHeader("X-Request-With");
        if("XMLHttpRequest".equals(request.getHeader("X-Request-With"))) {
            return true;
        }
        return false;
    }
}
