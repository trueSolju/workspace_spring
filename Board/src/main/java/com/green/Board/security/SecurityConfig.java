package com.green.Board.security;

import com.green.Board.vo.MemberVO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//스프링 시큐리티 인증, 인가에 대한 프로세스를 정의

// 이 클래스가 시큐리티 설정파일임을 인지시켜주는 역할
@EnableWebSecurity
//객체 생성 어노테이션 (Contoller, @Service)
@Configuration
public class SecurityConfig {

    //암호화에 사용하는 객체 생성
    @Bean
    public BCryptPasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }

    //@Bean : 객체 생성 어노테이션
    // 메소드의 실행 결과 리턴되는 데이터를 객체로 생성

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        //csrf 공격에 대한 방어를 해지 하겠다.
        security.csrf(AbstractHttpConfigurer::disable)
                //authorizeHttpRequests 메소드 안에서 인증 및 인가 관리
                .authorizeHttpRequests(
                        c -> {
                            c.requestMatchers(
                                    new AntPathRequestMatcher("/"),
                                    new AntPathRequestMatcher("/loginForm"),
                                    new AntPathRequestMatcher("/joinForm"),
                                    new AntPathRequestMatcher("/join"),
                                    new AntPathRequestMatcher("/sample"),
                                    new AntPathRequestMatcher("/sec")

                            ).permitAll()
                            .requestMatchers(
                                    new AntPathRequestMatcher("/admin")
                            ).hasRole("ADMIN")
                                    .requestMatchers(
                                            new AntPathRequestMatcher("/manager")
                                    ).hasRole("MANAGER")
                                    .requestMatchers(
                                            new AntPathRequestMatcher("/write")
                                    ).hasAnyRole("USER","MANAGER")
                            .anyRequest().authenticated();
                        }
                )
                // /member/** -> /member 하위 모두 체크 (**꼭 두개/하위에 몇단계가 있어도 전부 가능하게 하겠다)


                //anyRequest().permitAll() ->모든 경로 다 허용

                //로그인 form을 활용해서 할 것이고,
                //로그인 설정내용도 포함
                .formLogin(
                        formLogin ->{
                            //로그인 페이지 url 설정
                            formLogin.loginPage("/loginForm")
                                    //로그인 시 전달되는 id 및 비밀번호의 name 속성값을 지정
                                    .usernameParameter("memberId")
                                    .passwordParameter("memberPw")
                                    //로그인 기능이 실행되는 url
                                    .loginProcessingUrl("/login")
                                    //로그인 성공시 이동할 url
                                    //두번째 매개변수로 true를 넣으면 무조건 설정한 페이지로 넘어감!
                                    //두번째 매개변수가 없으면 이전 페이지로 이동.
                                    //이전 페이지가 없다면 지정한 url로 이동
                                    .defaultSuccessUrl("/")
                                    .failureUrl("/loginForm");
                        }
                ).logout(
                        logout ->{
                            //해당 url 요청이 들어오면 시큐리티가 로그아웃 진행
                            logout.logoutUrl("/logout")
                                    // 로그아웃 성공 시 이동할 url
                                    .logoutSuccessUrl("/")
                                    // 로그아웃 성공 시 세션 데이터 삭제
                                    .invalidateHttpSession(true);
                        }
                )
                //예외 발생시 처리해야 하는 내용 작성
                .exceptionHandling(
                        ex -> {
                            ex.accessDeniedPage("/deny");
                        }
                );


        return security.build();


//        //csrf : csrf 공격에 대해 어떻게 대처할거냐?
//
//        security.csrf(AbstractHttpConfigurer::disable) //메소드참조
//                //아래 메소드 안에 인증, 인가에 대한 모든 설정 작성
//                .authorizeHttpRequests(
//                        c -> {
//                            c.requestMatchers(
//                                    new AntPathRequestMatcher("/"),
//                                    new AntPathRequestMatcher("/loginForm"),
//                                    new AntPathRequestMatcher("/joinForm")
//                                    ).permitAll()
//                                    .anyRequest().authenticated();
//                        }
//                )
//                //로그인 설정 + 로그인 할때 form 태그 쓰겟다.
//                .formLogin(
//                        formLogin -> {
//                            // 로그인페이지로 이동하는 url 설정
//                            formLogin.loginPage("/loginForm")
//                                    //실제 로그인 기능을 실행하는 url 설정
//                                    .loginProcessingUrl("/login")
//                                    //로그인 성공 시 기본적으로 이동하는 페이지
//                                    .defaultSuccessUrl("/");
//                        }
//                );
//
//        return security.build();
    }
    //spring security(lambda)
    // 인증 및 인가를 관리하는 라이브러리
    //인증(Authentication):신분확인,로그인
    //인가/권한(Authorization) : admin

    //1. spring 프로젝트 생성시 security 를 사용하겠다는 dependency 추가
    //2. 모든 페이지를 방문하기 위해선 반드시 인증이 필요!
    //3. 프로젝트 성향에 맞춰 인증, 인가에 대한 설정을 우리가 코드로 구현
    //4. 로그인 기능을 구현
    //(로그인은 우리가 구현하는게 아니라 시큐리티한테 위임)
    // 로그인에 필요한 정보를 시큐리티한테 전달만 해주면됨.

}
