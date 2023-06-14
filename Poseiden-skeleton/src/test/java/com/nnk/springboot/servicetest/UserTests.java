package com.nnk.springboot.servicetest;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.impl.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void userTest(){
        User user = new User(1,"user", "Abcd123/", "User","USER");

        //Save
        when(userRepository.save(user)).thenReturn(user);
        user = userService.save(user);
        Assert.assertNotNull(user.getId());
        Assert.assertEquals(user.getUsername(), "user");

        // Update
        user.setUsername("username");
        when(userRepository.save(user)).thenReturn(user);
        user = userService.save(user);
        Assert.assertEquals(user.getUsername(), "username");

        // Find
        List<User> listTest = new ArrayList<>();
        listTest.add(user);
        when(userRepository.findAll()).thenReturn(listTest);
        List<User> listResult = userService.findAll();
        Assert.assertTrue(listResult.size() > 0);

        // Delete
        doNothing().when(userRepository).delete(user);
        userService.delete(user);

        verify(userRepository,times(1)).delete(user);

    }
}
