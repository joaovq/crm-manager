package br.com.joaovq.crm.manager.controller;

import br.com.joaovq.crm.manager.core.exception.CustomerNotFoundException;
import br.com.joaovq.crm.manager.core.utils.ExceptionHandlerResponse;
import br.com.joaovq.crm.manager.data.service.CustomerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
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
}
