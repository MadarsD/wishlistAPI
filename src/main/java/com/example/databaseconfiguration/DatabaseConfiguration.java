package com.example.databaseconfiguration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    private org.springframework.core.env.Environment environment;

    public DatabaseConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    @ConditionalOnProperty(prefix = "wish-list", name = "store-type", havingValue = "H2Database")
    public DataSource getDatabaseDataSourceH2() {
        return DataSourceBuilder.create()
                .driverClassName("org.h2.Driver")
                .url("jdbc:h2:mem:mydb")
                .username("sa")
                .password("")
                .build();
    }

    @Bean
    @ConditionalOnProperty(prefix = "wish-list", name = "store-type", havingValue = "postgresql")
    public DataSource getDatabaseDataSourcePostgres() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url(environment.getProperty("spring.datasource.url"))
                .username(environment.getProperty("spring.datasource.username"))
                .password(environment.getProperty("spring.datasource.password"))
                .build();

    }

}
