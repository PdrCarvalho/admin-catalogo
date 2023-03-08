package com.fullcycle.admin.catalogo.domain.category;

import java.time.Clock;
import java.time.Instant;
import java.util.Random;
import java.util.UUID;

public class Category {
    private String id;
    private String name;
    private String description;
    private Boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Category(
            String id,
            String name,
            String description,
            Boolean active,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static Category newCategory( String name, String description, Boolean active){
        final var  now = Instant.now();
        return new Category(
                UUID.randomUUID().toString(),
                name,
                description,
                active,
                now,
                now,
                null
        );
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getActive() {
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
