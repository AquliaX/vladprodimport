package org.vladimirskoe.project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.vladimirskoe.project.dto.Login;
import org.vladimirskoe.project.dto.Registration;
import org.vladimirskoe.project.entity.User;
import org.vladimirskoe.project.entity.User.UserRole;
import org.vladimirskoe.project.security.JwtTokenProvider;
import org.vladimirskoe.project.service.AuthService;
import org.vladimirskoe.project.service.UserService;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider tokenProvider;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager,
            JwtTokenProvider tokenProvider, PasswordEncoder passwordEncoder,
            UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    public String authUser(Login login) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getEmail(),
                        login.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenProvider.generateToken(authentication);

    }

    @Override
    public User regUser(Registration registration) {

        User user = new User();

        user.setEmail(registration.getEmail());
        user.setName(registration.getName());
        user.setOrganization(registration.getOrganization());
        user.setPhone(registration.getPhone());

        user.setPassword(registration.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRole(UserRole.CLIENT);

        User registeredUser = userService.addUser(user);

        return registeredUser;
    }
}
