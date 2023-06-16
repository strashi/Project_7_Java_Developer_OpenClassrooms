package com.nnk.springboot.controllertest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLogin() throws Exception {

        mockMvc.perform(get("login")).andExpect(status().isFound()).andDo(print());
    }
    @Test
    public void testGetAllUsersArticles() throws Exception {

        mockMvc.perform(get("secure/article-details")).andExpect(status().isFound()).andDo(print());
    }

    @Test
    public void testError() throws Exception {

        mockMvc.perform(get("error")).andExpect(status().isFound()).andDo(print());
    }
}
