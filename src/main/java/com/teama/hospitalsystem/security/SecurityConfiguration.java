package com.teama.hospitalsystem.security;

import com.teama.hospitalsystem.util.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtTokenFilter jwtTokenFilter;
    private final UserDetailsService userDetailsService;

    public SecurityConfiguration(JwtTokenFilter jwtTokenFilter, UserDetailsService userDetailsService) {
        this.jwtTokenFilter = jwtTokenFilter;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/admin/*").hasRole(UserRole.ADMIN.name())
                .antMatchers("/registry/*").hasRole(UserRole.REGISTRY.name())
                .antMatchers("/doctor/*").hasRole(UserRole.DOCTOR.name())
                .antMatchers("/patient/*").hasRole(UserRole.PATIENT.name())
                .antMatchers("/register", "/auth").permitAll()
                .and()
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix
    }
}
