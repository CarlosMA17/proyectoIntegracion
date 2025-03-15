package com.app.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.jwt.JwtAuthenticationFilter;
import com.app.service.UserDetailsServiceImpl;

//aqui se pondran los bean (@ ejemplo entity,service etc)
//@EnableWebSecurity(debug = true)
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

//	@Autowired
//	AuthenticationConfiguration authenticationConfiguration;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(csrf -> csrf.disable())
				.cors(Customizer.withDefaults())
				.authorizeHttpRequests(auth -> 
												auth.requestMatchers(HttpMethod.GET, 
																		"/api/ping", 
																		"/doc/swagger-ui/**", 
																		"/doc/swagger-ui.html", 
																		"/v3/api-docs/**").permitAll()
				.requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/scrapyards").hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/scrapyards").hasRole("USER")
							// .requestMatchers(HttpMethod.GET, "/api/writers/onlycreate").hasAuthority("CREATE")
				.anyRequest().authenticated()
				)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				.httpBasic(Customizer.withDefaults()) // para form user,pw
				.build();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception { // se puede inyectar por constructor el authCon

		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	AuthenticationProvider authenticationProvider(UserDetailsServiceImpl userDetailsService) throws Exception {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userDetailsService);

		return provider;
	}

	@Bean
	PasswordEncoder passwordEncoder() {

		// return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}
/**
	@Bean
	UserDetailsService userDetailService() {
		UserDetails userDetails = User.withUsername("alexis")
				.password("1234")
				.roles("ADMIN")
				.authorities("READ", "CREATE")
				.build();
		
		List<UserDetails> userDetailsList = new ArrayList<>();
		userDetailsList.add(userDetails);
		
		userDetailsList.add(User.withUsername("pepe")
				.password("pepe")
				.roles("USER")
				.authorities("READ")
				.build());

		return new InMemoryUserDetailsManager(userDetailsList);
	}
	
**/
}