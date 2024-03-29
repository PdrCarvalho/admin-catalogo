package com.fullcycle.admin.catalogo.domain.category;

import com.fullcycle.admin.catalogo.domain.validation.Error;
import com.fullcycle.admin.catalogo.domain.validation.ValidationHandler;
import com.fullcycle.admin.catalogo.domain.validation.Validator;

public class CategoryValidator extends Validator {

  public static final int NAME_MAX_LENGTH = 255;
  public static final int NAME_MIN_LENGTH = 3;
  private final Category category;

  public CategoryValidator(final Category category, final ValidationHandler handler) {
    super(handler);
    this.category = category;
  }

  @Override
  public void validate() {
    checkNameConstraints();
  }

  private void checkNameConstraints() {
    final var name = this.category.getName();
    if (name == null) {
      this.validationHandler().append(new Error("Name should not be null"));
      return;
    }

    if (name.isBlank() || name.isEmpty()) {
      this.validationHandler().append(new Error("Name should not be empty"));
      return;
    }

    final int length = name.trim().length();

    if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
      this.validationHandler().append(new Error("Name must be between 3 and 255 characters"));
    }
  }
}
