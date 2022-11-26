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
    Image expected = Image.builder()
        .id(42L).filename(RandomStringUtils.random(42)).contentType(RandomStringUtils.random(42)).contentLength(42L)
        .build();

    Mockito.when(repository.save(Mockito.any(Image.class))).thenReturn(expected);

    Image image = crudService.createImage(expected.getFilename(), expected.getContentType(), expected.getContentLength());

    Assertions.assertThat(image.getId()).isEqualTo(expected.getId());
    Assertions.assertThat(image.getFilename()).isEqualTo(expected.getFilename());
    Assertions.assertThat(image.getContentType()).isEqualTo(expected.getContentType());
    Assertions.assertThat(image.getContentLength()).isEqualTo(expected.getContentLength());
    Assertions.assertThat(image.getCreatedAt()).isEqualTo(image.getCreatedAt());
  }
}
