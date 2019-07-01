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
import org.vladimirskoe.project.security.JwtTokenProvider;

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

    private CustomUserDetailsService customUserDetailsService;
    private JwtAuthenticationEntryPoint unauthorizedHandler;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    SecurityConfig(CustomUserDetailsService customUserDetailsService,
            JwtAuthenticationEntryPoint unauthorizedHandler, JwtTokenProvider jwtTokenProvider){
        this.customUserDetailsService = customUserDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(customUserDetailsService, jwtTokenProvider);
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
            throws Exception {
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

                .antMatchers(HttpMethod.GET, "/api/products**")
                    .authenticated()
                .antMatchers(HttpMethod.POST, "/api/products")
                    .hasAnyAuthority(ROLE_ADMIN, ROLE_MANAGER)
                .antMatchers(HttpMethod.DELETE, "/api/products/{id}")
                     .hasAnyAuthority(ROLE_ADMIN, ROLE_MANAGER)
                .antMatchers(HttpMethod.PUT, "/api/products/{id}")
                     .hasAnyAuthority(ROLE_ADMIN, ROLE_MANAGER)

                .antMatchers(HttpMethod.GET, "/api/packages**")
                    .hasAnyAuthority(ROLE_ADMIN, ROLE_MANAGER, ROLE_CLIENT)
                .antMatchers(HttpMethod.POST, "/api/packages")
                    .hasAnyAuthority(ROLE_ADMIN, ROLE_MANAGER)
                .antMatchers(HttpMethod.DELETE, "/api/packages/{id}")
                    .hasAnyAuthority(ROLE_ADMIN, ROLE_MANAGER)
                .antMatchers(HttpMethod.PUT, "/api/packages/{id}")
                     .hasAnyAuthority(ROLE_ADMIN, ROLE_MANAGER)

                .antMatchers(HttpMethod.GET, "/api/orders**")
                    .hasAnyAuthority(ROLE_ADMIN, ROLE_OPERATOR, ROLE_CLIENT)
                .antMatchers(HttpMethod.POST, "/api/orders")
                    .hasAnyAuthority(ROLE_ADMIN, ROLE_OPERATOR, ROLE_CLIENT)
                .antMatchers(HttpMethod.DELETE, "/api/orders/{id}")
                    .hasAnyAuthority(ROLE_ADMIN, ROLE_OPERATOR, ROLE_CLIENT)
                .antMatchers(HttpMethod.PUT, "/api/orders/{id}")
                    .hasAnyAuthority(ROLE_ADMIN, ROLE_OPERATOR, ROLE_CLIENT);

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
