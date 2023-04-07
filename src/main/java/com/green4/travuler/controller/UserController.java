package com.green4.travuler.controller;

import com.green4.travuler.model.User;
import com.green4.travuler.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/travuler")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public User create(@RequestBody User user) {
        System.out.println("create 시도");
        return userRepository.save(user);
    }

    @GetMapping("/user/list")
    public List<User> readAll() {

        List<User> userList = userRepository.findAll();

        userList.forEach(user -> {
            System.out.println("user print: "+ user);
        });

        return userList;
    }

    @GetMapping("/user/{id}")
    public User readOne(@PathVariable Long id) {

        Optional<User> userOptional = userRepository.findById(id);
        userOptional.ifPresent(System.out::println);

        User userModelForResponse = userOptional.get();

        return userModelForResponse;
    }
}
