package org.gorshkovdev.db.images;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

import java.time.Instant;
import org.apache.commons.lang3.RandomStringUtils;
import org.gorshkovdev.service.exception.ImageNotFoundException;
import org.gorshkovdev.service.images.Image;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
@SpringBootTest
class ImageEntityCrudServiceTest {

  @Autowired
  private ImageEntityCrudService crudService;

  // mocks

  @MockBean
  private ImageEntityRepository repository;

  @AfterEach
  void afterEach() {
    repository.deleteAll();
  }

  @Test
  void getImage_shouldThrowsImageNotFoundException() {
    assertThatThrownBy(() -> crudService.getImageById(42L)).isInstanceOf(ImageNotFoundException.class);
  }

  @Test
  void createImage_shouldCreateImage() {
    Image expected = buildImage();

    doReturn(expected).when(repository).findById(expected.getId());

    Image actual = crudService.createImage(expected.getFilename(), expected.getContentType(), expected.getContentLength());

    assertThat(actual.getId()).isEqualTo(expected.getId());
    assertThat(actual.getFilename()).isEqualTo(expected.getFilename());
    assertThat(actual.getContentType()).isEqualTo(expected.getContentType());
    assertThat(actual.getContentLength()).isEqualTo(expected.getContentLength());
    assertThat(actual.getCreatedAt()).isEqualTo(expected.getCreatedAt());
  }

  private Image buildImage() {
    return new Image() {

      @Override
      public Long getId() {
        return 42L;
      }

      @Override
      public String getFilename() {
        return RandomStringUtils.random(42);
      }

      @Override
      public String getContentType() {
        return RandomStringUtils.random(42);
      }

      @Override
      public Long getContentLength() {
        return 42L;
      }

      @Override
      public Instant getCreatedAt() {
        return Instant.now();
      }
    };
  }
}
