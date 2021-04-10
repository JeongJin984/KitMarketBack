package com.siy.KitMarket.domain.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ApplicationDto {
    private Long id;

    private String content;

    private LocalDateTime chatDate; //채팅 시간
}
