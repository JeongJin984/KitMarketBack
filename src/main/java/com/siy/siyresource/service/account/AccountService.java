package com.siy.siyresource.service.account;

import com.siy.siyresource.feign.AccountServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class AccountService {

    private final AccountServiceClient accountServiceClient;


}
