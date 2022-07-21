package com.hanwha.tax.apiserver.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    //    private final JwtTokenProvider jwtTokenProvider;
    private final JwtEntryPoint jwtEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;      // JWT 기반으로 구현해야 하기 때문에 JwtAuthenticationFilter 라는 이름의 클래스를 구현
    private final CustomUserDetailsService customUserDetailsService;   // UserDetailsService 라는 유저의 정보를 가져오기 위한 클래스

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    // API rul 접속 보안(시큐리티) 설정
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()  // rest api이므로 기본설정 안함. 기본설정은 비인증 시 로그인 폼 화면으로 리다이렉트 된다.
                .csrf().disable()       // rest api 이므로 csrf 보안이 필요 없음. disable
                .sessionManagement().sessionCreationPolicy(STATELESS) // jwt token으로 생성하므로 세션은 필요 없으므로 생성 안함.

                .and().authorizeRequests()    // 다음 리퀘스트에 대한 사용권한 체크
                .antMatchers("/api/*/auth/*", "/actuator/**").permitAll()
                .antMatchers("/api/info/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/test/**").permitAll()
//                .antMatchers( "/api/*").hasRole("USER")
//                .anyRequest().hasRole("USER")   // 그 외 나머지 요청은 모두 인증된 회원만 접근 가능
                .anyRequest().authenticated()

                // JWT 기반으로 로그인/로그아웃을 처리할 것이기 때문에 logout은 disable해주었고, 스프링 시큐리티는 기본 로그인/로그아웃시 세션을 통해 유저 정보들을 저장
                // Redis를 사용할 것이기 때문에 상태를 저장하지 않는 STATELESS로 설정
                .and()
                .logout().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)

                // 시큐리티 필터 과정중 에러가 발생할 경우는 JwtEntryPoint에서 처리하도록 구현
                .and().exceptionHandling()
                .authenticationEntryPoint(jwtEntryPoint)

                // JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter(id/pw 인증필터) 전에 필터를 추가하겠다는 의미
                .and().addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    // Web url 접속 보안(시큐리티) 해제
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**",
                "/swagger-ui.html", "/webjars/**", "/swagger/**", "/h2-console/**");
    }


    @Bean
    // 사용자가 회원 가입시 입력한 비밀번호를 BCrypt strong hashing function을 통해 암호화하며, 단방향
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }
}