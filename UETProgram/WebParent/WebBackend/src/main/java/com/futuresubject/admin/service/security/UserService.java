package com.futuresubject.admin.service.security;


import com.futuresubject.admin.dto.user.CredentialsDto;
import com.futuresubject.admin.dto.user.SignUpDto;
import com.futuresubject.admin.dto.user.UserDto;
import com.futuresubject.admin.exceptions.AppException;
import com.futuresubject.admin.mapper.UserMapper;
import com.futuresubject.admin.repository.RoleRepository;
import com.futuresubject.admin.repository.UserRepository;
import com.futuresubject.common.entity.Role;
import com.futuresubject.common.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final RoleRepository roleRepository;

    public UserDto login(CredentialsDto credentialsDto) {
        User user = userRepository.findByLogin(credentialsDto.getLogin())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
            return userMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto userDto) {
        Optional<User> optionalUser = userRepository.findByLogin(userDto.getLogin());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.signUpToUser(userDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));
        user.addRole( roleRepository.findByName("ROLE_ADMIN").get());
        User savedUser = userRepository.save(user);

        return userMapper.toUserDto(savedUser);
    }

    public User findByLogin(String login) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return user;
    }

}
