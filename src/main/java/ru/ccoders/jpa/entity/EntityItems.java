package ru.ccoders.jpa.entity;

import javax.persistence.*;

@Entity
@Table(name = "items", schema = "public", catalog = "postgres",
uniqueConstraints = {@UniqueConstraint(columnNames = {"account", "default_item"})})
public class EntityItems {
    private int id;
    private int count;
    private EntityAccount accountByAccount;
    private EntityDefaultItem defaultItemByDefaultItem;

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
    @Column(name = "count")
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityItems that = (EntityItems) o;

        if (id != that.id) return false;
        if (count != that.count) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + count;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "account", referencedColumnName = "id", nullable = false)
    public EntityAccount getAccountByAccount() {
        return accountByAccount;
    }

    public void setAccountByAccount(EntityAccount accountByAccount) {
        this.accountByAccount = accountByAccount;
    }

    @ManyToOne
    @JoinColumn(name = "default_item", referencedColumnName = "id")
    public EntityDefaultItem getDefaultItemByDefaultItem() {
        return defaultItemByDefaultItem;
    }

    public void setDefaultItemByDefaultItem(EntityDefaultItem defaultItemByDefaultItem) {
        this.defaultItemByDefaultItem = defaultItemByDefaultItem;
    }
    
    public void addCount(int c) {
		count+=c;
	}
}
