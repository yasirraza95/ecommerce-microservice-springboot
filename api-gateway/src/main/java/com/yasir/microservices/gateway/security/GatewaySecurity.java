package com.yasir.microservices.gateway.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.NegatedServerWebExchangeMatcher;

@Configuration
@EnableWebSecurity(debug = true)
public class GatewaySecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtTokenFilter jwtTokenFilter;

	
	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http ) {
		http.csrf().disable().authorizeExchange().pathMatchers("/user/login/**").permitAll()
		.pathMatchers("/user/profile/**").hasRole("USER").anyExchange().authenticated();
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.exceptionHandling().authenticationEntryPoint((request, response, ex) -> {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
		});

		http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
	}

	
//	@Bean
//	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http, JwtTokenProvider tokenProvider,
//			ReactiveAuthenticationManager reactiveAuthenticationManager) {
//		http.csrf().disable().authorizeExchange().pathMatchers("/user/login/**").permitAll()
//				.pathMatchers("/user/profile/**").hasRole("USER").anyExchange().authenticated();
//		http.addFilterAt(new JwtTokenAuthenticationFilter(tokenProvider), SecurityWebFiltersOrder.HTTP_BASIC);
//		return http.build();
//	}
	

//	@Bean
//    public ReactiveUserDetailsService userDetailsService(UserRepository users) {
//
//        return username -> users.findByUsername(username)
//                .map(u -> User
//                        .withUsername(u.getUsername()).password(u.getPassword())
//                        .authorities(u.getRoles().toArray(new String[0]))
//                        .accountExpired(!u.isActive())
//                        .credentialsExpired(!u.isActive())
//                        .disabled(!u.isActive())
//                        .accountLocked(!u.isActive())
//                        .build()
//                );
//		
//		return null;
//	}
	
//	@Bean
//	public ReactiveAuthenticationManager reactiveAuthenticationManager(ReactiveUserDetailsService userDetailsService,
//			PasswordEncoder passwordEncoder) {
//		UserDetailsRepositoryReactiveAuthenticationManager authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(
//				userDetailsService);
//		authenticationManager.setPasswordEncoder(passwordEncoder);
//		return authenticationManager;
//	}

}
