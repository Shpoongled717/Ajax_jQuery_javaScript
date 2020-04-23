package com.example.springbootcrud.config;

import com.example.springbootcrud.config.handler.LoginSuccessHandler;
import com.example.springbootcrud.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImp;
	
	@Autowired
	private LoginSuccessHandler loginSuccessHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImp);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()
				.loginPage("/login")
				.successHandler(loginSuccessHandler)
				.loginProcessingUrl("/login")
				.usernameParameter("email")
				.passwordParameter("password")
				.permitAll();
		
		http.logout()
				// разрешаем делать логаут всем
				.permitAll()
				// указываем URL логаута
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				// указываем URL при удачном логауте
				.logoutSuccessUrl("/login")
				.and().csrf().disable();
		
		http
				// делаем страницу регистрации недоступной для авторизированных пользователей
				.authorizeRequests()
				//страницы аутентификаци доступна всем
				.antMatchers("/login").anonymous()
				.antMatchers("/user?**").access("hasAnyRole('ROLE_USER')")
				// защищенные URL
				.antMatchers("/admin/**").access("hasAnyRole('ROLE_ADMIN')").anyRequest().authenticated();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
