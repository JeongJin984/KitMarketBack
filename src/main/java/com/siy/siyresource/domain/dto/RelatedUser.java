package com.siy.siyresource.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelatedUser {
    private String username;
    private String email;
    private Integer age;
    private String major;
    private String gender;
    private Integer grade;
}
