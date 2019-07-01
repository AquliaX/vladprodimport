package org.vladimirskoe.project.service;

import org.vladimirskoe.project.dto.Login;
import org.vladimirskoe.project.dto.Registration;
import org.vladimirskoe.project.entity.User;

public interface AuthService {

    String authUser(Login login);

    User regUser(Registration registration);

}
