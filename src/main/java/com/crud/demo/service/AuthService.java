package com.crud.demo.service;

import com.crud.demo.dto.LoginRequest;
import com.crud.demo.model.User;
import com.crud.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public  String authenticate(LoginRequest request){
        // find user in db
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(()-> new BadCredentialsException("Invalid username or password"));
        //compare passwords
        if(!passwordEncoder.matches(request.password(), user.getPassword())){
            throw new BadCredentialsException("Invalid username or password");

        }

        return jwtService.generateToken(user.getUsername(), user.getRole());
    }
}
