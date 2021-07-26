package rga.test.task.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rga.test.task.entities.Item;
import rga.test.task.exceptions.ItemNotFoundException;
import rga.test.task.services.ItemsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
public class RestItemsController {

    private ItemsService itemsService;

    @Autowired
    public RestItemsController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @GetMapping
    public List<Item> getAll(){
        return itemsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneById(@PathVariable Long id){
        if (!itemsService.existsById(id)){
            throw new ItemNotFoundException("Attention! Such an item does not exist!");
        }
        return new ResponseEntity<>(itemsService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item save(@RequestBody Item item){
        if (item.getId() != null){
            item.setId(null);
        }
        return itemsService.saveOrUpdate(item);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Item item){
        if (item.getId() == null || !itemsService.existsById(item.getId())){
            throw new ItemNotFoundException("Attention! Such an item with id =  " + item.getId() + " doesn't exist!");
        }
        if (item.getScore() == 0){
            return new ResponseEntity<>("Attention! Item cannot be without score!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(itemsService.saveOrUpdate(item), HttpStatus.OK);
    }

    @DeleteMapping
    public String deleteAll(){
        itemsService.deleteAll();
        return "Attention! All items have just been deleted!";
    }

    @DeleteMapping("/{id}")
    public String deleteOneById(@PathVariable Long id){
        itemsService.deleteById(id);
        return "Attention! Item with an id =  " + id + " has just been deleted!";
    }
}
