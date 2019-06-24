package ru.ccoders.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ccoders.jpa.entity.EntityItems;

public interface ItemRepository extends CrudRepository<EntityItems, Integer> {

}
