package org.zerock.myapp.config;


import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import org.zerock.myapp.service.AdminsDetailServiceImpl;
import org.zerock.myapp.service.UsersDetailsServiceImpl;

import java.util.Objects;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Log4j2
@NoArgsConstructor
@EnableWebSecurity

@Configuration
public class SecurityConfig {
    // Users 계정에 권한을 주기 위해 의존성을 주입
    @Setter(onMethod_ = @Autowired)
    private UsersDetailsServiceImpl usersDetailsService;
    // Admin 계정에 권한을 주기 위해 의존성을 주입
    @Setter(onMethod_ = @Autowired)
    private AdminsDetailServiceImpl adminsDetailService;

//    1. 만든 UserDetailsService 객체를 이용해 특정 사용자의 인증정보를 사용하도록 하는 설정
//    @Bean으로 자바빈즈객체로 등록
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector)
            throws Exception{
        log.trace("securityFilterChain({}) Invoked.", http);
        Objects.requireNonNull(this.usersDetailsService);
        Objects.requireNonNull(this.adminsDetailService);


//     여기서 http는 Spring Security의 핵심 요소 중 하나, 웹 요청에 대한 보안 정책을 정의하고 구현하는데 사용
//      즉 우리가 만든 userDetailsService메소드를 호출하여 user,admin 사용자의 데이터를 가져온다.
        http
            .userDetailsService(this.usersDetailsService)
            .userDetailsService(this.adminsDetailService);

//      Spring Security에서 웹 요청을 판별하는 데 필요한 기반이 되는 부분이며, introspector 객체를 통해
//      웹 요청의 핸들러 매핑 정보를 활용하여 URL 패턴을 정확하게 매치할 수 있다.
//      이렇게 매치된 패턴은 보안 규칙과 권한 설정과 연계되어 해당 URL에 대한 접근 권한을 정의하는 데 사용됨.
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);



//        http.authorizeHttpRequests(
//                customizer ->
//                    customizer.requestMatchers(antMatcher("/main")).permitAll().
//                            requestMatchers(antMatcher("/recipe/write")).hasAnyRole("ADMIN","USER").
//                            requestMatchers(antMatcher("/noticeWriter")).hasRole("ADMIN").
//                            anyRequest().permitAll()
//                );


//        여기서 보면 main은 permitAll로 모든 권한이 접근 가능하게 설정해 줬음
//        그 후 hasAnyRole은 Role 여러개를 선택해서 접근 권한을 주는것이고
//        hasRole은 단 한개의 계정에만 권한을 주는것
//        마지막으로 이것을 제외한 어떠한 요청(anyRequest)에 모두 접근을 허락한다.
//        그 후 로그인 페이지를 설정하고 URI를 매핑하는 코드이다.
        http.authorizeHttpRequests(
                customizer ->
                        customizer.requestMatchers(antMatcher("/main")).permitAll().
                                requestMatchers(antMatcher("/recipe/page/1")).hasAnyRole("USER","ADMIN").
                                requestMatchers(antMatcher("/help/help")).hasAnyRole("USER","ADMIN").
                                requestMatchers(antMatcher("/admin/main")).hasRole("ADMIN").
                                requestMatchers(antMatcher("/mypage/**")).hasAnyRole("USER","ADMIN").
                                anyRequest().permitAll()
        ).
                formLogin(
                customizer -> customizer.loginPage("/login/admin").
                        loginProcessingUrl("/login/admin").
                        usernameParameter("id").
                        passwordParameter("password").
                        defaultSuccessUrl("/main", true)
        ).formLogin(
                customizer -> customizer.loginPage("/login/login").
                        loginProcessingUrl("/login/login").
                        usernameParameter("userId").
                        passwordParameter("password").
                        defaultSuccessUrl("/main", true)
        );





//      CSRF 공격은 웹 애플리케이션에서 사용자가 의도하지 않은 동작을 수행할 수 있는 보안 취약점을 이용한 공격
        http.csrf(AbstractHttpConfigurer::disable);

//      접근이 거부되면 이페이지로 보내겠다라는것을 설정한다.
        http.exceptionHandling(customizer -> customizer.accessDeniedPage("/accessDenied"));


//      로그아웃에 대한 설정
        http.logout( customizer ->
                customizer.invalidateHttpSession(true).
                        logoutSuccessUrl("/main")
        ); // logout


//        위에서 작성한 코드들을 실행하겠다.
        return http.build();
    } // securityFilterChain


    //    Password Encoding암호화!
    @Bean
    public PasswordEncoder passwordEncoder(){
        log.trace("passwordEncoder() Invoked.");

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    } // passwordEncoder




} // end class
