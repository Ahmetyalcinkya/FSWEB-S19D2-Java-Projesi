package com.workintech.S19d2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean // authentication spring tarafından yapılmasın veri tabanı tasarımına göre yönetelim
    public AuthenticationManager authManager(UserDetailsService service){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(service);
        return new ProviderManager(authenticationProvider);
    }

    @Bean // endpoint yönetimi
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        return security.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/auth/**").permitAll(); // herkese izin verir

//                    auth.requestMatchers("/account/**").hasAuthority("ADMIN"); // admin kullanıcılara izin verir

//                    auth.requestMatchers(HttpMethod.GET,"/account/**")
//                    .hasAnyAuthority("ADMIN", "USER"); // GET isteğini adminler ve user'lar yapabilir. Kısıtlama yapmak istediğin durumlarda kullanmalısın.
//                                                          Bu durumda gerekli bir metod değildir.

                    auth.requestMatchers(HttpMethod.POST,"/account/**").hasAuthority("ADMIN"); // POST isteğini sadece adminler yapabilir.
                    auth.requestMatchers(HttpMethod.PUT,"/account/**").hasAuthority("ADMIN"); // PUT isteğini sadece adminler yapabilir.
                    auth.requestMatchers(HttpMethod.DELETE,"/account/**").hasAuthority("ADMIN"); // DELETE isteğini sadece adminler yapabilir.

                    auth.anyRequest().authenticated(); // giriş yapmamış kimseye izin vermez.
                })
                .oauth2Login(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
