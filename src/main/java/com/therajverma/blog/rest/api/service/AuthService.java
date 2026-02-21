package com.therajverma.blog.rest.api.service;

import com.therajverma.blog.rest.api.payload.LoginDto;
import com.therajverma.blog.rest.api.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
