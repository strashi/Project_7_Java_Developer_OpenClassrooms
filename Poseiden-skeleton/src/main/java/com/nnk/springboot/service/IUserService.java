package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    public List<User> findAll();

    public User save(User user) ;

    public Optional<User> findById(Integer id);

    public void delete(User user);
}
