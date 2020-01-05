package com.atguigu.gmall.pms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
//此类用于放开网页的访问请求权限
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //放开所有的请求   Matchers匹配所有路径
       http.authorizeRequests().antMatchers("/**").permitAll();
      //禁用csrf
       http.csrf().disable();
    }
}
