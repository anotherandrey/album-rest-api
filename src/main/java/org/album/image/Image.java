package org.album.image;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

/**
 * @since 1.0-SNAPSHOT
 */
@Entity
@SequenceGenerator(name="image_id_sequence", allocationSize=1)
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Image {

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

  @Builder.Default
  @Column(name = "created_at")
  private LocalDateTime createdAt = LocalDateTime.now();

  public Image() {
  }
}
