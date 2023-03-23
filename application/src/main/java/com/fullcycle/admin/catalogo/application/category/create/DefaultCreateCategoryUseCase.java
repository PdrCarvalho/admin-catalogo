package com.fullcycle.admin.catalogo.application.category.create;

import com.fullcycle.admin.catalogo.domain.category.Category;
import com.fullcycle.admin.catalogo.domain.category.CategoryGateway;
import com.fullcycle.admin.catalogo.domain.validation.handler.Notification;
import com.fullcycle.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.Try;
import static io.vavr.control.Either.left;
import static io.vavr.control.Either.right;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(final  CategoryGateway categoryGateway){

        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }
    @Override
    public Either<Notification,CreateCategoryOutput> execute(CreateCategoryCommand command) {
        final var notification = Notification.create();
        final var category = Category.newCategory(
                command.name(),
                command.description(),
                command.isActive()
        );
        category.validate(notification);

        return notification.hasError()? left(notification): create(category);
    }

    private Either<Notification,CreateCategoryOutput> create(final Category category) {

        return Try(()->  this.categoryGateway.create(category))
                .toEither()
                .bimap(Notification::create,CreateCategoryOutput::from);
    }
}
