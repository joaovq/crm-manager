package br.com.joaovq.crm.manager.service.auth;

import br.com.joaovq.crm.manager.controller.request.RegisterRequest;
import br.com.joaovq.crm.manager.data.models.User;
import org.springframework.security.core.Authentication;

public interface AuthUseCase {
    Authentication authenticate(String username, String password);
    User register(RegisterRequest request);
}
