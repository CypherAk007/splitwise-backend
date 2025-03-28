package com.backend.splitwise.services;

import com.backend.splitwise.models.User;
import com.backend.splitwise.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public User signup(String name,String phone,String password){
        Optional<User> existingUser = userRepository.findByPhone(phone);
        if(existingUser.isPresent()){
            throw new RuntimeException("User with Phone Number Already Present. Please Login!!");
        }
        User user = new User();
        user.setName(name);
        user.setPhone(phone);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(password));

        return userRepository.save(user);
    }

    public User login(String phone,String password){
        User existingUser = userRepository.findByPhone(phone)
                .orElseThrow(()-> new RuntimeException("User Not Found. Please Signup!!"));

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(bCryptPasswordEncoder.matches(password,existingUser.getPassword())){
         return existingUser;
        }
//        if(password.equals(existingUser.getPassword())){
//            return existingUser;
//        }

        throw new RuntimeException("Invalid Credentials!!!");
    }


}
