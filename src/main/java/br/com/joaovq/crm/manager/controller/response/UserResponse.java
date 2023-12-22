package br.com.joaovq.crm.manager.controller.response;

import br.com.joaovq.crm.manager.data.models.User;

public record UserResponse(
        String username,
        String email,
        Boolean isActive
) {
    public static UserResponse toResponse(User user) {
        return new UserResponse(
            user.getUsername(),
            user.getEmail(),
            user.getIsActive()
        );
    }
}
