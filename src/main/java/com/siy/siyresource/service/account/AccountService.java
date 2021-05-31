package com.siy.siyresource.service.account;

import com.siy.siyresource.domain.dto.account.UserDto;
import com.siy.siyresource.feign.AccountServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class AccountService {

    private final AccountServiceClient accountServiceClient;
    private final CircuitBreakerFactory circuitBreakerFactory;

    public UserDto getUser(String username) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");

        log.info("Before call user-service for createdPosts");
        UserDto accountDto = circuitBreaker.run(() -> accountServiceClient.getUser(username),
                throwable -> new UserDto());
        log.info("After call user-service for createdPosts");


        return accountDto;
    }
}
