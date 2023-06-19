package com.nnk.springboot.controllertest;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.impl.BidListService;
import com.nnk.springboot.service.impl.CurvePointService;
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
public class CurveControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurvePointService curvePointService;

    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testHome() throws Exception{
        mockMvc.perform(get("/curvePoint/list")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testAddBidForm() throws Exception{
        mockMvc.perform(get("/curvePoint/add")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testValidate() throws Exception{
        mockMvc.perform(post("/curvePoint/validate").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("curveId", "1")
                        .param("term","1")
                        .param("value","1"))
                .andExpect(status().isFound()).andDo(print())
                .andExpect(view().name("redirect:/curvePoint/list"));
    }
    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testShowUpdateForm() throws Exception{
        CurvePoint curvePoint = new CurvePoint(1,1,1d,1d);
        when(curvePointService.findById(1)).thenReturn(Optional.of(curvePoint));
        mockMvc.perform(get("/curvePoint/update/1"))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testUpdateBid() throws Exception{
        CurvePoint curvePoint = new CurvePoint(1,1,1d,1d);

        mockMvc.perform(post("/curvePoint/update/1").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("curveId", String.valueOf(curvePoint.getCurveId()))
                        .param("term", String.valueOf(curvePoint.getTerm()))
                        .param("value", String.valueOf(curvePoint.getValue()))                        )
                .andExpect(status().isFound()).andDo(print())
                .andExpect(view().name("redirect:/curvePoint/list"));
    }

    @Test
    @WithMockUser(username = "user", password = "Password22!", authorities= {"USER"})
    public void testDeleteBid() throws Exception{
        CurvePoint curvePoint = new CurvePoint(1,1,1d,1d);
        when(curvePointService.findById(1)).thenReturn(Optional.of(curvePoint));
        mockMvc.perform(get("/curvePoint/delete/1"))
                .andExpect(status().isFound()).andDo(print())
                .andExpect(view().name("redirect:/curvePoint/list"));
    }
}
