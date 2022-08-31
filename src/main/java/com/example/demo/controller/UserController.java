package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void init() {

        String[] allBeanNames = applicationContext.getBeanDefinitionNames();
//        for (String beanName : allBeanNames) {
//            log.info(" --- bean '{}'", beanName);
//        }
    }

    @GetMapping("/users")
    private List<UserDto> getAll() {
        List<User> users = userService.getAllUsers();

        List<UserDto> usersDto = users.stream().map(p -> {
            System.out.println(userMapper.toDto(p));
            return userMapper.toDto(p);
        }).collect(Collectors.toList());

        return usersDto;
    }

    @GetMapping("/user/{id}")
    private User getUserId(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping("/user")
    private User newUser(@RequestBody User newUser) {
        return userService.saveUser(newUser);
    }

    @GetMapping("/user")
    private User getUserLogin(@RequestParam String login){
        return userService.getUserByLogin(login);
    }

    @PutMapping("/user/{id}")
    private User editUser(@RequestBody User newUser, @PathVariable Long id) {
        return userService.editUser(newUser, id);
    }

    @DeleteMapping("/user/{id}")
    private void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
