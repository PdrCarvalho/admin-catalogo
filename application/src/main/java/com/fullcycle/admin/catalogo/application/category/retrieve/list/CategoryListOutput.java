package com.fullcycle.admin.catalogo.application.category.retrieve.list;

import com.fullcycle.admin.catalogo.domain.category.Category;
import com.fullcycle.admin.catalogo.domain.category.CategoryID;
import java.time.Instant;

public record CategoryListOutput(
    CategoryID id,
    String name,
    String description,
    Boolean isActive,
    Instant createdAt,
    Instant deletedAt) {

  public static CategoryListOutput from(final Category category) {
    return new CategoryListOutput(
        category.getId(),
        category.getName(),
        category.getDescription(),
        category.isActive(),
        category.getCreatedAt(),
        category.getDeletedAt());
  }
}
