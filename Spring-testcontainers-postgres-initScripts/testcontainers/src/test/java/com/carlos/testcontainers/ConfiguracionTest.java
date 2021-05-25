package com.carlos.testcontainers;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@Configuration
@ComponentScan("com.carlos.testcontainers")
public class ConfiguracionTest {

	private static final DockerImageName POSTGRES_TEST_IMAGE = DockerImageName.parse("postgres:10.4");
	private static final PostgreSQLContainer<?> postgres;
	private static final Resource PATH_INIT_SCHEMA = new ClassPathResource("Create_Table.sql");
	private static final Resource PATH_INIT_DATA = new ClassPathResource("Insert_Records.sql");

	static {
		postgres = new PostgreSQLContainer<>(POSTGRES_TEST_IMAGE);
		postgres.start();
	}

	@Bean
	@Profile("integrationTest")
	static public DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl(postgres.getJdbcUrl());
		driverManagerDataSource.setUsername(postgres.getUsername());
		driverManagerDataSource.setPassword(postgres.getPassword());

		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(PATH_INIT_SCHEMA, PATH_INIT_DATA);
		DatabasePopulatorUtils.execute(databasePopulator, driverManagerDataSource);

		return driverManagerDataSource;
	}

}
