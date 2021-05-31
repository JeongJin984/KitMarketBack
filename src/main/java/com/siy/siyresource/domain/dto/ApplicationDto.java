package com.siy.siyresource.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApplicationDto {
    private Long id;

    private String username;
    private String content;
    private String chatDate; //채팅 시간



    @QueryProjection
    public ApplicationDto(Long id, String content, LocalDateTime chatDate, String username) {
        this.id = id;
        this.content = content;
        this.chatDate = chatDate.toString();
        this.username = username;
    }
}
