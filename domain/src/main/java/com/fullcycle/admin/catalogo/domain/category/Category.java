package com.fullcycle.admin.catalogo.domain.category;

import com.fullcycle.admin.catalogo.domain.AggregateRoot;
import com.fullcycle.admin.catalogo.domain.validation.ValidationHandler;

import java.time.Instant;

public class Category extends AggregateRoot<CategoryID> {
    private String name;
    private String description;
    private Boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Category(
            CategoryID id,
            String name,
            String description,
            Boolean active,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        super(id);
        this.name = name;
        this.description = description;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static Category newCategory(String name, String description, Boolean active) {
        final var now = Instant.now();
        final var deletedAt = active?null:now;
        return new Category(
                CategoryID.unique(),
                name,
                description,
                active,
                now,
                now,
                deletedAt
        );
    }

    @Override
    public void validate(ValidationHandler handler) {
        new CategoryValidator(this, handler).validate();
    }

    public Category update(
            final String name,
            final String description,
            final boolean isActive
    ){
        if(isActive){
            activate();
        }else {
            deactivate();
        }
        this.name = name;
        this.description = description;
        this.updatedAt = Instant.now();
        return this;
    }
    public Category deactivate(){
    if(getDeletedAt() == null){
        this.deletedAt = Instant.now();
    }
    this.active = false;
    this.updatedAt = Instant.now();
    return this;
    }

    public Category activate(){
        this.deletedAt = null;
        this.active = true;
        this.updatedAt = Instant.now();
        return this;
    }

    public CategoryID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean isActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }
}
