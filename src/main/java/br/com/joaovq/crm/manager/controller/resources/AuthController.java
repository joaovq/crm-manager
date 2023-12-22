package br.com.joaovq.crm.manager.controller.resources;

import br.com.joaovq.crm.manager.controller.request.SignInRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "v1/token")
public class AuthController {
    @PostMapping
    public ResponseEntity<String> token(@Valid @RequestBody SignInRequest tokenRequest) {
        return ResponseEntity.ok("Token path");
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@Valid @RequestBody SignInRequest tokenRequest) {
        return ResponseEntity.ok("Token path");
    }
}
