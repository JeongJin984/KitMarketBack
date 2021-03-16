package com.siy.KitMarket.domain.dto;

import com.siy.KitMarket.domain.entity.account.AccountRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountAuthDto {
    private String username;
    private String email;
    private int age;
    private String password;
    private Set<AccountRole> accountRoles;
}
