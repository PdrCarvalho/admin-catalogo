package com.fullcycle.admin.catalogo.application.category.update;

import com.fullcycle.admin.catalogo.domain.category.Category;
import com.fullcycle.admin.catalogo.domain.category.CategoryGateway;
import com.fullcycle.admin.catalogo.domain.category.CategoryID;
import com.fullcycle.admin.catalogo.domain.exceptions.DomainException;
import com.fullcycle.admin.catalogo.domain.validation.Error;
import com.fullcycle.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.Supplier;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class DefaultUpdateCategoryUseCase extends UpdateCategoryUseCase {
    private final CategoryGateway categoryGateway;

    public DefaultUpdateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Either<Notification, UpdateCategoryOutput> execute(final UpdateCategoryCommand command) {
        final var id = CategoryID.from(command.id());
        final var category = categoryGateway.findById(id)
                .orElseThrow(notFound(id));
        final var notification = Notification.create();
        category.update(
                command.name(),
                command.description(),
                command.isActive()
        ).validate(notification);

        return notification.hasError()? Left(notification): update(category);
    }

    private Either<Notification,UpdateCategoryOutput> update(final Category category){
        return Try(()-> this.categoryGateway.update(category))
                .toEither()
                .bimap(Notification::create,UpdateCategoryOutput::from);
    }

    private Supplier<DomainException> notFound(final CategoryID id){
        return () -> DomainException.with(
                new Error("Category with ID %s was not found".formatted(id.getValue()))
        );
    }
}
