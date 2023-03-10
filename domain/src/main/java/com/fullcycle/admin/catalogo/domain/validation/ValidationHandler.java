package com.fullcycle.admin.catalogo.domain.validation;

import java.util.List;

public interface ValidationHandler {
    ValidationHandler append(Error error);

    ValidationHandler append(ValidationHandler handler);

    List<Error> getErrors();
    default boolean hasError(){
        return getErrors() != null &&  !getErrors().isEmpty();
    }

    ValidationHandler validate(Validation validation);

    interface Validation{
        void validate();
    }
}
