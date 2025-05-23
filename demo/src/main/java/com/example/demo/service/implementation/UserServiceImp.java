package com.example.demo.service.implementation;

import com.example.demo.Exception.InvalidCredentialsException;
import com.example.demo.Utils.JwtUtil;
import com.example.demo.domain.Token;
import com.example.demo.domain.User;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private UserRepository userRepository;
    private TokenRepository tokenRepository;

    private JwtUtil jwtUtil;


    @Override
    public String getText() {
        return "User Api working";
    }

    @Override
    public UserResponseDTO addUser(User user) {


        User userData = userRepository.save(user);
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setFirstName(userData.getFirstName());
        userResponseDTO.setLastName(userData.getLastName());
        userResponseDTO.setEmail(userData.getEmail());
        userResponseDTO.setDob(userData.getDob());
        userResponseDTO.setGender(userData.getGender());
        userResponseDTO.setCountryCode(userData.getCountryCode());
        userResponseDTO.setMobileNumber(userData.getMobileNumber());
        return userResponseDTO;

    }

    @Override
    public String signin(User user) {

        Optional<User> finduser = userRepository.findByEmail(user.getEmail());
        if (finduser.isPresent()) {
            if (Objects.equals(finduser.get().getPassword(), user.getPassword())) {
                String token = jwtUtil.generateToken(finduser.get().getEmail());
                User updateUser = finduser.get();
                updateUser.setLastLogin(LocalDateTime.now());
                updateUser.setToken_valid("valid");
                userRepository.save(updateUser);
                Token token1 = new Token();
                token1.setToken(token);
                token1.setUserEmail(finduser.get().getEmail());
                tokenRepository.save(token1);

                return token;
            } else {
                throw new InvalidCredentialsException("Invalid Password");
            }
        } else {
            throw new InvalidCredentialsException("User not found ");
        }

    }

    @Override
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void updateUserPassword(User user) {

        Optional<User> user1 = userRepository.findById(user.getId());
        if (user1.isPresent()) {
            User user2 = user1.get();
            user2.setPassword(user.getPassword());
            user2.setUpdatedDate(LocalDateTime.now());
            userRepository.save(user2);
        } else {
            throw new InvalidCredentialsException("User not found");
        }

    }

    @Override
    public void updateDetails(User user) {
        Optional<User> updateUser = userRepository.findById(user.getId());
        if (updateUser.isPresent()) {
            User userUpdate1 = updateUser.get();
            if (user.getFirstName() != null) {
                userUpdate1.setFirstName(user.getFirstName());
            }
            if (user.getLastName() != null) {
                userUpdate1.setLastName(user.getLastName());
            }
            if (user.getEmail() != null) {
                userUpdate1.setEmail(user.getEmail());
            }
            if (user.getDob() != null) {
                userUpdate1.setDob(user.getDob());
            }
            if (user.getCountryCode() != null) {
                userUpdate1.setCountryCode(user.getCountryCode());
            }
            if (user.getMobileNumber() != null) {
                userUpdate1.setMobileNumber(user.getMobileNumber());
            }
            userUpdate1.setUpdatedDate(LocalDateTime.now());
            userRepository.save(userUpdate1);
        } else {
            throw new InvalidCredentialsException("User not found with id :" + user.getId());
        }
    }

    @Override
    public String logout(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
            String email = jwtUtil.extractUsername(token);
            userRepository.findByEmail(email).ifPresent(user -> {
                user.setToken_valid("invalid");
            });
            tokenRepository.deleteByToken(token);
        }
        return "Signout successfull";
    }
}
