package com.siy.siyresource.common.api.request;

import lombok.Data;

@Data
public class CreateMiniProjectRequest extends CreatePostRequest{
    private String duration;
    private String subject;

}
