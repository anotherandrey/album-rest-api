package org.album.service;

import org.album.exception.ImageNotFoundException;
import org.album.image.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @since 1.0-SNAPSHOT
 */
@SpringBootTest
class AlbumCrudServiceTest {

  @Autowired
  private AlbumCrudService crudService;

  // mocks

  @MockBean
  private ImageRepository repository;

  @AfterEach
  void afterEach() {
    repository.deleteAll();
  }

  @Test
  void getImage_shouldThrowsImageNotFoundException() {
    Assertions.assertThatThrownBy(() -> crudService.getImage(42L)).isInstanceOf(ImageNotFoundException.class);
  }

  @Test
  void createImage_shouldCreateImage() {
    Image image = crudService.createImage(RandomStringUtils.random(42), RandomStringUtils.random(42), 42L);
    Mockito.when(repository.save(Mockito.any(Image.class))).thenReturn(image);

    Assertions.assertThat(image.getId()).isNotNull();
    Assertions.assertThat(image.getFilename()).isEqualTo(image.getFilename());
    Assertions.assertThat(image.getContentType()).isEqualTo(image.getContentType());
    Assertions.assertThat(image.getContentLength()).isEqualTo(image.getContentLength());
    Assertions.assertThat(image.getCreatedAt()).isNotNull();
  }
}
