package com.siy.siyresource.feign;

import com.siy.siyresource.domain.dto.account.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="siy-user-service")
public interface AccountServiceClient {
    @GetMapping("/user/{username}")
    UserDto getUser(@PathVariable("username") String username);
}
