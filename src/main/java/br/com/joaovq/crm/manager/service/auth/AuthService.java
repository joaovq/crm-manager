package br.com.joaovq.crm.manager.service.auth;

import br.com.joaovq.crm.manager.controller.request.RegisterRequest;
import br.com.joaovq.crm.manager.data.models.User;
import br.com.joaovq.crm.manager.data.models.UserRole;
import br.com.joaovq.crm.manager.data.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthUseCase {
    private final AuthenticationManager authManager;
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthenticationManager authManager, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authManager = authManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(String username, String password) {
        var authReq
                = new UsernamePasswordAuthenticationToken(username, password);
        var auth = authManager.authenticate(authReq);
        var sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        return auth;
    }

    @Override
    public User register(RegisterRequest request) {
        var passwordEncrypted = passwordEncoder.encode(request.password());
        return userRepository.save(new User(
                request.username(),
                passwordEncrypted,
                UserRole.USER,
                true,
                request.email()
        ));
    }
}
