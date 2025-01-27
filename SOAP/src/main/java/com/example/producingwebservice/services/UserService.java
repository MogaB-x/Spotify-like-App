package com.example.producingwebservice.services;

import com.example.producingwebservice.entities.User;
import com.example.producingwebservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            return null;
        return  user.get();
    }

    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void create(User user) {
        userRepository.save(user);
    }

    public User update(User user) {
        User temp = userRepository.findByUsername(user.getUsername());
        if (temp != null) {
            userRepository.delete(temp);
        }
        userRepository.save(user);
        return user;
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }
}
