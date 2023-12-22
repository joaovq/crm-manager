package br.com.joaovq.crm.manager.controller.handler;

import br.com.joaovq.crm.manager.core.utils.ExceptionHandlerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class AuthorizationExceptionHandler {
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionHandlerResponse> handleBadCredentials(
            BadCredentialsException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(
                new ExceptionHandlerResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        ex.getMessage(),
                        ex.getClass().getName(),
                        "class.AuthController"
                )
        );
    }

    @ExceptionHandler(CredentialsExpiredException.class)
    public ResponseEntity<ExceptionHandlerResponse> handleBadCredentials(
            CredentialsExpiredException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(
                new ExceptionHandlerResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        ex.getMessage(),
                        ex.getClass().getName(),
                        "class.AuthController"
                )
        );
    }
}
