package br.com.joaovq.crm.manager.service;

public interface AuthUseCase {
    String authenticate(String username, String password);
}
