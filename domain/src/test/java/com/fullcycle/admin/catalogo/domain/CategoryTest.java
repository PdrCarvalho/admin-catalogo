package com.fullcycle.admin.catalogo.domain;

import com.fullcycle.admin.catalogo.domain.category.Category;
import com.fullcycle.admin.catalogo.domain.exceptions.DomainException;
import com.fullcycle.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {
    @Test
    public void giveAValidParams_whenCallNewCategory_thenInstantiateACategory(){
        final var expectedName = "filme";
        final var expectedDescription = "descricao";
        final var expectedIsActive = true;
        final var actualCategory = Category.newCategory(
                expectedName,
                expectedDescription,
                expectedIsActive
        );

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription,actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive,actualCategory.getActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void shouldCreateCategoryWithEmptyDescription(){
        final var expectedName = "filme";
        final var expectedDescription = "";
        final var expectedIsActive = true;
        final var actualCategory = Category.newCategory(
                expectedName,
                expectedDescription,
                expectedIsActive
        );

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription,actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive,actualCategory.getActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void shouldCreateCategoryWithDeletedAtNotNull(){
        final var expectedName = "filme";
        final var expectedDescription = "describe";
        final var expectedIsActive = false;
        final var actualCategory = Category.newCategory(
                expectedName,
                expectedDescription,
                expectedIsActive
        );

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription,actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive,actualCategory.getActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNotNull(actualCategory.getDeletedAt());
    }

    @Test
    public void shouldValidateNameIsNull(){
        final String expectedName = null;
        final var expectedDescription = "descricao";
        final var expectedIsActive = true;
        final var actualCategory = Category.newCategory(
                expectedName,
                expectedDescription,
                expectedIsActive
        );

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));


        Assertions.assertEquals(1, actualException.getErrors().size());
        Assertions.assertEquals("Name should not be null", actualException.getErrors().get(0).message());
    }
    @Test
    public void shouldValidateNameIsEmpty(){
        final String expectedName = "      ";
        final var expectedDescription = "descricao";
        final var expectedIsActive = true;
        final var actualCategory = Category.newCategory(
                expectedName,
                expectedDescription,
                expectedIsActive
        );

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));


        Assertions.assertEquals(1, actualException.getErrors().size());
        Assertions.assertEquals("Name should not be empty", actualException.getErrors().get(0).message());
    }

    @Test
    public void shouldValidateNameLengthIsCorrect(){
        final String expectedName = "fi ";
        final var expectedDescription = "descricao";
        final var expectedIsActive = true;
        final var actualCategory = Category.newCategory(
                expectedName,
                expectedDescription,
                expectedIsActive
        );

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));


        Assertions.assertEquals(1, actualException.getErrors().size());
        Assertions.assertEquals("Name must be between 3 and 255", actualException.getErrors().get(0).message());
    }
}
