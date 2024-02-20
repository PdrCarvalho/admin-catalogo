package com.fullcycle.admin.catalogo.e2e.category;

import com.fullcycle.admin.catalogo.E2ETest;
import com.fullcycle.admin.catalogo.domain.category.CategoryID;
import com.fullcycle.admin.catalogo.infrastructure.category.models.CategoryResponse;
import com.fullcycle.admin.catalogo.infrastructure.category.models.CreateCategoryRequest;
import com.fullcycle.admin.catalogo.infrastructure.category.persistence.CategoryRepository;
import com.fullcycle.admin.catalogo.infrastructure.configuration.json.Json;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@E2ETest
@Testcontainers
public class CategoryE2ETest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @Container
    private static final MySQLContainer MY_SQL_CONTAINER =
            new MySQLContainer("mysql:8.2.0")
                    .withPassword("123456")
                    .withUsername("root")
                    .withDatabaseName("adm_videos");

    @DynamicPropertySource
    public static void setDataSourceProperties(final DynamicPropertyRegistry registry) {
        final var mappedPort = MY_SQL_CONTAINER.getMappedPort(3306);
        System.out.printf("Container is running on port %s\n", mappedPort);
        registry.add("mysql.port", () -> mappedPort);
    }

    @Test
    public void asCatalogAdminIShouldBeAbleToCreateANewCategoryWithValidValues() throws Exception {
        Assertions.assertTrue(MY_SQL_CONTAINER.isRunning());
        Assertions.assertEquals(0, categoryRepository.count());

        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var actualId = givenCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualCategory = retrieveCategory(actualId.getValue());

        Assertions.assertEquals(expectedName, actualCategory.name());
        Assertions.assertEquals(expectedDescription, actualCategory.description());
        Assertions.assertEquals(expectedIsActive, actualCategory.active());
        Assertions.assertNotNull(actualCategory.createdAt());
        Assertions.assertNotNull(actualCategory.updatedAt());
        Assertions.assertNull(actualCategory.deletedAt());
    }

    private CategoryID givenCategory(final String name, final String description, final boolean active) throws Exception {
        final var requestBody = new CreateCategoryRequest(name, description, active);

        final var request = MockMvcRequestBuilders.post("/categories")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(Json.writeValueAsString(requestBody));
        final var currentId = this.mvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse().getHeader("Location")
                .replace("/categories/", "");

        return CategoryID.from(currentId);
    }

    private CategoryResponse retrieveCategory(final String id) throws Exception {

        final var request = MockMvcRequestBuilders
                .get("/categories/" + id)
                .accept(APPLICATION_JSON);

        final var json = this.mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return Json.readValue(json, CategoryResponse.class);
    }
}
