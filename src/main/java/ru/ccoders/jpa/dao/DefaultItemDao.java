package ru.ccoders.jpa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.ccoders.jpa.entity.EntityDefaultItem;
import ru.ccoders.jpa.repository.ItemRepository;

import java.util.ArrayList;
import java.util.List;

@Service("jpaDefaultItem")
@Repository
public class DefaultItemDao {
    private final ItemRepository repository;

    @Autowired
    public DefaultItemDao(ItemRepository repository) {
        this.repository = repository;
    }

    public EntityDefaultItem save(EntityDefaultItem entityDefaultItem){
        return repository.save(entityDefaultItem);
    }

    public List<EntityDefaultItem> loadAll(){
        List<EntityDefaultItem> result = new ArrayList<>();
        repository.findAll().forEach(result::add);
        return result;
    }
}
