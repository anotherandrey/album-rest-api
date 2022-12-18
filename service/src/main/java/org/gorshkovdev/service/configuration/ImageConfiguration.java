package org.gorshkovdev.service.configuration;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**v
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
@Getter
@Setter
@Configuration
public class ImageConfiguration {

  @Value("${images.parent-directories}")
  private String parentDirectories;

  @Value("${images.content-type}")
  private String[] contentTypes;
}