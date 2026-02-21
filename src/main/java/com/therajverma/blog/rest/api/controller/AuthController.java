package com.therajverma.blog.rest.api.controller;



import com.therajverma.blog.rest.api.payload.LoginDto;
import com.therajverma.blog.rest.api.payload.RegisterDto;
import com.therajverma.blog.rest.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    //  Register endpoint
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        return ResponseEntity.ok(authService.register(registerDto));
    }

    //  Login endpoint
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(authService.login(loginDto));
    }
}