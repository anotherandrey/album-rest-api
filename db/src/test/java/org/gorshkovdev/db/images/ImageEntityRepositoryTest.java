package org.gorshkovdev.db.images;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ImageEntityRepositoryTest {

  @Autowired
  private ImageEntityRepository repository;

  @AfterEach
  void afterEach() {
    repository.deleteAll();
  }

  @Test
  void findByFilename_shouldFindByFilename() {
    String filename = RandomStringUtils.random(42);

    ImageEntity entity = new ImageEntity();
    entity.setId(null);
    entity.setFilename(filename);
    entity.setContentType(RandomStringUtils.random(42));
    entity.setContentLength(42L);

    repository.save(entity);

    int count = repository.countByFilename(filename);
    assertThat(count).isEqualTo(1);
  }
}
