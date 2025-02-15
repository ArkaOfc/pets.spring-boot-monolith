package alekseybykov.portfolio.springboot.component.configuration;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Aleksey Bykov
 * @since 24.06.2019
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @SneakyThrows
    protected void configure(AuthenticationManagerBuilder auth) {
       auth.inMemoryAuthentication()
               .withUser("user")
               .password("{noop}user")
               .roles("USER"); //
    }

    @Override
    @SneakyThrows
    protected void configure(HttpSecurity http) {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/person/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/person/add").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/person/update").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/person/**").hasRole("USER")
                .and()
                .csrf()
                .disable()
                .formLogin()
                .disable();
    }
}
