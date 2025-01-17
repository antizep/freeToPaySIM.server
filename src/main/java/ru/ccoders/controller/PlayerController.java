//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.ccoders.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.encoder.EchoEncoder;
import ru.ccoders.jpa.dao.AccountDao;
import ru.ccoders.jpa.entity.EntityAccount;
import ru.ccoders.jpa.entity.EntityDefaultItem;
import ru.ccoders.jpa.entity.EntityItems;
import ru.ccoders.model.ItemModel;
import ru.ccoders.model.PlayerModel;
import ru.ccoders.utill.AIUtil;
import ru.ccoders.utill.PlayerUtill;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Api(
        value = "/player",
        description = "Player Controller"
)
@RestController
@RequestMapping({"/player"})
@Slf4j
public class PlayerController {
    private final ApplicationContext ctx;
    private final AccountDao accountDao;
    private PlayerUtill playerUtill = new PlayerUtill();
    ItemController itemController;
    
    @Autowired
    public PlayerController(ApplicationContext ctx) {
        this.ctx = ctx;
        itemController  = new ItemController(ctx);
        accountDao = (AccountDao) ctx.getBean("jpaAccount");
    }

    @ApiOperation("Load game profile")
    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    public PlayerModel load(@PathVariable int id) {
    	log.info("load(@PathVariable int "+id+")");
    	EntityAccount account = accountDao.load(id);
    	if(account == null) {
    		try {
    			return registration();
    		}catch (Exception e) {
    			log.warn("не удалось создать аккаунт",e);
				return null;
			}
    	}else {
            return new PlayerModel(account);

		}
    }
    
    @ApiOperation("vine game profile")
    @RequestMapping(
            value = {"/{id}/vine"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    public PlayerModel vine(@PathVariable int id) throws Exception {
    	log.info("load(@PathVariable int "+id+")");
    	EntityAccount account = accountDao.load(id);
    	account.setHeal(100);
    	account.getItemsById().clear();
    	Collection<EntityItems> items = account.getItemsById();

        EntityItems entityItems = new EntityItems();
        entityItems.setCount(10);
        entityItems.setDefaultItemByDefaultItem(itemController.getItemByName("STONE"));
        entityItems.setAccountByAccount(account);
        items.add(entityItems);
        entityItems = new EntityItems();
        entityItems.setCount(10);
        entityItems.setDefaultItemByDefaultItem(itemController.getItemByName("PLANTAIN"));
        entityItems.setAccountByAccount(account);

        items.add(entityItems);
        
        account.setItemsById(items);
        
        return new PlayerModel(accountDao.save(account));
    }

    @ApiOperation("Создать профиль")
    @RequestMapping(
            value = {"/reg"},
            method = {RequestMethod.POST},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    public PlayerModel registration() throws Exception {
    	log.info("registration()");
        

        EntityAccount account = new EntityAccount();
        account.setHeal(100);
        account.setMoney(100);
        accountDao.save(account);
        List<EntityItems> items  = new ArrayList<>();

        EntityItems entityItems = new EntityItems();
        entityItems.setCount(10);
        entityItems.setDefaultItemByDefaultItem(itemController.getItemByName("STONE"));
        entityItems.setAccountByAccount(account);
        items.add(entityItems);
        entityItems = new EntityItems();
        entityItems.setCount(10);
        entityItems.setDefaultItemByDefaultItem(itemController.getItemByName("PLANTAIN"));
        entityItems.setAccountByAccount(account);

        items.add(entityItems);

        itemController.addAll(items);

        account.setItemsById(items);

        return new PlayerModel(account);

    }
    @ApiOperation("Создать AI")
    @RequestMapping(
            value = {"/enemyAI"},
            method = {RequestMethod.POST},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    public PlayerModel createEnemy(@RequestParam String power){
    	log.info("createEnemy(@RequestParam String "+power+")");
        AIUtil aiUtil = new AIUtil(new ItemController(ctx));
        return aiUtil.createEnemy(Float.parseFloat(power));
    }

    @ApiOperation("Обновить здоровье")
    @RequestMapping(
            value = {"/{id}/heal"},
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public void updateHeal(@PathVariable int id,@RequestParam String heal){
    	log.info("updateHeal(@PathVariable int "+id+",@RequestParam String "+heal+")");
        int healI = Integer.parseInt(heal);
        EntityAccount entityAccount = accountDao.load(id);
        entityAccount.setHeal(healI);
        accountDao.save(entityAccount);
    }

    @ApiOperation("Использовать предмет")
    @RequestMapping(
            value = "/use/{user}/{item}",
            method = {RequestMethod.POST},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    public void useItem(@PathVariable int user,@PathVariable int item){
    	log.info("useItem(@PathVariable int "+user+",@PathVariable int "+item+")");
        EntityAccount account = accountDao.load(user);
        Collection<EntityItems> itemsSet = account.getItemsById();
        for (EntityItems items: itemsSet){
            int itemID = items.getDefaultItemByDefaultItem().getId();
            if(itemID == item){
                int count = items.getCount();
                    items.setCount(count-1);
                }
                accountDao.save(account);
                return;
            }
    }
    
    
    @ApiOperation("Добавить пременты по списку id дефолтных ")
    @RequestMapping(
    		value = "/{user}/addIems",
    		method = {RequestMethod.POST},
    		produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    public void addItemUserFromDefoltItemIds(@RequestParam List<String> idsDefoltItem,@PathVariable int userId) {
    	log.info("addItemUserFromDefoltItemIds "+ idsDefoltItem+"|"+userId);

    	EntityAccount account = accountDao.load(userId);
    	Map<Integer,Integer> defMapCount = new HashMap();
    	for (String idS : idsDefoltItem) {
    		try {
				
			
    		if(defMapCount.containsKey(idS)) {
    			defMapCount.put(Integer.parseInt(idS), defMapCount.get(idS)+1);
    		}else {
    			defMapCount.put(Integer.parseInt(idS), 1);
    		}
    		} catch (Exception e) {
				log.warn("Не удалось преобразовать ID",e);
			}
    	}	
    	
		Collection<EntityItems> eItems = account.getItemsById();
		for (EntityItems items : eItems) {
			int idDef = items.getDefaultItemByDefaultItem().getId();
			if(defMapCount.containsKey(idDef)) {
			int c = defMapCount.get(idDef);
			
			if(c>0) {
				items.addCount(c);;
			}
			defMapCount.remove(idDef);
			}
		}
		Set<Integer> keys = defMapCount.keySet(); 
		if(!defMapCount.isEmpty()) {
			for (Integer integer : keys) {
				EntityItems entityItems = new EntityItems();
				entityItems.setAccountByAccount(account);
				entityItems.setCount(defMapCount.get(integer));
				entityItems.setDefaultItemByDefaultItem(itemController.getItemById(String.valueOf(integer)));
				eItems.add(entityItems);
			}
		}
		itemController.addAll(new ArrayList<EntityItems>(eItems));
    }

}
