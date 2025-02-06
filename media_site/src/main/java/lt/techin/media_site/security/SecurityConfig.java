package lt.techin.media_site.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserServiceImplementation userServiceImplementation;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder,
                          UserServiceImplementation userServiceImplementation) {

        this.userServiceImplementation = userServiceImplementation;
        this.passwordEncoder = passwordEncoder;
    }

@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws  Exception {
        return config.getAuthenticationManager();
}

@Bean
public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
}

@Bean
public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userServiceImplementation);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
}

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .sessionManagement(session -> {
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            })
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .csrf(csrf-> csrf.disable())
            .authorizeHttpRequests(authorize ->authorize
                    .requestMatchers(HttpMethod.POST, "/api/authentication/**").permitAll()
                    .requestMatchers( HttpMethod.GET,"/api/test/admin").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST,"/api/users").permitAll()
                    .requestMatchers(HttpMethod.GET,"/api/users").hasRole("ADMIN")
                    .anyRequest().authenticated()
            );
    return http.build();
}

}
