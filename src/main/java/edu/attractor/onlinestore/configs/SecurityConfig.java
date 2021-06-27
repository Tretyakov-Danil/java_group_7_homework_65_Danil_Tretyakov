package edu.attractor.onlinestore.configs;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder(10);
    }

    private final DataSource dataSource;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        String fetchUsersQuery = "select email, password, enabled"
//                + " from clients"
//                + " where email = ?";
//
//        String fetchRoleQuery = "select email, role"
//                + " from clients"
//                + " where email = ?";
//
//        auth.jdbcAuthentication()
//                .usersByUsernameQuery(fetchUsersQuery)
//                .authoritiesByUsernameQuery(fetchRoleQuery)
//                .dataSource(dataSource)
//                .passwordEncoder(encoder());
//
//    }

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
