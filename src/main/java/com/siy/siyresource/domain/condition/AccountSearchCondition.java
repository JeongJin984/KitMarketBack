package com.siy.siyresource.domain.condition;

import lombok.Data;

@Data
public class AccountSearchCondition {
    private String userName;

    public AccountSearchCondition(String userName) {
        this.userName = userName;
    }
}
