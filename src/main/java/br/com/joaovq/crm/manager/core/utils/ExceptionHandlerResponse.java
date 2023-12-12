package br.com.joaovq.crm.manager.core.utils;

public record ExceptionHandlerResponse(
        Integer statusCode,
        String message,
        String target,
        String className
) {
}
