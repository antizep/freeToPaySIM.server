package ru.ccoders.jpa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.ccoders.jpa.entity.EntityItems;
import ru.ccoders.jpa.repository.ItemRepository;

import java.util.List;

@Service("jpaItem")
@Repository
public class ItemDao {
    final
    ItemRepository repository;
    @Autowired
    public ItemDao(ItemRepository repository) {
        this.repository = repository;
    }
    public List<EntityItems> savaAll(List<EntityItems> entityItemsList){
        repository.saveAll(entityItemsList);
        return entityItemsList;
    }

}
