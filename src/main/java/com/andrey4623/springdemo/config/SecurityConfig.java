package com.andrey4623.springdemo.config;


import com.andrey4623.springdemo.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final MyUserDetailsService userDetailsService;

  public SecurityConfig(MyUserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/home/**").hasRole("READ_PROFILE")
        .antMatchers("/admin/**").hasRole("ACCESS_CONTROL_PANEL")
        .and()
        .formLogin()
        .loginPage("/login").failureUrl("/login?error")
        .and()
        .exceptionHandling().accessDeniedPage("/403");
  }

  @Autowired
  void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider());
  }

  @Bean
  DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider
        = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(encoder());
    return authProvider;
  }

  Md5PasswordEncoder encoder() {
    return new Md5PasswordEncoder();
  }

}
