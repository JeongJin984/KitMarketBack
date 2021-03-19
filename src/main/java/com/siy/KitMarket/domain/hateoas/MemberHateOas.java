package com.siy.KitMarket.domain.hateoas;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

public class MemberHateOas extends RepresentationModel<MemberHateOas> {

    private final String name;
    private final String email;
    private final Integer age;

    @JsonCreator
    public MemberHateOas(@JsonProperty("name") String name,
                         @JsonProperty("email") String email,
                         @JsonProperty("age") Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
}
