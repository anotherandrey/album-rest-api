package org.album.image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @since 1.0-SNAPSHOT
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

}
