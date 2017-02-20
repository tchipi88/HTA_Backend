/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ganeo.appli.hta.config;

import com.zaxxer.hikari.HikariDataSource;
import java.util.Arrays;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

/**
 *
 * @author tchipnangngansopa
 */
@Configuration
@EnableJpaRepositories("org.ganeo.appli.hta.repository")
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@EnableTransactionManagement
public class JpaConfig {

    private final Logger log = LoggerFactory.getLogger(JpaConfig.class);

    @Autowired
    private Environment env;

    
    @Bean(destroyMethod = "close")
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource dataSource(DataSourceProperties dataSourceProperties) {
        log.debug("Configuring Datasource");
        if (dataSourceProperties.getUrl() == null) {
            log.error("Your database connection pool configuration is incorrect! The application"
                    + " cannot start. Please check your Spring profile, current profiles are: {}",
                    Arrays.toString(env.getActiveProfiles()));

            throw new ApplicationContextException("Database connection pool is not configured correctly");
        }
        HikariDataSource hikariDataSource = (HikariDataSource) DataSourceBuilder
                .create(dataSourceProperties.getClassLoader())
                .type(HikariDataSource.class)
                .driverClassName(dataSourceProperties.getDriverClassName())
                .url(dataSourceProperties.getUrl())
                .username(dataSourceProperties.getUsername())
                .password(dataSourceProperties.getPassword())
                .build();
       
        return hikariDataSource;
    }

  

    @Bean
    public Hibernate5Module hibernate4Module() {
        return new Hibernate5Module();
    }
}
