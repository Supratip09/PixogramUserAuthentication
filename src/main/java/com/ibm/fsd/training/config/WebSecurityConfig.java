package com.ibm.fsd.training.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfig {
	
	 @Bean
	    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
		 http.cors().configurationSource(corsConfigurationSource());
	        return http
	            .csrf().disable()
	            .authorizeExchange()
	                .pathMatchers("/error", "/logout").permitAll()
	                .pathMatchers(""
	                		+ "/gateway/register/**",
	                    "/css/**",
	                    "/fonts/**",
	                    "/icons-reference/**",
	                    "/img/**",
	                    "/js/**",
	                    "/vendor/**").permitAll()
	            .anyExchange()
	                .authenticated()
	                .and()
	           // .exceptionHandling()
	            	//.authenticationEntryPoint(new HttpStatusServerEntryPoint(HttpStatus.UNAUTHORIZED))
	            	//.and()
	            .httpBasic()
	            //.formLogin()
	               // .loginPage("/login")
	                .and()
					/*
					 * .logout() .logoutUrl("/logout") .and()
					 */
	            .build();
	    }

	 
  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
  
  @Bean
  public ReactiveAuthenticationManager authenticationManager(ReactiveUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
  	UserDetailsRepositoryReactiveAuthenticationManager manager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
  	manager.setPasswordEncoder(passwordEncoder);
  	return manager;
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
      return source;
  }

}
