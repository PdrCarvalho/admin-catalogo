package com.fullcycle.admin.catalogo.application.category.create;

import com.fullcycle.admin.catalogo.domain.category.Category;
import com.fullcycle.admin.catalogo.domain.category.CategoryGateway;
import com.fullcycle.admin.catalogo.domain.validation.handler.Notification;
import com.fullcycle.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;
import io.vavr.control.Either;

import java.util.Objects;

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

        if(notification.hasError()){
            //
        }
        return CreateCategoryOutput.from(this.categoryGateway.create(category));
    }
}
