package com.citronbrick.kpi;

import com.citronbrick.kpi.services.*;
import com.citronbrick.kpi.entities.*;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.*;
import lombok.*;
import java.util.*;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.*;
import org.springframework.boot.autoconfigure.orm.jpa.*;
import javax.sql.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.autoconfigure.jdbc.*;



@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class})
// @SpringBootApplication
// @EnableJpaRepositories
@AllArgsConstructor
public class KpiBackendApplication implements CommandLineRunner {

	private FakeCompanyService fakeCompanyService;

	/*@Autowired
	private DataSource dsList;*/


	public void run(String ...args) {
		// System.out.println(dsList);
	}

	public static void main(String[] args) {
		SpringApplication.run(KpiBackendApplication.class, args);
	}

}
