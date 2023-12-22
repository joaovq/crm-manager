package br.com.joaovq.crm.manager.data.models;

public enum UserRole {
    ADMIN("ADMIN"),
    USER("USER");

    final String role;

    UserRole(String role) {
        this.role = role;
    }
}
