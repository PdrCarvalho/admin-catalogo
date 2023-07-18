package com.fullcycle.admin.catalogo;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;

class CleanUpExtensions implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {

        final var repositories = SpringExtension
                .getApplicationContext(context)
                .getBeansOfType(CrudRepository.class)
                .values();

        clenUp(repositories);
    }

    private void clenUp(final Collection<CrudRepository> repositories){

        repositories.forEach(CrudRepository::deleteAll);
    }
}