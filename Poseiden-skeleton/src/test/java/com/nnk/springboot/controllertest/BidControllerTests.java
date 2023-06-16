package com.nnk.springboot.controllertest;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.impl.BidListService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

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
public class BidControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BidListService bidListService;

    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testHome() throws Exception{
        mockMvc.perform(get("/bidList/list")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testAddBidForm() throws Exception{
        mockMvc.perform(get("/bidList/add")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testValidate() throws Exception{
        mockMvc.perform(post("/bidList/validate").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("account", "Account")
                        .param("type","Type")
                        .param("bidQuantity","2"))
                        .andExpect(status().isFound()).andDo(print())
                .andExpect(view().name("redirect:/bidList/list"));
    }
    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testShowUpdateForm() throws Exception{
        BidList bid = new BidList(1,"Account","Type",2d);
        when(bidListService.findById(1)).thenReturn(Optional.of(bid));
        mockMvc.perform(get("/bidList/update/1"))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testUpdateBid() throws Exception{
        BidList bid = new BidList(1,"Account","Type",2d);

        mockMvc.perform(post("/bidList/update/1").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("account", bid.getAccount())
                        .param("type", bid.getType())
                        .param("bidQuantity", String.valueOf(bid.getBidQuantity()))                        )
                .andExpect(status().isFound()).andDo(print())
                .andExpect(view().name("redirect:/bidList/list"));
    }

    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testDeleteBid() throws Exception{
        BidList bid = new BidList(1,"Account","Type",2d);
        when(bidListService.findById(1)).thenReturn(Optional.of(bid));
        mockMvc.perform(get("/bidList/delete/1"))
                .andExpect(status().isFound()).andDo(print())
                .andExpect(view().name("redirect:/bidList/list"));
    }
}
