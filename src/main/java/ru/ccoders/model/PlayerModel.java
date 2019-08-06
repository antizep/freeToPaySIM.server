//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.ccoders.model;

import ru.ccoders.jpa.entity.EntityAccount;
import ru.ccoders.jpa.entity.EntityItems;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PlayerModel {
    private int id;
    private int health;
    private int money;
    private Set<ItemModel> items;

    public PlayerModel() {
    }
    public PlayerModel(EntityAccount account){
        id = account.getId();
        health = account.getHeal();
        money = account.getMoney();
        items = new HashSet<>();
        for (EntityItems i: account.getItemsById()){
            items.add(new ItemModel(i.getDefaultItemByDefaultItem(),i.getCount()));
        }
    }
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return this.money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Set<ItemModel> getItems() {
        return this.items;
    }

    public void setItems(Set<ItemModel> items) {
        this.items = items;
    }

    public ItemModel getItemByItemId(int itemId) throws Exception {
        for(ItemModel item: items){
            if(item.getId() == itemId){
                return item;
            }
        }
        throw new Exception("Item not found");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerModel)) return false;
        PlayerModel model = (PlayerModel) o;
        return id == model.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
