package com.siy.siyresource.common.api.request;

import lombok.Data;

@Data
public class CreateCarPoolRequest extends CreatePostRequest{
    private String gender;
    private Long fare;
    private String departure;
    private String destination;
    private String departTime;
}
