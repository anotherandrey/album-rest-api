package org.album;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @since 1.0-SNAPSHOT
 */
@SpringBootApplication
public class AlbumApp {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(AlbumApp.class);

    app.setBannerMode(Banner.Mode.OFF);

    app.run(args);
  }
}
