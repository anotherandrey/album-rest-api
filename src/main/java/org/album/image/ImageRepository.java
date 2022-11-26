package org.album.image;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @since 1.0-SNAPSHOT
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

  List<Image> findAllByFilename(String filename);
}
