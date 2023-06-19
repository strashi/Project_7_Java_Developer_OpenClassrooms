package com.nnk.springboot.controllertest;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.impl.RatingService;
import com.nnk.springboot.service.impl.TradeService;
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
public class TradeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TradeService tradeService;

    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testHome() throws Exception{
        mockMvc.perform(get("/trade/list")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testAddBidForm() throws Exception{
        mockMvc.perform(get("/trade/add")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testValidate() throws Exception{
        mockMvc.perform(post("/trade/validate").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("moodyRating", "moody")
                        .param("sandPRating","sandPRating")
                        .param("fitchRating","fitch")
                        .param("orderNumber","1"))
                .andExpect(status().isFound()).andDo(print())
                .andExpect(view().name("redirect:/trade/list"));
    }
    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testShowUpdateForm() throws Exception{
        Trade trade = new Trade(1,"account","type");
        when(tradeService.findById(1)).thenReturn(Optional.of(trade));
        mockMvc.perform(get("/trade/update/1"))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testUpdateBid() throws Exception{
        Trade trade = new Trade(1,"account","type");
        mockMvc.perform(post("/trade/update/1").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("account", trade.getAccount())
                        .param("type",trade.getType()))
                .andExpect(status().isFound()).andDo(print())
                .andExpect(view().name("redirect:/trade/list"));
    }

    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testDeleteBid() throws Exception{
        Trade trade = new Trade(1,"account","type");
        when(tradeService.findById(1)).thenReturn(Optional.of(trade));
        mockMvc.perform(get("/trade/delete/1"))
                .andExpect(status().isFound()).andDo(print())
                .andExpect(view().name("redirect:/trade/list"));
    }
}
