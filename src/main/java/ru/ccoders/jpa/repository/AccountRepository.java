package ru.ccoders.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ccoders.jpa.entity.EntityAccount;

public interface AccountRepository extends CrudRepository<EntityAccount, Integer> {
}
