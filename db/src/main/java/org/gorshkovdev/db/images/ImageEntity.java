package org.gorshkovdev.db.images;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.Data;
import org.gorshkovdev.service.images.Image;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
@Entity(name = ImageEntity.ENTITY_NAME)
@Table(name = ImageEntity.TABLE_NAME)
@SequenceGenerator(name = "image_id_sequence", allocationSize = 1)
@Data
public class ImageEntity implements Image {

  final static String ENTITY_NAME = "";
  final static String TABLE_NAME = "image";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_id_sequence")
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "filename", nullable = false)
  private String filename;

  @Column(name = "content_type", nullable = false)
  private String contentType;

  @Column(name = "content_length", nullable = false)
  private Long contentLength;

  @Column(name = "created_at")
  private Instant createdAt = Instant.now();

  public ImageEntity() {
  }
}
