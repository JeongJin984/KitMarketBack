package com.siy.siyresource.common.api.request;

import lombok.Data;

@Data
public class CreatePostRequest{
    private String writer;
    private String title;
    private String content;
    private String deadLine;
    private String createdAt;
    private Integer maxNum;
    private Integer curNum;
    private String category;
}