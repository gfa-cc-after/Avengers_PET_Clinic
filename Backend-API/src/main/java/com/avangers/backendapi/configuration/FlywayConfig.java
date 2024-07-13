package com.avangers.backendapi.configuration;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.password}")
    private String datasourcePassword;

    @Value("${spring.datasource.username}")
    private String datasourceUsername;

    @Bean
    FlywayMigrationStrategy cleanMigrateStrategy() {
        return Flyway::migrate;
    }

    @Bean
    @FlywayDataSource
    Flyway flyway() {
        return Flyway.configure().dataSource(datasourceUrl, datasourceUsername, datasourcePassword).load();
    }

    @Bean
    FlywayMigrationInitializer flywayInitializer(Flyway flyway) {
        return new FlywayMigrationInitializer(flyway);
    }

}
