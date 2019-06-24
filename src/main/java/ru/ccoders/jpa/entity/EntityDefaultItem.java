package ru.ccoders.jpa.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "default_item", schema = "public", catalog = "postgres")
public class EntityDefaultItem {
    private String sprite;
    private int demage;
    private int heal;
    private String name;
    private int id;
    private Collection<EntityItems> itemsById;

    @Basic
    @Column(name = "sprite")
    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    @Basic
    @Column(name = "demage")
    public int getDemage() {
        return demage;
    }

    public void setDemage(int demage) {
        this.demage = demage;
    }

    @Basic
    @Column(name = "heal")
    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityDefaultItem that = (EntityDefaultItem) o;

        if (demage != that.demage) return false;
        if (heal != that.heal) return false;
        if (id != that.id) return false;
        if (sprite != null ? !sprite.equals(that.sprite) : that.sprite != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sprite != null ? sprite.hashCode() : 0;
        result = 31 * result + demage;
        result = 31 * result + heal;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }

    @OneToMany(mappedBy = "defaultItemByDefaultItem")
    public Collection<EntityItems> getItemsById() {
        return itemsById;
    }

    public void setItemsById(Collection<EntityItems> itemsById) {
        this.itemsById = itemsById;
    }
}
