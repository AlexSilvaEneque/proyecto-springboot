package com.web.logincrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.web.logincrud.utils.CustomAccessDenied;
import com.web.logincrud.utils.EncoderCustom;
import com.web.logincrud.utils.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	@Autowired
	UserDetailsServiceImpl service;
	
	@Bean
	AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDenied();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(service);
		auth.setPasswordEncoder(EncoderCustom.passwordEncoder());
		return auth;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth
				.antMatchers("/","/error","/fragments","/forbidden","/login")
				.permitAll()
				.anyRequest()
				.authenticated()
				);
		http.formLogin(form -> form
				.loginProcessingUrl("/siging")
				.loginPage("/login").permitAll()
				.defaultSuccessUrl("/index")
				.usernameParameter("email")
				.passwordParameter("password")
				);
		http.exceptionHandling(ex -> ex.accessDeniedHandler(accessDeniedHandler()));
		http.logout(lo -> lo.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout").permitAll());
		return http.build();
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
	    return (web) -> web.ignoring().mvcMatchers("/js/**", "/css/**", "/images/**");
	}
}