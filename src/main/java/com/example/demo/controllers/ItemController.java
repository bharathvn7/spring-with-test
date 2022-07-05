package com.example.demo.controllers;

import com.example.demo.business.ItemBusinessService;
import com.example.demo.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemBusinessService businessService;

    @GetMapping("/dummy-item")
    public Item getDummyItem(){

        return new Item(1,"Ball",10.0,100);

    }

    @GetMapping("/item")
    public Item itemFromBusinessService(){
        return businessService.retrieveHarcodedItem();
    }

    @GetMapping("/items")
    public List<Item> retriveveAllItems(){
        return businessService.retrieveAllItems();
    }

}
