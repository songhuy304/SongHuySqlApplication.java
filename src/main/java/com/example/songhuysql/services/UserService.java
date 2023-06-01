package com.example.songhuysql.services;

import com.example.songhuysql.entity.User;
import com.example.songhuysql.repository.IUserRepository;


import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }
}
