package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailService.class);

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("loadUserByUsername sollicité de UserDetailService");
        try {
            User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not present"));
            logger.info("loadUserByUsername effectuée de UserDetailService");
            return user;
        }catch (Exception e) {
            logger.error("Erreur au loadUserByUsername de UserDetailService", e);
            return null;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
