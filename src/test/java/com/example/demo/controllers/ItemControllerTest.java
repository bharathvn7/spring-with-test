package com.example.demo.controllers;

import com.example.demo.business.ItemBusinessService;
import com.example.demo.model.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemBusinessService businessService;

    @Test
    public void dummyItems_Test() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/dummy-item")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Ball\",\"price\":10.0,\"quantity\":100}"))
                .andReturn();
    }

    @Test
    public void itemsFromBusinessService_Test() throws Exception {

        when(businessService.retrieveHarcodedItem()).thenReturn(new Item(2,"Item2",10.0,10));
        RequestBuilder request = MockMvcRequestBuilders
                .get("/item")
                .accept(MediaType.APPLICATION_JSON);

        /*
        Example for post
        RequestBuilder request = MockMvcRequestBuilders
                .post("/item")
                .accept(MediaType.APPLICATION_JSON);
                .content("{id:1,"name":"Ball","price":10.0,"quantity":10}");
                .contentType(MediaType.APPLICATION_JSON);

         MvcResult result = mockMvc.perform(requestBuilder)
                                .andExpect(status().isCreated())
                                .andExpect(header().string("location",containsString("/item/")))
                                .andReturn();
         */

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{id:2,name:Item2,price:10.0,quantity:10}"))
                .andReturn();
    }

    @Test
    public void should_retrieve_all_items() throws Exception {

        when(businessService.retrieveAllItems()).thenReturn(
                Arrays.asList(new Item(2,"Item2",10.0,10),
                        new Item(3,"Item3",20.0,20)
                )
        );
        RequestBuilder request = MockMvcRequestBuilders
                .get("/items")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{id:2,name:Item2,price:10.0,quantity:10},{id:3,name:Item3,price:20.0,quantity:20}]"))
                .andReturn();
    }
}
