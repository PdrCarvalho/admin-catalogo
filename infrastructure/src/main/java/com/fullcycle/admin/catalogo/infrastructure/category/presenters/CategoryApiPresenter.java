package com.fullcycle.admin.catalogo.infrastructure.category.presenters;

import com.fullcycle.admin.catalogo.application.category.retrieve.get.CategoryOutput;
import com.fullcycle.admin.catalogo.infrastructure.category.models.CategoryApiOutput;

public interface CategoryApiPresenter {

  static CategoryApiOutput present(final CategoryOutput category) {
    return new CategoryApiOutput(
        category.id().getValue(),
        category.name(),
        category.description(),
        category.isActive(),
        category.createdAt(),
        category.updatedAt(),
        category.deletedAt());
  }
}
