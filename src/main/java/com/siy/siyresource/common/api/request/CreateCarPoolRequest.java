package com.siy.siyresource.common.api.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.siy.siyresource.domain.dto.DepartTime;
import lombok.Data;

@Data
public class CreateCarPoolRequest extends CreatePostRequest{
    private String gender;
    private String fare;
    private DepartTime departTime;
    private String destination;
    private Double lat;
    @JsonAlias("long")
    private Double Long;
}
