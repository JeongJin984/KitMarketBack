package com.siy.siyresource.feign;


import com.siy.siyresource.domain.dto.RelatedUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "siy-user-service")
public interface UserServiceClient {
    @GetMapping("/profile/{username}")
    RelatedUser getUserData();





    //@GetMapping(value = "/user")
    //@PostMapping(value = "/signup")
}
