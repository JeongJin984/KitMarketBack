package com.siy.siyresource.feign;

import com.siy.siyresource.domain.dto.account.FullAccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="siy-user-service")
public interface AccountServiceClient {

    @GetMapping("/profile/{username}")
    public ResponseEntity<FullAccountDto> getProfile(@PathVariable("username") String username);


}
