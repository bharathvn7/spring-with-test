package com.example.demo.business;

import com.example.demo.data.ItemRepository;
import com.example.demo.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemBusinessService {

    @Autowired
    private ItemRepository repository;

    public Item retrieveHarcodedItem(){
        return new Item(1,"Ball",10.0,100);
    }

    public List<Item> retrieveAllItems(){
        List<Item> items = repository.findAll();
        for(Item item:items){
            item.setValue(item.getPrice() * item.getQuantity());
        }
        return repository.findAll();
    }
}
