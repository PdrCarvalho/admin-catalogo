package com.fullcycle.admin.catalogo.infrastructure.api.controllers.models;

import com.fullcycle.admin.catalogo.domain.exceptions.DomainException;
import com.fullcycle.admin.catalogo.domain.validation.Error;
import java.util.List;

public record ApiError(String message, List<Error> errors) {
  public static ApiError from(final DomainException ex) {
    return new ApiError(ex.getMessage(), ex.getErrors());
  }
}
