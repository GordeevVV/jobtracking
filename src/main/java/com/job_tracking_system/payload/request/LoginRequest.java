package com.job_tracking_system.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Accessors(chain = true)
public class LoginRequest {
    @NotBlank
    private String login;

    @NotBlank
    private String password;

}
