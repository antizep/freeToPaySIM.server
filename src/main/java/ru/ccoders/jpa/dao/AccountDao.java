package ru.ccoders.jpa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.ccoders.jpa.entity.EntityAccount;
import ru.ccoders.jpa.repository.AccountRepository;

@Service("jpaAccount")
@Repository
public class AccountDao {
    final AccountRepository repository;
    @Autowired
    public AccountDao(AccountRepository repository) {
        this.repository = repository;
    }
    public EntityAccount save(EntityAccount account){
        return repository.save(account);
    }
    public EntityAccount load(int id){
        return repository.findById(id).get();
    }
}
