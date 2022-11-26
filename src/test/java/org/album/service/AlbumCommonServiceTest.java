package org.album.service;

import static com.google.common.io.Files.write;
import static org.springframework.util.Base64Utils.encodeToString;

import java.io.*;
import lombok.extern.slf4j.Slf4j;
import org.album.AlbumConfiguration;
import org.album.exception.*;
import org.album.image.Image;
import org.album.service.AlbumCommonService.Response;
import org.apache.commons.lang3.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.*;

/**
 * @since 1.0-SNAPSHOT
 */
@Slf4j
@SpringBootTest
class AlbumCommonServiceTest {

  @Autowired
  private AlbumCommonService commonService;

  // mocks

  @MockBean
  private AlbumConfiguration configuration;

  @MockBean
  private AlbumCrudService crudService;

  @BeforeEach
  void beforeEach() {
    Mockito.when(configuration.getParentDirs()).thenReturn(StringUtils.EMPTY);
  }

  @Test
  void getResource_shouldGetResource() throws IOException {
    Image image = buildImage();
    Mockito.when(crudService.getImage(image.getId())).thenReturn(image);

    byte[] bytes = {42, 42, 42, 42, 42, 42};
    File file = new File(image.getFilename());
    write(bytes, file);

    Response<Resource> expected = new Response<>(image, new PathResource(file.getPath()));
    Assertions.assertThat(commonService.getResource(image.getId())).isEqualTo(expected);

    deleteFile(file);
  }

  @Test
  void getResource_shouldThrowsResourceNotExistsException() {
    Image image = buildImage();
    Mockito.when(crudService.getImage(image.getId())).thenReturn(image);
    Assertions.assertThatThrownBy(() -> commonService.getResource(image.getId())).isInstanceOf(ResourceNotExistsException.class);
  }

  @Test
  void getResource_shouldThrowsImageNotFoundException() {
    Mockito.when(crudService.getImage(42L)).thenThrow(ImageNotFoundException.class);
    Assertions.assertThatThrownBy(() -> commonService.getResource(42L)).isInstanceOf(ImageNotFoundException.class);
  }

  @Test
  void getBase46_shouldGetBase64() throws IOException {
    Image image = buildImage();
    Mockito.when(crudService.getImage(image.getId())).thenReturn(image);

    byte[] bytes = {42, 42, 42, 42, 42, 42};
    File file = new File(image.getFilename());
    write(bytes, file);

    Response<String> expected = new Response<>(image, encodeToString(bytes));
    Assertions.assertThat(commonService.getBase64(image.getId())).isEqualTo(expected);

    deleteFile(file);
  }

  @Test
  void getBase64_shouldThrowsResourceNotExistsException() {
    Image image = buildImage();
    Mockito.when(crudService.getImage(image.getId())).thenReturn(image);
    Assertions.assertThatThrownBy(() -> commonService.getResource(image.getId())).isInstanceOf(ResourceNotExistsException.class);
  }

  @Test
  void getBase64_shouldThrowsImageNotFoundException() {
    Mockito.when(crudService.getImage(42L)).thenThrow(ImageNotFoundException.class);
    Assertions.assertThatThrownBy(() -> commonService.getResource(42L)).isInstanceOf(ImageNotFoundException.class);
  }

  @Test
  void create_shouldCreate() throws IOException {
    Image image = buildImage();
    byte[] allBytes = new byte[]{42, 42, 42, 42, 42, 42};

    InputStream mockInputStream = Mockito.mock(InputStream.class);
    Mockito.when(mockInputStream.readAllBytes()).thenReturn(allBytes);

    Resource mockResource = Mockito.mock(Resource.class);
    Mockito.when(mockResource.getInputStream()).thenReturn(mockInputStream);

    Mockito.when(configuration.getContentTypes()).thenReturn(new String[]{image.getContentType()});
    Mockito.when(crudService.createImage(Mockito.anyString(), Mockito.anyString(), Mockito.any(Long.class))).thenReturn(image);

    Assertions.assertThat(commonService.create(image.getFilename(), image.getContentType(), mockResource)).isEqualTo(image);

    File file = new File(image.getFilename());
    try (FileInputStream inputStream = new FileInputStream(file)) {
      Assertions.assertThat(inputStream.readAllBytes()).isEqualTo(allBytes);
    }

    deleteFile(file);
  }

  @Test
  void create_shouldThrowsNotSupportedContentTypeException() {
    Assertions.assertThatThrownBy(() -> commonService.create(RandomStringUtils.random(42), RandomStringUtils.random(42), null)).isInstanceOf(NotSupportedContentTypeException.class);
  }

  private Image buildImage() {
    return Image.builder()
        .id(42L).filename(RandomStringUtils.random(42)).contentType(RandomStringUtils.random(42)).contentLength(42L)
        .build();
  }

  private void deleteFile(File file) throws IOException {
    if (!file.delete()) {
      log.error("can't delete {}", file.getAbsolutePath());
      throw new IOException();
    }
  }
}
