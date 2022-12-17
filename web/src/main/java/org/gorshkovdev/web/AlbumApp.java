package org.gorshkovdev.web;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
@SpringBootApplication
public class AlbumApp {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(AlbumApp.class);

    app.setBannerMode(Banner.Mode.OFF);

    app.run(args);
  }
}