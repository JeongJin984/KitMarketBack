package com.siy.siyresource.domain.dto.account;

import lombok.Data;

@Data
public class AccountParamDto {
    String username;
    String password;
    Boolean rememberMe;
}
