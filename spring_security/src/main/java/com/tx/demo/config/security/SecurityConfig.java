package com.tx.demo.config.security;

import com.tx.demo.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author tianxin
 * @Date 2019/1/29
 * @Description:
 */
@Configurable
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 自定义用户认证策略
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth
                .userDetailsService(new MyUserDetailsService())
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    // 请求过滤
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //  开启post 请求
                .csrf().disable()
                //  为请求的url添加权限
                .authorizeRequests()
                //  所有的请求必须通过授权
                .anyRequest().authenticated()
                .and()
                //  配置登录页面
                .formLogin()
                .defaultSuccessUrl("/index.html")
                .failureUrl("/login?error")
                .permitAll()
                .and()
                //  指定登出的url
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index")
                .and()
                .httpBasic();


    }
}
