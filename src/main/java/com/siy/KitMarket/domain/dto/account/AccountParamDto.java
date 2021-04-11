package com.siy.KitMarket.domain.dto.account;

import lombok.Data;

@Data
public class AccountParamDto {
    String username;
    String password;
    Boolean rememberMe;
}
