package com.fullcycle.admin.catalogo.infrastructure.category.presenters;

import com.fullcycle.admin.catalogo.application.category.retrieve.get.CategoryOutput;
import com.fullcycle.admin.catalogo.application.category.retrieve.list.CategoryListOutput;
import com.fullcycle.admin.catalogo.infrastructure.category.models.CategoryListResponse;
import com.fullcycle.admin.catalogo.infrastructure.category.models.CategoryResponse;

public interface CategoryApiPresenter {

  static CategoryResponse present(final CategoryOutput category) {
    return new CategoryResponse(
        category.id().getValue(),
        category.name(),
        category.description(),
        category.isActive(),
        category.createdAt(),
        category.updatedAt(),
        category.deletedAt());
  }

  static CategoryListResponse present(final CategoryListOutput category) {
    return new CategoryListResponse(
        category.id().getValue(),
        category.name(),
        category.description(),
        category.isActive(),
        category.createdAt(),
        category.deletedAt());
  }
}
