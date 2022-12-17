package org.gorshkovdev.db.images;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
@Repository
public interface ImageEntityRepository extends JpaRepository<ImageEntity, Long> {

  int countByFilename(String filename);
}
