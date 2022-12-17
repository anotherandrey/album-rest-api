package org.gorshkovdev.openapi;

import lombok.*;
import org.gorshkovdev.db.configuration.DbConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
@Getter
@Setter
@Configuration
@Import(DbConfiguration.class)
public class WebMvcConfiguration {

  @Value("${web.mvc.allowed-origins}")
  private String[] allowedOrigins;

  @Value("${web.mvc.allowed-methods}")
  private String[] allowedMethods;

  @SuppressWarnings("unused")
  @Bean
  public WebMvcConfigurer webMvcConfigurer() {
    return new WebMvcConfigurer() {

      private final static String API_V1_MAPPING = "/api/v1/**";

      @Override
      public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping(API_V1_MAPPING)
            .allowedOrigins(allowedOrigins)
            .allowedMethods(allowedMethods);
      }
    };
  }
}
