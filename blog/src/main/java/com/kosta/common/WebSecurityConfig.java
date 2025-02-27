package com.kosta.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@Configuration // 이 클래스가 Spring의 구성 클래스임을 나타낸다
@EnableWebSecurity // Spring Security의 웹 보안 기능을 활성화한다
@RequiredArgsConstructor
public class WebSecurityConfig {
	
	private final UserDetailsService userService;
	
//	@Bean
//	WebSecurityCustomizer configure() {
//		return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/static/**"));
//	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		return http.authorizeHttpRequests(auth -> auth.requestMatchers(new AntPathRequestMatcher("/login"), new AntPathRequestMatcher("/join"))// 로그인, 회원가입 경로는 인증 없이 접근 가능
				.permitAll().anyRequest().authenticated()) //제외한 모든 요청 인증
				.formLogin(formLogin -> formLogin.loginPage("/login").defaultSuccessUrl("/blog/list"))//로그인 페이지 지정, 성공시 이동할 페이지 지정
				.logout(logout -> logout.logoutSuccessUrl("/login").invalidateHttpSession(true))
				.csrf(AbstractHttpConfigurer::disable)
				.cors(AbstractHttpConfigurer::disable)
				.build();
				
	}
	@Bean
	//인증 처리 담당
	AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCrypt, UserDetailsService userDS) throws Exception{
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userService);
		authProvider.setPasswordEncoder(bCrypt);
		return new ProviderManager(authProvider);
		
	}
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
