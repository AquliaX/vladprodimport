package org.vladimirskoe.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.vladimirskoe.project.dao.UserRepository;
import org.vladimirskoe.project.entity.User;
import org.vladimirskoe.project.exception.NullObjectException;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new NullObjectException("User not found with this email")
                );

        return new UserPrincipal(user.getId(), user.getEmail(), user.getPassword(), user.getRole());
    }

    public UserDetails loadUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NullObjectException("User not found with this id")
        );

        return new UserPrincipal(user.getId(), user.getEmail(), user.getPassword(), user.getRole());
    }
}
