package ru.ccoders.jpa.entity;

import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.Collection;

@Entity
@Table(name = "account", schema = "public", catalog = "postgres")
public class EntityAccount {
    private int id;
    private Integer heal;
    private Integer money;
    private Collection<EntityItems> itemsById;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "heal")
    public Integer getHeal() {
        return heal;
    }

    public void setHeal(Integer heal) {
        this.heal = heal;
    }

    @Basic
    @Column(name = "money")
    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityAccount that = (EntityAccount) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }
    @NotFound(action = NotFoundAction.IGNORE)
    @OneToMany(mappedBy = "accountByAccount", cascade = CascadeType.ALL,orphanRemoval = true)
    public Collection<EntityItems> getItemsById() {
        return itemsById;
    }

    public void setItemsById(Collection<EntityItems> itemsById) {
        this.itemsById = itemsById;
    }
}
