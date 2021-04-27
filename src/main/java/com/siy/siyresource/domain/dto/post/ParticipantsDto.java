package com.siy.siyresource.domain.dto.post;

import com.siy.siyresource.domain.entity.account.AccountCode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParticipantsDto {
    private String username;
    private String email;
    private Integer age;
    private AccountCode code;

    public ParticipantsDto(String username, String email, Integer age, AccountCode code) {
        this.username = username;
        this.email = email;
        this.age = age;
        this.code = code;
    }
}


