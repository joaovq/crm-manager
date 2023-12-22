package br.com.joaovq.crm.manager.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record SignInRequest(
        @NotBlank
        @Length(min = 3, max = 30)
        String username,
        @NotBlank
        @Length(min = 6, max = 18)
        String password
) {
}
