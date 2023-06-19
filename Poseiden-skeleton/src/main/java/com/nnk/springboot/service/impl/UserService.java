package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Implementation of service for user
 */
@Service
public class UserService implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        logger.debug("findAll() sollicité de UserService");
        try {
            logger.info("findAll() effectuée de UserService");
            return userRepository.findAll();
        }catch (Exception e){
            logger.error("Erreur au findAll() de UserService", e);
            return null;
        }
    }

    public User save(User user) {
        logger.debug("save() sollicité de UserService");
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            logger.info("save() effectuée de UserService");
            return userRepository.save(user);
        }catch (Exception e){
            logger.error("Erreur au save() de UserService", e);
            return null;
        }
    }

    public Optional<User> findById(Integer id) {
        logger.debug("findById() sollicité de UserService");
        try {
            logger.info("findById() effectuée de UserService");
            return userRepository.findById(id);
        }catch (Exception e){
            logger.error("Erreur au findById() de UserService", e);
            return null;
        }
    }

    public void delete(User user) {
        logger.debug("delete() sollicité de UserService");
        try{
            logger.info("delete() effectuée de UserService");
            userRepository.delete(user);
        }catch(Exception e) {
            logger.error("Erreur au delete() de UserService", e);
        }
    }
}
