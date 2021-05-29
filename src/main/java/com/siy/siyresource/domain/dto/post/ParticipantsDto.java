package com.siy.siyresource.domain.dto.post;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParticipantsDto {
    private String username;
    private String email;
    private Integer age;

    @QueryProjection
    public ParticipantsDto(String username, String email, Integer age) {
        this.username = username;
        this.email = email;
        this.age = age;
    }
}


