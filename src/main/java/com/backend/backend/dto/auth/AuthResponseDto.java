package com.backend.backend.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDto {
    private String accessToken;
    private String refreshToken;
    private AuthUserDto user;
    private Boolean mfaRequired;
    private String challengeToken;
    private Integer mfaExpiresInSeconds;
    private String mfaDestination;
}
