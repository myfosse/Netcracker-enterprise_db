package by.netcracker.enterprisedb.config.security;

import by.netcracker.enterprisedb.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String PREFIX = "/api/v1";

  private final UserDetailsServiceImpl userDetailsService;

  @Autowired
  public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
      throws Exception {
    authenticationManagerBuilder
        .userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers(
            PREFIX + "/auth/sign-up",
            PREFIX + "/department/all/**",
            PREFIX + "/news/all/**",
            PREFIX + "/position/all/**")
        .anonymous()
        .antMatchers(
            PREFIX + "/auth/sign-up",
            PREFIX + "/department/all/**",
            PREFIX + "/news/all/**",
            PREFIX + "/position/all/**")
        .permitAll()
        .antMatchers(
            PREFIX + "/bonus/user/**}",
            PREFIX + "/career/user/**",
            PREFIX + "/employee/user/**",
            PREFIX + "/holiday/user/**",
            PREFIX + "/news/user/**",
            PREFIX + "/request/user/**}")
        .hasAuthority("ROLE_USER")
        .antMatchers(
            PREFIX + "/bonus/**",
            PREFIX + "/career/**",
            PREFIX + "/department/**",
            PREFIX + "/employee/**",
            PREFIX + "/holiday/**",
            PREFIX + "/news/**",
            PREFIX + "/position/**",
            PREFIX + "/request/**")
        .hasAuthority("ROLE_ADMIN")
        .and()
        .httpBasic()
        .and()
        .logout()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .csrf()
        .disable();
  }
}
