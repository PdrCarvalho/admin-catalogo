package com.fullcycle.admin.catalogo.infrastructure.configuration.json;

import static com.fasterxml.jackson.databind.DeserializationFeature.*;
import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SNAKE_CASE;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import java.util.concurrent.Callable;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

public enum Json {
  INSTANCE;

  public static ObjectMapper mapper() {
    return INSTANCE.mapper.copy();
  }

  public static String writeValueAsString(final Object obj) {
    return invoke(() -> INSTANCE.mapper.writeValueAsString(obj));
  }

  public static <T> T readValue(final String json, final Class<T> tClass) {
    return invoke(() -> INSTANCE.mapper.readValue(json, tClass));
  }

  private final ObjectMapper mapper =
      new Jackson2ObjectMapperBuilder()
          .dateFormat(new StdDateFormat())
          .featuresToDisable(
              FAIL_ON_UNKNOWN_PROPERTIES,
              FAIL_ON_NULL_FOR_PRIMITIVES,
              FAIL_ON_NULL_CREATOR_PROPERTIES,
              WRITE_DATES_AS_TIMESTAMPS)
          .modules(new JavaTimeModule(), new Jdk8Module(), afterBurnerModule())
          .propertyNamingStrategy(SNAKE_CASE)
          .build();

  private AfterburnerModule afterBurnerModule() {
    var module = new AfterburnerModule();
    module.setUseValueClassLoader(false);
    return module;
  }

  private static <T> T invoke(final Callable<T> callable) {
    try {
      return callable.call();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
