package com.siy.KitMarket.domain.dto;

import com.siy.KitMarket.domain.entity.account.Account;
import com.siy.KitMarket.domain.entity.account.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRoleAuthDto {
    private Role role;
    private Account account;
}
