package com.example.demo.business;

import com.example.demo.controllers.ItemController;
import com.example.demo.data.ItemRepository;
import com.example.demo.model.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ItemBusinessServiceTest {

    @InjectMocks
    private ItemBusinessService businessService;

    @Mock
    private ItemRepository repository;

    @Test
    public void should_return_all_items_with_value(){
        when(repository.findAll()).thenReturn(Arrays.asList(
                new Item(2,"Item2",10.0,10),
                new Item(3,"Item3",20.0,20))
        );

        List<Item> items = businessService.retrieveAllItems();

        assertEquals(100.0, items.get(0).getValue());
        assertEquals(400.0, items.get(1).getValue());

    }

}
