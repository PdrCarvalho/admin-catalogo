package com.fullcycle.admin.catalogo.application.category.update;

import com.fullcycle.admin.catalogo.domain.category.Category;
import com.fullcycle.admin.catalogo.domain.category.CategoryID;

public record UpdateCategoryOutput(String id) {

  public static UpdateCategoryOutput from(final Category category) {
    return new UpdateCategoryOutput(category.getId().getValue());
  }

  public static UpdateCategoryOutput from(final String id) {
    return new UpdateCategoryOutput(id);
  }
}
