package com.ibm.fsd.training.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ibm.fsd.training.model.User;
import com.ibm.fsd.training.repository.ReactiveUserRepository;

import reactor.core.publisher.Mono;

@Service
public class PixogramUserDetailsService implements ReactiveUserDetailsService {

    @Autowired
    private ReactiveUserRepository reactiveUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public Mono<UserDetails> findByUsername(String username) {
        Mono<UserDetails> data = reactiveUserRepository.findByUsername(username);
        return data.cast(UserDetails.class);
    }
    
    public Mono<User> registerNewUserAccount(User accountDto) {
		/*
		 * if (emailExist(accountDto.getEmail())) { throw new EmailExistsException(
		 * "There is an account with that email adress:" + accountDto.getEmail()); }
		 */
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername(accountDto.getUsername());
        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        System.out.println("User Data : "+user);
        return reactiveUserRepository.save(user);
    }

}

