package com.yodadoc.v1.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.NullSecurityContextRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.yodadoc.v1.security.OAuthFilter;

@SpringBootApplication(scanBasePackages = "com.yodadoc")
@EnableAutoConfiguration
@Configuration
@EntityScan(basePackages = { "com.yodadoc.v1.core.entity" })
@EnableJpaRepositories(basePackages = { "com.yodadoc.v1.core.entity.repository" })
@EnableTransactionManagement
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Application extends WebSecurityConfigurerAdapter {
	@Autowired
	private OAuthFilter oAuthFilter;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
				.antMatchers("/bower_components/**/*", "/**/*.html", "/**/*.css", "/**/*.js", "/", "/login")
				.permitAll().and().securityContext().securityContextRepository(new NullSecurityContextRepository())
				.and().addFilterAfter(oAuthFilter, BasicAuthenticationFilter.class).authorizeRequests().anyRequest()
				.authenticated().and().exceptionHandling()
				.authenticationEntryPoint(new Http401AuthenticationEntryPoint("yodaDoc"));
	}

}