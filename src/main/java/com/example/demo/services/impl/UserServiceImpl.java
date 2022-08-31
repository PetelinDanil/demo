package com.example.demo.services.impl;

import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers(){
        List<User> allUsers =  userRepository.findAll();
        return allUsers;
    }

    @Override
    public User saveUser(User product){
        return this.userRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id){
        return this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByLogin(String login){
        return this.userRepository.findByLogin(login);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email){
        return this.userRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByPhoneNumber(String phoneNumber){
        return this.userRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public String deleteUser(Long id){
        if(this.userRepository.existsById(id)){
            this.userRepository.deleteById(id);
            return "product removed !! " + id;
        }
        throw new UserNotFoundException(id);
    }

    @Override
    public User editUser(User newUser, Long id) {
        return userRepository.findById(id).map(user -> {
            user.setLogin(newUser.getLogin());
            user.setEmail(newUser.getEmail());
            user.setPhoneNumber(newUser.getPhoneNumber());
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            return userRepository.save(user);
        }).orElseThrow(() -> new UserNotFoundException(id));
    }

}
