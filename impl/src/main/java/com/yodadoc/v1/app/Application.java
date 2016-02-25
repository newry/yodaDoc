package com.yodadoc.v1.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.yodadoc")
@EnableAutoConfiguration
@Configuration
@EntityScan(basePackages = { "com.yodadoc.v1.core.entity" })
@EnableJpaRepositories(basePackages = { "com.yodadoc.v1.core.entity.repository" })
@EnableTransactionManagement
public class Application {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}
