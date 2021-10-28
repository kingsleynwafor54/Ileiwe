package com.ileiwe.ileiwe;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class IleIweApplicationTests {
		@Autowired
		DataSource dataSource;
	@Test
	void contextLoads() {
	}
		@Test
		public void connectToDatabaseTest() {

			assertThat(dataSource).isNotNull();
			log.info("Datasource properties->{}",dataSource);
			try {
				Connection connection = dataSource.getConnection();
				assertThat(connection).isNotNull();
				log.info("Database->{}",connection.getCatalog());
			} catch (SQLException exception) {
				log.info("An exception occurred ->{}", exception.getMessage());
			}

		}


}
