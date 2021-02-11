package com.siy.KitMarket.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {
    @GetMapping(value = "/hello")
    String jwtLogin() {
        return "hello world";
    }
}
