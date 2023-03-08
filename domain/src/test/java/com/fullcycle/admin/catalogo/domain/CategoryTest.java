package com.fullcycle.admin.catalogo.domain;

import com.fullcycle.admin.catalogo.domain.category.Category;
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
}
