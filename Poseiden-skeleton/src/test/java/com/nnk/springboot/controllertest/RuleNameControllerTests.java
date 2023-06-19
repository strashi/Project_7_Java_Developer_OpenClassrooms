package com.nnk.springboot.controllertest;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.impl.RatingService;
import com.nnk.springboot.service.impl.RuleNameService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class RuleNameControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RuleNameService ruleNameService;

    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testHome() throws Exception{
        mockMvc.perform(get("/ruleName/list")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testAddBidForm() throws Exception{
        mockMvc.perform(get("/ruleName/add")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testValidate() throws Exception{
        mockMvc.perform(post("/ruleName/validate").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("description", "description")
                        .param("json","json")
                        .param("template","template")
                        .param("sqlStr","sqlStr")
                        .param("sqlPart","sqlPart"))
                .andExpect(status().isFound()).andDo(print())
                .andExpect(view().name("redirect:/ruleName/list"));
    }
    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testShowUpdateForm() throws Exception{
        RuleName ruleName = new RuleName(1,"name","description","json","template","sqlStr","sqlPart");
        when(ruleNameService.findById(1)).thenReturn(Optional.of(ruleName));
        mockMvc.perform(get("/ruleName/update/1"))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testUpdateBid() throws Exception{
        RuleName ruleName = new RuleName(1,"name","description","json","template","sqlStr","sqlPart");
        mockMvc.perform(post("/ruleName/update/1").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("description", ruleName.getName())
                        .param("json",ruleName.getJson())
                        .param("template",ruleName.getTemplate())
                        .param("sqlStr",ruleName.getSqlStr())
                        .param("sqlPart",ruleName.getSqlPart()))
                .andExpect(status().isFound()).andDo(print())
                .andExpect(view().name("redirect:/ruleName/list"));
    }

    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testDeleteBid() throws Exception{
        RuleName ruleName = new RuleName(1,"name","description","json","template","sqlStr","sqlPart");
        when(ruleNameService.findById(1)).thenReturn(Optional.of(ruleName));
        mockMvc.perform(get("/ruleName/delete/1"))
                .andExpect(status().isFound()).andDo(print())
                .andExpect(view().name("redirect:/ruleName/list"));
    }
}
