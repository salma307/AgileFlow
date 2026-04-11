package com.backend.backend.service.serviceInterface;

public interface IEmailService {

    void sendOtpEmail(String toEmail, String otpCode, long otpDurationMinutes);
}