package org.album;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @since 1.0-SNAPSHOT
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "album")
public class AlbumConfiguration {

  private String parentDirs;
  private String[] allowedOrigins;
  private String[] allowedContentTypes;

  @SuppressWarnings("unused")
  @Bean
  public WebMvcConfigurer webMvcConfigurer() {
    return new WebMvcConfigurer() {

      private final static String API_V1_MAPPING = "/api/v1/album/**";

      @Override
      public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping(API_V1_MAPPING).allowedOrigins(allowedOrigins).allowedMethods("*");
      }
    };
  }
}