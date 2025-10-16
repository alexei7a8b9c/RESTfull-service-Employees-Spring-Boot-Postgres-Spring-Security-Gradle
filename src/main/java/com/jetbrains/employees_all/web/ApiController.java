package com.jetbrains.employees_all.web;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ApiController {
    @GetMapping("/api/public")
    public Map publicEndpoint() {
        return Map.of("Добро пожаловать:", "Public OK");
    }

    @GetMapping("/api/user")
    public Map userEndpoint(Authentication auth) {
        return Map.of(
                "Вы: ", "User OK",
                "principal", auth.getName(),
                "authorities", auth.getAuthorities()
        );
    }

    @GetMapping("/api/admin")

    public Map adminEndpoint(Authentication auth) {
        return Map.of(
                "Вы: ", "Admin OK",
                "principal", auth.getName(),
                "authorities", auth.getAuthorities()
        );
    }
}