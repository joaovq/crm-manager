package br.com.joaovq.crm.manager.controller.handler;

import br.com.joaovq.crm.manager.core.exception.CustomerNotFoundException;
import br.com.joaovq.crm.manager.core.utils.ExceptionHandlerResponse;
import br.com.joaovq.crm.manager.service.customer.CustomerService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomerExceptionHandler {
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ExceptionHandlerResponse> handleCustomerNotFound(
            Exception ex, WebRequest request) {
        return new ResponseEntity<ExceptionHandlerResponse>(
                new ExceptionHandlerResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        "Customer not found! try again.",
                        ex.toString(),
                        CustomerService.class.toString()
                ), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionHandlerResponse> handleDataIntegrityViolation(
            DataIntegrityViolationException ex, WebRequest request) {
        return new ResponseEntity<>(
                new ExceptionHandlerResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        ex.getMessage(),
                        ex.toString(),
                        CustomerService.class.toString()
                ), HttpStatus.BAD_REQUEST
        );
    }
}
