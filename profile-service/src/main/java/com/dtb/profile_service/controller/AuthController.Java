package com.dtb.profile_service.controller;

import com.dtb.profile_service.dto.LoginRequest;
import com.dtb.profile_service.RegisterRequest;
import com.dtb.profile_service.User;
import com.bank.profile.security.JwtTokenUtil;
import com.dtb.profile_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthController(UserService userService, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        userService.register(registerRequest);
        return ResponseEntity.ok("Registration successful");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.authenticate(loginRequest);
        String token = jwtTokenUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getProfile(@RequestHeader("Authorization") String token) {
        String username = jwtTokenUtil.extractUsername(token.substring(7));
        User user = userService.findUserByEmail(username);
        return ResponseEntity.ok(user);
    }
}
