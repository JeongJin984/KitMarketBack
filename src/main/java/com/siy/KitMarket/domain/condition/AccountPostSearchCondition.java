package com.siy.KitMarket.domain.condition;

import com.siy.KitMarket.domain.entity.account.AccountCode;
import lombok.Data;

@Data
public class AccountPostSearchCondition {
    private String username;
    private Long postId;
    private AccountCode code;
}
