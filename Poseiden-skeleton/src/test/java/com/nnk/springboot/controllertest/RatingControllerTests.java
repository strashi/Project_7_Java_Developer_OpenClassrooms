package com.nnk.springboot.controllertest;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.impl.CurvePointService;
import com.nnk.springboot.service.impl.RatingService;
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
public class RatingControllerTests {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RatingService ratingService;

    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testHome() throws Exception{
        mockMvc.perform(get("/rating/list")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testAddBidForm() throws Exception{
        mockMvc.perform(get("/rating/add")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testValidate() throws Exception{
        mockMvc.perform(post("/rating/validate").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("moodyRating", "moody")
                        .param("sandPRating","sandPRating")
                        .param("fitchRating","fitch")
                        .param("orderNumber","1"))
                .andExpect(status().isFound()).andDo(print())
                .andExpect(view().name("redirect:/rating/list"));
    }
    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testShowUpdateForm() throws Exception{
        Rating rating = new Rating(1,"moody","sandPRating","fitch",1);
        when(ratingService.findById(1)).thenReturn(Optional.of(rating));
        mockMvc.perform(get("/rating/update/1"))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testUpdateBid() throws Exception{
        Rating rating = new Rating(1,"moody","sandPRating","fitch",1);
        mockMvc.perform(post("/rating/update/1").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("moodyRating", rating.getMoodysRating())
                .param("sandPRating",rating.getSandPRating())
                .param("fitchRating",rating.getFitchRating())
                .param("orderNumber",String.valueOf(rating.getOrderNumber())))
                .andExpect(status().isFound()).andDo(print())
                .andExpect(view().name("redirect:/rating/list"));
    }

    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testDeleteBid() throws Exception{
        Rating rating = new Rating(1,"moody","sandPRating","fitch",1);
        when(ratingService.findById(1)).thenReturn(Optional.of(rating));
        mockMvc.perform(get("/rating/delete/1"))
                .andExpect(status().isFound()).andDo(print())
                .andExpect(view().name("redirect:/rating/list"));
    }
}
