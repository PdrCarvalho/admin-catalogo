package com.fullcycle.admin.catalogo;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.springframework.context.annotation.FilterType.REGEX;

@Target(value = TYPE)
@Retention(value = RUNTIME)
@Inherited
@ActiveProfiles("test-integration")
@DataJpaTest
@ComponentScan(
        includeFilters = {
                @ComponentScan.Filter(type = REGEX, pattern = ".[MySQLGateway]")
        }
)
@ExtendWith(CleanUpExtensions.class)
public @interface MySQLGatewayTest {
}
