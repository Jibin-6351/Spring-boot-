package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.UserResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;

import java.util.Optional;

public interface UserService {
    String getText();
    UserResponseDTO addUser(User user);
    String signin(User user);
    Optional<User> getUser(Long id);
    void updateUserPassword(User user);
    void updateDetails(User user);
    CsrfToken generateToken(HttpServletRequest request);

}
