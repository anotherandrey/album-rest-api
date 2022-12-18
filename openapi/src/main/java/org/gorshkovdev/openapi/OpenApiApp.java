package org.gorshkovdev.openapi;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
@SpringBootApplication(scanBasePackages = OpenApiApp.SCAN_BASE_PACKAGES)
public class OpenApiApp {

  public static final String SCAN_BASE_PACKAGES = "org.gorshkovdev";

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(OpenApiApp.class);
    app.setBannerMode(Banner.Mode.OFF);
    app.run(args);
  }
}
