package com.fullcycle.admin.catalogo;

import com.fullcycle.admin.catalogo.infrastructure.configuration.WebServerConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(value = TYPE)
@Retention(value = RUNTIME)
@Inherited
@ActiveProfiles("test-e2e")
@SpringBootTest(
        classes = WebServerConfig.class
)
@ExtendWith(CleanUpExtensions.class)
public @interface E2ETest {
}
