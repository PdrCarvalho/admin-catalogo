package com.fullcycle.admin.catalogo.application.category.retrieve.get;

import com.fullcycle.admin.catalogo.domain.category.CategoryGateway;
import com.fullcycle.admin.catalogo.domain.category.CategoryID;
import com.fullcycle.admin.catalogo.domain.exceptions.DomainException;
import com.fullcycle.admin.catalogo.domain.validation.Error;
import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetCategoryByIdUseCase extends GetCategoryByIdUseCase {

  private final CategoryGateway categoryGateway;

  public DefaultGetCategoryByIdUseCase(final CategoryGateway categoryGateway) {
    this.categoryGateway = Objects.requireNonNull(categoryGateway);
  }

  @Override
  public CategoryOutput execute(String id) {
    return categoryGateway
        .findById(CategoryID.from(id))
        .map(CategoryOutput::from)
        .orElseThrow(notFound(CategoryID.from(id)));
  }

  private Supplier<DomainException> notFound(final CategoryID id) {
    return () ->
        DomainException.with(
            new Error("Category with ID %s was not found".formatted(id.getValue())));
  }
}
