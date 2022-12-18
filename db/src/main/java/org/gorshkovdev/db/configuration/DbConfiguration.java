package org.gorshkovdev.db.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
@EntityScan(DbConfiguration.ENTITY_SCAN_VALUE)
@EnableJpaRepositories(DbConfiguration.ENABLE_JPA_REPOSITORIES_VALUE)
public class DbConfiguration {

    public static final String ENTITY_SCAN_VALUE = "org.gorshkovdev.db";
    public static final String ENABLE_JPA_REPOSITORIES_VALUE = "org.gorshkovdev.db";

}
