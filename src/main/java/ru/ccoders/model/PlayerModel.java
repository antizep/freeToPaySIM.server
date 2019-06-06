//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.ccoders.model;

import java.util.Set;

public class PlayerModel {
    private int id;
    private int health;
    private float money;
    private Set<ItemModel> items;

    public PlayerModel() {
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

    public float getMoney() {
        return this.money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public Set<ItemModel> getItems() {
        return this.items;
    }

    public void setItems(Set<ItemModel> items) {
        this.items = items;
    }
}
