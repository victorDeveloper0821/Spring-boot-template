package idv.victor.sideproject.system.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Spring Data Jpa 設定
 */
@Configuration
@EntityScan(basePackages = "idv.victor.sideproject.*.domain.entity")
@EnableJpaRepositories(basePackages = "idv.victor.sideproject.*.repository")
public class JpaConfig {

}
