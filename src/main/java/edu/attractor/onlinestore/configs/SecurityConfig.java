package edu.attractor.onlinestore.configs;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/client/login")
                .defaultSuccessUrl("/products")
                .failureUrl("/client/invalid")
                .usernameParameter("email")
                .passwordParameter("password");

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/products")
                .invalidateHttpSession(true)
                .clearAuthentication(true);

        http.authorizeRequests()
                .antMatchers("/orders/**")
                .authenticated();

        http.authorizeRequests()
                .anyRequest()
                .permitAll();

    }
}
