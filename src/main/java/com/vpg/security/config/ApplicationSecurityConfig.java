package com.vpg.security.config;

import com.vpg.security.passwordConfig.PasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordConfig passwordConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Level 01 Configure HTTP Basic Authentication
        /*http
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();*/

        // Level 02 Configure HTTP Basic with Matchers
        http
                .authorizeRequests()
                .antMatchers("/") // Allowing permission tp root web page
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

    }

    // In Memory user details manager
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails gopalUser = User
                .builder()
                .username("gopal")
                .password(passwordConfig.passwordEncoder().encode("password"))
                .roles("EMPLOYEE")
                .build();

        return new InMemoryUserDetailsManager(gopalUser);
    }
}
