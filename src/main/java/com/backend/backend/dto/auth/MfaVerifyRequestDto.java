package com.backend.backend.dto.auth;

import lombok.Data;

@Data
public class MfaVerifyRequestDto {
    private String challengeToken;
    private String otp;
}
