package com.backend.backend.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserDto {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
}
