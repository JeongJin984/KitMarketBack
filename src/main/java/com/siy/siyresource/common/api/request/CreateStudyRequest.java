package com.siy.siyresource.common.api.request;

import lombok.Data;

@Data
public class CreateStudyRequest extends CreatePostRequest{
    private String subject;
    private String region;
    private String duration;
}
