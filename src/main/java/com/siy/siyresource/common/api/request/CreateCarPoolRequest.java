package com.siy.siyresource.common.api.request;

import com.siy.siyresource.domain.dto.DepartTime;
import lombok.Data;

@Data
public class CreateCarPoolRequest extends CreatePostRequest{
    private String gender;
    private String fare;
    private String departure;
    private String destination;
    private DepartTime departTime;
}
