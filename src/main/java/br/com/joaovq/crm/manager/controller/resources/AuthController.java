package br.com.joaovq.crm.manager.controller.resources;

import br.com.joaovq.crm.manager.controller.request.RegisterRequest;
import br.com.joaovq.crm.manager.controller.request.SignInRequest;
import br.com.joaovq.crm.manager.controller.response.TokenResponse;
import br.com.joaovq.crm.manager.controller.response.UserResponse;
import br.com.joaovq.crm.manager.data.models.User;
import br.com.joaovq.crm.manager.service.auth.AuthService;
import br.com.joaovq.crm.manager.service.auth.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("v1/token")
public class AuthController {

    private final AuthService authUseCase;
    private final TokenService tokenService;

    public AuthController(AuthService authUseCase, TokenService tokenService) {
        this.authUseCase = authUseCase;
        this.tokenService = tokenService;
    }

    @Operation(summary = "Sign In user in Application")
    @PostMapping
    public ResponseEntity<TokenResponse> token(@Valid @RequestBody SignInRequest tokenRequest) {
        Authentication authenticate = authUseCase.authenticate(tokenRequest.username(), tokenRequest.password());
        String generatedToken = tokenService.generateToken((User) authenticate.getPrincipal());
        return ResponseEntity.ok(new TokenResponse(generatedToken));
    }

    @Operation(summary = "Sign up user in Application")
    @PostMapping("register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest req) {
        User register = authUseCase.register(req);
        return ResponseEntity.created(URI.create("token")).body(UserResponse.toResponse(register));
    }
}
