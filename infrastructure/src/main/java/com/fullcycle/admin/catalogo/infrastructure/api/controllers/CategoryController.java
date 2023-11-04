package com.fullcycle.admin.catalogo.infrastructure.api.controllers;

import com.fullcycle.admin.catalogo.application.category.create.CreateCategoryCommand;
import com.fullcycle.admin.catalogo.application.category.create.CreateCategoryUseCase;
import com.fullcycle.admin.catalogo.application.category.retrieve.get.GetCategoryByIdUseCase;
import com.fullcycle.admin.catalogo.domain.pagination.Pagination;
import com.fullcycle.admin.catalogo.infrastructure.api.CategoryApi;
import com.fullcycle.admin.catalogo.infrastructure.category.models.CategoryApiOutput;
import com.fullcycle.admin.catalogo.infrastructure.category.models.CreateCategoryApiInput;
import com.fullcycle.admin.catalogo.infrastructure.category.presenters.CategoryApiPresenter;
import java.net.URI;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController implements CategoryApi {

  private final CreateCategoryUseCase createCategoryUseCase;

  private final GetCategoryByIdUseCase getCategoryByIdUseCase;

  public CategoryController(
      final CreateCategoryUseCase createCategoryUseCase,
      final GetCategoryByIdUseCase getCategoryByIdUseCase) {
    this.createCategoryUseCase = Objects.requireNonNull(createCategoryUseCase);
    this.getCategoryByIdUseCase = Objects.requireNonNull(getCategoryByIdUseCase);
  }

  @Override
  public ResponseEntity<?> createCategory(CreateCategoryApiInput input) {

    final var command =
        CreateCategoryCommand.with(
            input.name(), input.description(), input.active() != null ? input.active() : true);

    return this.createCategoryUseCase
        .execute(command)
        .fold(
            notification -> ResponseEntity.unprocessableEntity().body(notification),
            output ->
                ResponseEntity.created(URI.create("/categories/" + output.id())).body(output));
  }

  @Override
  public Pagination<?> listCategories(
      String search, int page, int perPage, String sort, String direction) {
    return null;
  }

  @Override
  public CategoryApiOutput getCategoryById(String id) {
    return CategoryApiPresenter.present(this.getCategoryByIdUseCase.execute(id));
  }
}
