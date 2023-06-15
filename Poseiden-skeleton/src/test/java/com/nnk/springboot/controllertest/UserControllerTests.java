package com.nnk.springboot.controllertest;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.impl.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;


import javax.validation.ConstraintValidatorContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testHome() throws Exception{

        mockMvc.perform(get("/user/list")).andExpect(status().isOk()).andDo(print());

    }

    @Test
    public void testAddUser() throws Exception{

        mockMvc.perform(get("/user/add")).andExpect(status().isOk()).andDo(print());

    }

    @Test
    public void testValidate() throws Exception{

        User user = new User("User","Password22!","user","USER");

      //  doNothing().when(userService).save(any(User.class));
        when(userService.save(any(User.class))).thenReturn(null);

        mockMvc.perform(post("/user/validate").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", user.getUsername())
                .param("password", user.getPassword())
                .param("fullname", user.getFullname())
                .param("role", user.getRole()))
                .andExpect(status().isFound()).andDo(print())
                .andExpect(view().name("redirect:/user/list"));

       // verify(userService, times(1)).save(any(User.class));
    }
    @Test
    public void testShowUpdateForm() throws Exception{

        User user = new User(1,"User","Password22!","user","USER");
        mockMvc.perform(get("/user/update/1"))
                         .andExpect(status().isFound()).andDo(print());
    }

    @Test
    public void testUpdateUser() throws Exception{
        User user = new User("User","Password22!","user","USER");

        mockMvc.perform(post("/user/update/1").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id","1")
                        .param("username", user.getUsername())
                        .param("password", user.getPassword())
                        .param("fullname", user.getFullname())
                        .param("role", user.getRole()))
                .andExpect(status().isFound()).andDo(print())
                .andExpect(view().name("redirect:/user/list"));
    }

}
