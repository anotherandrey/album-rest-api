package org.album.service;

import static com.google.common.io.Files.*;
import static org.springframework.util.Base64Utils.encodeToString;

import java.io.*;
import lombok.RequiredArgsConstructor;
import org.album.AlbumConfiguration;
import org.album.exception.*;
import org.album.image.Image;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @since 1.0-SNAPSHOT
 */
@RequiredArgsConstructor
@Service
public class AlbumCommonServiceImpl implements AlbumCommonService {

  private final AlbumConfiguration configuration;
  private final AlbumCrudService crudService;

  @Transactional(readOnly = true)
  @Override
  public Response<Resource> getResource(long id) {
    Image image = crudService.getImage(id);

    String filename = image.getFilename();

    File file = getFile(filename);
    if (!file.exists()) {
      throw new FileNotExistsException(file);
    }

    Resource body = new PathResource(file.getPath());

    try {
      image.setContentLength(body.contentLength());
    } catch (IOException e) {
      throw new AlbumCommonServiceException(e);
    }

    return new Response<>(image, body);
  }

  @Transactional(readOnly = true)
  @Override
  public Response<String> getBase64(long id) {
    Image image = crudService.getImage(id);

    String filename = image.getFilename();

    File file = getFile(filename);
    if (!file.exists()) {
      throw new FileNotExistsException(file);
    }

    try (FileInputStream inputStream = new FileInputStream(file)) {
      byte[] allBytes = inputStream.readAllBytes();

      image.setContentLength((long) allBytes.length);

      return new Response<>(image, encodeToString(allBytes));

    } catch (IOException e) {
      throw new AlbumCommonServiceException(e);
    }
  }

  @Transactional
  @Override
  public Image create(String filename, String contentType, Resource resource) {
    assert !StringUtils.isEmpty(filename) && !StringUtils.isEmpty(contentType);

    checkContentType(contentType);

    try (InputStream inputStream = resource.getInputStream()) {

      String parentDirs = configuration.getParentDirs();
      createParentDirs(new File(parentDirs));

      File file = getFile(filename);

      byte[] allBytes = inputStream.readAllBytes();

      write(allBytes, file);

      return crudService.createImage(filename, contentType, resource.contentLength());

    } catch (IOException e) {
      throw new AlbumCommonServiceException(e);
    }
  }

  @Transactional
  @Override
  public void delete(long id) {
    Image image = crudService.getImage(id);

    crudService.deleteImage(image);
  }

  protected File getFile(String filename) {
    return new File(configuration.getParentDirs() + "/" + filename);
  }

  protected void checkContentType(String contentType) {
    boolean isNotAllowed = !StringUtils.equalsAny(contentType, configuration.getAllowedContentTypes());
    if (isNotAllowed) {
      throw new NotAllowedContentTypeException(contentType);
    }
  }
}
