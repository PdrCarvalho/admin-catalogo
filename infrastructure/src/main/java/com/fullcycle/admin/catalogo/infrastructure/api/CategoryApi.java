package com.fullcycle.admin.catalogo.infrastructure.api;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fullcycle.admin.catalogo.domain.pagination.Pagination;
import com.fullcycle.admin.catalogo.infrastructure.category.models.CategoryApiOutput;
import com.fullcycle.admin.catalogo.infrastructure.category.models.CreateCategoryApiInput;
import com.fullcycle.admin.catalogo.infrastructure.category.models.UpdateCategoryApiInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "categories")
@Tag(name = "categories")
public interface CategoryApi {

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(value = CREATED)
    @Operation(summary = "Create a new category")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Created successfully"),
                    @ApiResponse(responseCode = "422", description = "Unprocessable error"),
                    @ApiResponse(responseCode = "500", description = "An internal server error"),
            })
    ResponseEntity<?> createCategory(@RequestBody CreateCategoryApiInput input);

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "List all categories paginated")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Listed successfully"),
                    @ApiResponse(responseCode = "422", description = "Invalid param received"),
                    @ApiResponse(responseCode = "500", description = "An internal server error"),
            })
    Pagination<?> listCategories(
            @RequestParam(name = "search", required = false, defaultValue = "") final String search,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "name") final String sort,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") final String direction);

    @GetMapping(value = "{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Get category by Id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Category get successfully"),
                    @ApiResponse(responseCode = "404", description = "Not found category"),
                    @ApiResponse(responseCode = "500", description = "An internal server error"),
            })
    CategoryApiOutput getCategoryById(@PathVariable(name = "id") String id);

    @PutMapping(value = "{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Update category by Id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Category update successfully"),
                    @ApiResponse(responseCode = "404", description = "Not found category"),
                    @ApiResponse(responseCode = "500", description = "An internal server error"),
            })
    ResponseEntity<?> updateCategoryById(
            @PathVariable(name = "id") String id, @RequestBody UpdateCategoryApiInput input);

    @DeleteMapping(value = "{id}")
    @Operation(summary = "Delete category by Id")
    @ResponseStatus(NO_CONTENT)
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Category delete successfully"),
                    @ApiResponse(responseCode = "404", description = "Not found category"),
                    @ApiResponse(responseCode = "500", description = "An internal server error"),
            })
    void deleteCategoryById(
            @PathVariable(name = "id") String id);
}
