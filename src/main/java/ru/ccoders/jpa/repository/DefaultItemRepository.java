package ru.ccoders.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ccoders.jpa.entity.EntityDefaultItem;

public interface DefaultItemRepository extends CrudRepository<EntityDefaultItem, Integer> {
}
