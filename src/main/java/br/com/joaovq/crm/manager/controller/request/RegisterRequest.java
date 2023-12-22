package br.com.joaovq.crm.manager.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Schema(name = "Register Request")
public record RegisterRequest(
        @NotBlank
        @Length(min = 3, max = 30)
        String username,
        @NotBlank
        @Length(min = 6, max = 18)
        String password,
        @NotBlank
        @Email
        String email
) {
}
