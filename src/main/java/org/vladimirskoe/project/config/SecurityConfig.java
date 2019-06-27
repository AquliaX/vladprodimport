package org.vladimirskoe.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.vladimirskoe.project.entity.User;
import org.vladimirskoe.project.security.CustomUserDetailsService;
import org.vladimirskoe.project.security.JwtAuthenticationEntryPoint;
import org.vladimirskoe.project.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String ROLE_ADMIN = User.UserRole.ADMIN.toString();
    private static final String ROLE_CLIENT = User.UserRole.CLIENT.toString();
    private static final String ROLE_OPERATOR = User.UserRole.OPERATOR.toString();
    private static final String ROLE_MANAGER = User.UserRole.MANAGER.toString();

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/auth/**")
                    .permitAll()

                .antMatchers(HttpMethod.GET, "/products**")
                    .authenticated()
                .antMatchers(HttpMethod.POST, "/products")
                    .hasAnyAuthority(ROLE_ADMIN, ROLE_MANAGER)
                .antMatchers(HttpMethod.DELETE, "/products/{id}")
                     .hasAnyAuthority(ROLE_ADMIN, ROLE_MANAGER)
                .antMatchers(HttpMethod.PUT, "/products/{id}")
                     .hasAnyAuthority(ROLE_ADMIN, ROLE_MANAGER)

                .antMatchers(HttpMethod.GET, "/packages**")
                    .hasAnyAuthority(ROLE_ADMIN, ROLE_MANAGER, ROLE_CLIENT)
                .antMatchers(HttpMethod.POST, "/packages")
                    .hasAnyAuthority(ROLE_ADMIN, ROLE_MANAGER)
                .antMatchers(HttpMethod.DELETE, "/packages/{id}")
                    .hasAnyAuthority(ROLE_ADMIN, ROLE_MANAGER)
                .antMatchers(HttpMethod.PUT, "/packages/{id}")
                     .hasAnyAuthority(ROLE_ADMIN, ROLE_MANAGER)

                .antMatchers(HttpMethod.GET, "/orders**")
                    .hasAnyAuthority(ROLE_ADMIN, ROLE_OPERATOR, ROLE_CLIENT)
                .antMatchers(HttpMethod.POST, "/orders")
                    .hasAnyAuthority(ROLE_ADMIN, ROLE_OPERATOR, ROLE_CLIENT)
                .antMatchers(HttpMethod.DELETE, "/orders/{id}")
                    .hasAnyAuthority(ROLE_ADMIN, ROLE_OPERATOR, ROLE_CLIENT)
                .antMatchers(HttpMethod.PUT, "/orders/{id}")
                    .hasAnyAuthority(ROLE_ADMIN, ROLE_OPERATOR, ROLE_CLIENT);

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
