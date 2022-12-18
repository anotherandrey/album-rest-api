package org.gorshkovdev.service.impl.images;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.*;
import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.*;
import org.gorshkovdev.service.configuration.ImageConfiguration;
import org.gorshkovdev.service.exception.*;
import org.gorshkovdev.service.images.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.*;
import org.springframework.util.Base64Utils;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
@Slf4j
@SpringBootTest
class ImageResourceServiceTest {

  @Autowired
  private ImageResourceService resourceService;

  // mocks

  @MockBean
  private ImageCrudService crudService;

  @MockBean
  private ImageConfiguration configuration;

  @BeforeEach
  void beforeEach() {
    doReturn(StringUtils.EMPTY).when(configuration).getParentDirectories();
  }

  @Test
  void getResource_shouldGetResource() throws IOException {
    Image image = buildImage();

    long id = image.getId();
    String filename = image.getFilename();

    doReturn(image).when(crudService).getImageById(id);

    byte[] byteArray = {42, 42, 42, 42, 42, 42};
    File file = new File(filename);
    FileUtils.writeByteArrayToFile(file, byteArray);

    String path = file.getPath();

    ImageResourceServiceResponse<Resource> expected = new ImageResourceServiceResponse<>(image, new PathResource(path));
    assertThat(resourceService.getResource(id)).isEqualTo(expected);

    deleteFile(file);
  }

  @Test
  void getResource_shouldThrowsResourceNotExistsException() {
    Image image = buildImage();

    long id = image.getId();

    doReturn(image).when(crudService).getImageById(id);

    assertThatThrownBy(() -> resourceService.getResource(id))
        .isInstanceOf(ResourceNotExistsException.class);
  }

  @Test
  void getResource_shouldThrowsImageNotFoundException() {
    long id = 42L;

    doThrow(ImageNotFoundException.class).when(crudService).getImageById(id);

    assertThatThrownBy(() -> resourceService.getResource(id))
        .isInstanceOf(ImageNotFoundException.class);
  }

  @Test
  void getBase46_shouldGetBase64() throws IOException {
    Image image = buildImage();

    long id = image.getId();
    String filename = image.getFilename();

    doReturn(image).when(crudService).getImageById(id);

    byte[] byteArray = {42, 42, 42, 42, 42, 42};
    File file = new File(filename);
    FileUtils.writeByteArrayToFile(file, byteArray);

    ImageResourceServiceResponse<String> expected = new ImageResourceServiceResponse<>(image,
        Base64Utils.encodeToString(byteArray));
    assertThat(resourceService.getBase64(id)).isEqualTo(expected);

    deleteFile(file);
  }

  @Test
  void getBase64_shouldThrowsResourceNotExistsException() {
    Image image = buildImage();

    long id = image.getId();

    doReturn(image).when(crudService).getImageById(id);

    assertThatThrownBy(() -> resourceService.getResource(id))
        .isInstanceOf(ResourceNotExistsException.class);
  }

  @Test
  void getBase64_shouldThrowsImageNotFoundException() {
    long id = 42L;

    doThrow(ImageNotFoundException.class).when(crudService).getImageById(id);

    assertThatThrownBy(() -> resourceService.getResource(id))
        .isInstanceOf(ImageNotFoundException.class);
  }

  @Test
  void create_shouldCreate() throws IOException {
    Image image = buildImage();

    String filename = image.getFilename();
    String contentType = image.getFilename();

    byte[] allBytes = new byte[]{42, 42, 42, 42, 42, 42};

    InputStream mockInputStream = mock(InputStream.class);
    doReturn(allBytes).when(mockInputStream).readAllBytes();

    Resource mockResource = mock(Resource.class);
    doReturn(mockInputStream).when(mockResource).getInputStream();

    doReturn(new String[]{image.getContentType()}).when(configuration).getContentTypes();
    doReturn(image).when(crudService).createImage(anyString(), anyString(), any(long.class));

    assertThat(resourceService.create(filename, contentType, mockResource)).isEqualTo(image);

    File file = new File(filename);
    try (FileInputStream inputStream = new FileInputStream(file)) {
      assertThat(inputStream.readAllBytes()).isEqualTo(allBytes);
    }

    deleteFile(file);
  }

  @Test
  void create_shouldThrowsNotSupportedContentTypeException() {
  }

  @Test
  void delete_shouldNotDeleteFileIfFilenameIsUsed() throws IOException {
    Image image = buildImage();

    long id = image.getId();
    String filename = image.getFilename();

    doReturn(image).when(crudService).deleteImageById(id);
    doReturn(42).when(crudService).countImagesByFilename(filename);

    byte[] byteArray = {42, 42, 42, 42, 42, 42};
    File file = new File(filename);
    FileUtils.writeByteArrayToFile(file, byteArray);

    resourceService.delete(id);

    assertThat(file.exists()).isTrue();

    deleteFile(file);
  }

  @Test
  void delete_shouldDeleteFileIfFilenameIsUnused() throws IOException {
    Image image = buildImage();

    long id = image.getId();
    String filename = image.getFilename();

    doReturn(image).when(crudService).deleteImageById(id);
    doReturn(0).when(crudService).countImagesByFilename(filename);

    byte[] byteArray = {42, 42, 42, 42, 42, 42};
    File file = new File(filename);
    FileUtils.writeByteArrayToFile(file, byteArray);

    resourceService.delete(id);

    assertThat(file.exists()).isFalse();
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

      @Override
      public String getCreatedAtToString() {
        return Instant.now().toString();
      }
    };
  }

  private void deleteFile(File file) throws IOException {
    if (!file.delete()) {
      String absolutePath = file.getAbsolutePath();
      log.error("can't delete {}", absolutePath);
      throw new IOException();
    }
  }
}
