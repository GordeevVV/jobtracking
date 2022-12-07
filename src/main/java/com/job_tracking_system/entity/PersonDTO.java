package com.job_tracking_system.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class PersonDTO {
    @NotBlank(message = "Login is required!")
    private String login;
    @NotBlank(message = "Password is required!")
    private String password;
    private String position;
}
