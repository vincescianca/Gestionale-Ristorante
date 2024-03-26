package it.sciancaleporevincenzo.gestRest;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DatasourceConfiguration {

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

}
