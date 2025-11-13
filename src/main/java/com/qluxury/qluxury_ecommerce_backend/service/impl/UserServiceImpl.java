package com.qluxury.qluxury_ecommerce_backend.service.impl;

import com.qluxury.qluxury_ecommerce_backend.config.JwtProvider;
import com.qluxury.qluxury_ecommerce_backend.modal.User;
import com.qluxury.qluxury_ecommerce_backend.repository.UserRepository;
import com.qluxury.qluxury_ecommerce_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        return this.findUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("user not found with email - "+email);
        }
        return user;
    }
}
