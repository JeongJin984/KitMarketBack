package com.siy.siyresource.domain.condition;

import com.siy.siyresource.domain.entity.account.AccountCode;
import lombok.Data;

@Data
public class AccountPostSearchCondition {
    private String username;
    private Long postId;
    private AccountCode code;
}
