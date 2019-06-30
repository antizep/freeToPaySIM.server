//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.ccoders.model;

import ru.ccoders.jpa.entity.EntityDefaultItem;

import java.util.Objects;

public class ItemModel extends EntityDefaultItem {
    private int id;
    private int count;
    private String sprite;
    private int demage;
    private int heal;
    private String name;
    private ItemModel(){

    }
    public ItemModel(EntityDefaultItem defaultItem, int count) {

        this.count = count;
        id = defaultItem.getId();
        sprite = defaultItem.getSprite();
        demage = defaultItem.getDemage();
        heal = defaultItem.getHeal();
        name = defaultItem.getName();

    }
    public static ItemModel clone(ItemModel im){
        ItemModel cloneItem  = new ItemModel();
        //cloneItem.setCount(im.getCount());
        cloneItem.setDemage(im.demage);
        cloneItem.setId(im.id);
        cloneItem.setSprite(im.sprite);
        cloneItem.setHeal(im.heal);
        return cloneItem;
    }
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSprite() {
        return this.sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public int getDemage() {
        return this.demage;
    }

    public void setDemage(int demage) {
        this.demage = demage;
    }

    public int getHeal() {
        return this.heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof ItemModel)) {
            return false;
        } else {
            ItemModel itemModel = (ItemModel)o;
            return this.id == itemModel.id;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id});
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
