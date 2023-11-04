package com.fullcycle.admin.catalogo.domain.validation.handler;

import com.fullcycle.admin.catalogo.domain.exceptions.DomainException;
import com.fullcycle.admin.catalogo.domain.validation.Error;
import com.fullcycle.admin.catalogo.domain.validation.ValidationHandler;
import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {

  @Override
  public ValidationHandler append(Error error) {
    throw DomainException.with(error);
  }

  @Override
  public ValidationHandler append(ValidationHandler handler) {
    return null;
  }

  @Override
  public ValidationHandler validate(final Validation validation) {
    try {
      validation.validate();
    } catch (final Exception ex) {
      throw DomainException.with(new Error(ex.getMessage()));
    }
    return this;
  }

  @Override
  public List<Error> getErrors() {
    return null;
  }
}
