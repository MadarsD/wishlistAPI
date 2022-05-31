package com.example.databaseconfiguration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

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
                .url("jdbc:postgresql://localhost:5432/postgres")
                .username("postgres")
                .password("password")
                .build();

    }

}
