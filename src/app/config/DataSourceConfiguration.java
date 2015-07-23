package app.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * 
 * @author abdulsattar
 * 
 * Initialize H2 data source.
 *
 */

@Configuration
public class DataSourceConfiguration {


	@Bean
	public DataSource embeddedDataSource() 
	{
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		builder.setType(EmbeddedDatabaseType.H2);
		builder.addScript("classpath:schema.sql");
		DataSource ds = builder.build();
		return ds;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) 
	{
		return new JdbcTemplate(dataSource);
	}
}
