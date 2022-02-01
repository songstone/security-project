package com.example.securityproject.service;

import com.example.securityproject.exception.AlreadyRegisteredUserException;
import com.example.securityproject.domain.user.User;
import com.example.securityproject.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User signUp(String username, String password){
        if(userRepository.findByUsername(username) != null) {
            throw new AlreadyRegisteredUserException();
        }
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .authority("ROLE_USER")
                .build();
        return userRepository.save(user);
    }

    public User signUpAdmin(String username, String password){
        if(userRepository.findByUsername(username) != null){
            throw new AlreadyRegisteredUserException();
        }
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .authority("ROLE_ADMIN")
                .build();
        return userRepository.save(user);
    }

    public User findByUserName(String userName){
        return userRepository.findByUsername(userName);
    }
}
