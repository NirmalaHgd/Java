package org.learning.SpringStarter.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)

public class WebSecurityConfig {
    private static final String[] WHITELIST ={
        "/",
        
        "/register",
        "/loging",
        "/db-console/**",
        "/css/**",
        "/fonts/**",
        "/images/**",
        "/js/**",
        "/reset-password/**",
        "/forgot-password/**"
    };

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     http.authorizeHttpRequests((auth) -> auth.
     requestMatchers(WHITELIST).
    permitAll().
    requestMatchers("/profile/**").authenticated().
    requestMatchers("/admin/**").hasRole("ADMIN").
    requestMatchers("/editor/**").hasAnyRole("ADMIN", "EDITOR").
    requestMatchers("/post/**").hasAnyRole("USER","ADMIN", "EDITOR").
    requestMatchers("/posts/**").hasAnyRole("USER","ADMIN", "EDITOR").
    requestMatchers("/add_post/**").hasAnyRole("USER","ADMIN", "EDITOR").
    requestMatchers("/edit_post/**").hasAnyRole("USER","ADMIN", "EDITOR").
    requestMatchers("/update_photo/**").hasAnyRole("USER","ADMIN", "EDITOR").
    requestMatchers("/admin/**").hasAuthority("ACCESS_ADMIN_PANEL")
    
    
    )
    .formLogin((formLogin) -> formLogin
                    .loginPage("/loging")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/", true)
                    .failureUrl("/loging?error")
                    .permitAll()
                    
            )
            
            
            .logout(logout -> logout.logoutUrl("/logout").
            logoutSuccessUrl("/").permitAll())
            .rememberMe((remember) -> remember
				.rememberMeParameter("remember-me")
			)
            ;
    
    
    ;
    
    //remove after upgrading the db from h2 infile db
    
 http.headers((headers) ->headers.frameOptions((frameOptions) -> frameOptions.disable()));
 http.csrf(csrf->csrf.disable());

    return http.build();
	
}    
}

