package com.job_tracking_system.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String login;

    private String role;

    @NotBlank
    @Size(min = 3, max = 20)
    private String password;
}
