package com.tx.demo.config.security;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.AuthProvider;

//*
// * @Author tianxin
// * @Date 2019/1/29
// * @Description:


@Configurable
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new BCryptPasswordEncoder();
    }


    // 自定义用户认证策略
  /*  @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth
                .userDetailsService(new MyUserDetailsService())
                .passwordEncoder(new BCryptPasswordEncoder());
    }*/

    @Bean
    UserDetailsService detailsService() {
        return new MyUserDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailsService()).passwordEncoder(passwordEncoder());
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    // 请求过滤
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                //  开启post 请求
//                .csrf().disable()
//                //  为请求的url添加权限
//                .authorizeRequests()
//                .antMatchers("/rest/**").permitAll()
//                //  所有的请求必须通过授权
//                .anyRequest().authenticated()
//                .and()
//                //  配置登录页面
//                .formLogin().loginPage("/rest/login")
//                .failureUrl("/login?error")
//                .permitAll()
//                .and()
//                //  指定登出的url
//                .logout()
//                .logoutUrl("/rest/logout").permitAll()
//                .and()
//                .httpBasic();


    }
}
