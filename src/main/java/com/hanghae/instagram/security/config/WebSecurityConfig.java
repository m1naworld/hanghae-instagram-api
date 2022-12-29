package com.hanghae.instagram.security.config;


import com.hanghae.instagram.security.jwt.JwtAuthFilter;
import com.hanghae.instagram.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
public class WebSecurityConfig {

    private final JwtUtil jwtUtil;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF 설정
        http.csrf().disable().cors().configurationSource(corsConfigurationSource());

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/api/member/**").permitAll()
                .anyRequest().authenticated() // 그외 전부 인증처리
                .and().addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedOrigin("http://152.69.229.168:86/");
        configuration.addAllowedOrigin("https://hanghaecatsanddogs.s3.ap-northeast-2.amazonaws.com/");
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","PATCH","DELETE")); // 허용할 Http Method
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true); // 내 서버가 응답할 때 json을 js에서 처리할 수 있게 설정
        configuration.setMaxAge(3600L);
        configuration.addExposedHeader(JwtUtil.AUTHORIZATION_HEADER);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}

