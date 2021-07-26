package rga.test.task.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rga.test.task.entities.Item;
import rga.test.task.exceptions.ItemNotFoundException;
import rga.test.task.repositories.ItemsRepository;

import java.util.List;

@Service
public class ItemsService {

    private ItemsRepository itemsRepository;

    @Autowired
    public void setItemsRepository(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public List<Item> findAll(){
        return itemsRepository.findAll();
    }

    public Item findById(Long id){
        return itemsRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Attention! Such an item cannot be found!"));
    }

    public Item saveOrUpdate(Item item){
        return itemsRepository.save(item);
    }

    public void deleteById(Long id){
        itemsRepository.deleteById(id);
    }

    public void deleteAll(){
        itemsRepository.deleteAll();
    }

    public boolean existsById(Long id){
        return itemsRepository.existsById(id);
    }

}
