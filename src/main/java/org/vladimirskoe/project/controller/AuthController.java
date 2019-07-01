package org.vladimirskoe.project.controller;

import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.vladimirskoe.project.dto.Login;
import org.vladimirskoe.project.dto.Registration;
import org.vladimirskoe.project.entity.User;
import org.vladimirskoe.project.payload.ApiResponse;
import org.vladimirskoe.project.payload.JwtAuthenticationResponse;
import org.vladimirskoe.project.service.AuthService;
import org.vladimirskoe.project.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private UserService userService;
    private AuthService authService;

    @Autowired
    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody Login login) {

        return ResponseEntity.ok(new JwtAuthenticationResponse(authService.authUser(login)));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Registration registration) {

        if (userService.existsUserByEmail(registration.getEmail())) {
            return new ResponseEntity
                    (new ApiResponse(false, "Email Address already in use!"),
                            HttpStatus.UNAUTHORIZED);
        }

        if (!registration.getPassword().equals(registration.getConfirmPassword())) {
            return new ResponseEntity
                    (new ApiResponse(false, "Passwords not match!"),
                            HttpStatus.UNAUTHORIZED);
        }

        User registeredUser = authService.regUser(registration);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(registeredUser.getEmail()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully"));
    }
}
