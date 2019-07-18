package ru.ccoders.jpa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.ccoders.jpa.entity.EntityAccount;
import ru.ccoders.jpa.repository.AccountRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("jpaAccount")
@Repository
public class AccountDao {
    private static List<EntityAccount> cacheAccounts = new ArrayList<>();
    final AccountRepository repository;
    @Autowired
    public AccountDao(AccountRepository repository) {
        this.repository = repository;
    }
    public EntityAccount save(EntityAccount account){
        return repository.save(account);
    }
    public EntityAccount load(int id){
        for (EntityAccount account :cacheAccounts){
            if(account.getId() == id){
                return account;
            }
        }
        EntityAccount account = repository.findById(id).get();
        cacheAccounts.add(account);
        return account;
    }
}
