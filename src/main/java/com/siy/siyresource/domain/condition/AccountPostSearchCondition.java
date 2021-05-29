package com.siy.siyresource.domain.condition;

import lombok.Data;

@Data
public class AccountPostSearchCondition {
    private String username;
    private Long postId;
}
