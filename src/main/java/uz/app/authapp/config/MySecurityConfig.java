package uz.app.authapp.config;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import uz.app.authapp.db.UserDAO;
import uz.app.authapp.entity.User;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class MySecurityConfig {
    private final UserDAO userDAO;

    @Bean
    @SneakyThrows
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(c -> c.disable())
                .csrf(c -> c.disable());
        http
                .authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers("/auth/**")
                            .permitAll()
//                            .requestMatchers("/test/post")
//                            .hasAuthority("CAN_POST")
//                            .requestMatchers("/test/put")
//                            .hasAuthority("CAN_PUT")
//                            .requestMatchers("/test/delete")
//                            .hasRole("ADMIN")
                            .anyRequest()
                            .authenticated();
                });
        http.formLogin(login -> {
            login
                    .loginPage("/auth/signIn")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .failureForwardUrl("/auth/signIn");
//                    .defaultSuccessUrl("/product",false);
        });

        http.userDetailsService(userDetailsService());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return (username) ->
                userDAO.getUserByUsername(username);
    }

}
