package com.fullcycle.admin.catalogo.infrastructure.api.controllers;

import com.fullcycle.admin.catalogo.domain.exceptions.DomainException;
import com.fullcycle.admin.catalogo.domain.exceptions.NotFoundException;
import com.fullcycle.admin.catalogo.domain.validation.Error;
import java.util.List;

import com.fullcycle.admin.catalogo.infrastructure.api.controllers.models.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = NotFoundException.class)
  public ResponseEntity<?> handleNotFoundException(final NotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiError.from(ex));
  }

  @ExceptionHandler(value = DomainException.class)
  public ResponseEntity<?> handleDomainException(final DomainException ex) {
    return ResponseEntity.unprocessableEntity().body(ApiError.from(ex));
  }
}
