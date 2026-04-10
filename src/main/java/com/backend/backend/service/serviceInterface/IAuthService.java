package com.backend.backend.service.serviceInterface;

import com.backend.backend.dao.entities.User;
import com.backend.backend.dto.auth.AuthResponseDto;
import com.backend.backend.dto.auth.LoginRequestDto;
import com.backend.backend.dto.auth.RefreshTokenRequestDto;
import com.backend.backend.dto.auth.RegisterRequestDto;
import com.backend.backend.dto.auth.TokenResponseDto;

public interface IAuthService {
    AuthResponseDto register(RegisterRequestDto request);

    AuthResponseDto login(LoginRequestDto request);

    TokenResponseDto refresh(RefreshTokenRequestDto request);

    User getCurrentUser();
}
