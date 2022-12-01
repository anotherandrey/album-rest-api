package org.album.image;

import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * @since 1.0-SNAPSHOT
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ImageRepositoryTest {

  @Autowired
  private ImageRepository repository;

  @AfterEach
  void afterEach() {
    repository.deleteAll();
  }

  @Test
  void findByFilename_shouldFindByFilename() {
    String filename = RandomStringUtils.random(42);

    Image image = Image.builder()
        .id(null)
        .filename(filename)
        .contentType(RandomStringUtils.random(42))
        .contentLength(42L)
        .build();

    repository.save(image);

    int count = repository.countByFilename(filename);
    Assertions.assertThat(count).isEqualTo(1);
  }
}
