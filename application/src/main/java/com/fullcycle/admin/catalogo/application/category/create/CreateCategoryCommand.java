package com.fullcycle.admin.catalogo.application.category.create;

public record CreateCategoryCommand(String name, String description, Boolean isActive) {

  public static CreateCategoryCommand with(
      final String name, final String description, final boolean isActive) {
    return new CreateCategoryCommand(name, description, isActive);
  }
}
