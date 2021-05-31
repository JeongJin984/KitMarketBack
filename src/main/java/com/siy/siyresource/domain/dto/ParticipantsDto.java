package com.siy.siyresource.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
    @QueryProjection
    public ParticipantsDto(String username){
        this.username = username;
    }
}


