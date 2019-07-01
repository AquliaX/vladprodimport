package org.vladimirskoe.project.service;

import org.vladimirskoe.project.entity.User;

public interface UserService {

    User addUser(User user);

    User findUserByEmail(String email);

    Boolean existsUserByEmail(String email);
}
