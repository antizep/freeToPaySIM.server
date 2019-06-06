//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.ccoders.utill;

import org.springframework.beans.factory.annotation.Autowired;
import ru.ccoders.jpa.dao.DefaultItemDao;
import ru.ccoders.jpa.entity.EntityDefaultItem;
import ru.ccoders.model.ItemModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemUtil {

    private final DefaultItemDao dao;
    private static List<EntityDefaultItem> defaultItems;

    public ItemUtil(DefaultItemDao dao){

        this.dao = dao;
        if(defaultItems== null){
            defaultItems = dao.loadAll();
            if(defaultItems.size() == 0){
                init();
            }
        }
    }


    public ItemModel getRandomItem(){
        ItemModel im;
        if(defaultItems.size() == 0){
            defaultItems = dao.loadAll();
        }
        Random random = new Random();
        im = new ItemModel(defaultItems.get(random.nextInt(defaultItems.size())),0);
        return im;
    }

    private void init(){
        EntityDefaultItem entityDefaultItem = new EntityDefaultItem();
        entityDefaultItem.setName("KNIFE");
        entityDefaultItem.setDemage(3);
        entityDefaultItem.setSprite("equip_0");
        entityDefaultItem.setHeal(0);
        dao.save(entityDefaultItem);
        defaultItems.add(entityDefaultItem);

        entityDefaultItem = new EntityDefaultItem();
        entityDefaultItem.setName("STONE");
        entityDefaultItem.setDemage(2);
        entityDefaultItem.setSprite("equip_2");
        entityDefaultItem.setHeal(0);
        dao.save(entityDefaultItem);
        defaultItems.add(entityDefaultItem);

        entityDefaultItem = new EntityDefaultItem();
        entityDefaultItem.setName("PLANTAIN");
        entityDefaultItem.setDemage(0);
        entityDefaultItem.setSprite("equip_3");
        entityDefaultItem.setHeal(2);
        dao.save(entityDefaultItem);
        defaultItems.add(entityDefaultItem);

        entityDefaultItem = new EntityDefaultItem();
        entityDefaultItem.setName("AXE");
        entityDefaultItem.setDemage(3);
        entityDefaultItem.setSprite("equip_1");
        entityDefaultItem.setHeal(0);
        dao.save(entityDefaultItem);
        defaultItems.add(entityDefaultItem);
    }
}
