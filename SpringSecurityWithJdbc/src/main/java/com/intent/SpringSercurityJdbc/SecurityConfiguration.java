package com.intent.SpringSercurityJdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	@Autowired
	DataSource dataSource;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// this.disableLocalConfigureAuthenticationBldr = true;
		auth.jdbcAuthentication().dataSource(dataSource);
	}
	

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		//BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(16);
		//return bCryptPasswordEncoder;
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	public void configure(HttpSecurity security) throws Exception {
				security.authorizeRequests().antMatchers("/user").hasAnyRole("USER")
				.antMatchers("/admin").hasRole("ADMIN").antMatchers("/").permitAll().and().formLogin();

	}
}
