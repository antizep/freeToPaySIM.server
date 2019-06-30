//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.ccoders.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.ccoders.jpa.dao.DefaultItemDao;
import ru.ccoders.jpa.dao.ItemDao;
import ru.ccoders.jpa.entity.EntityDefaultItem;
import ru.ccoders.jpa.entity.EntityItems;
import ru.ccoders.model.ItemModel;
import ru.ccoders.utill.ItemUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Api(
        value = "/item",
        description = "Item Controller"
)
@RestController
@RequestMapping({"/item"})
public class ItemController {
    private final ApplicationContext ctx;
    private final ItemUtil iu;
    private final ItemDao itemDao;
    @Autowired
    public ItemController(ApplicationContext ctx) {
        this.ctx = ctx;
        itemDao = (ItemDao) ctx.getBean("jpaItem");
        this.iu = new ItemUtil((DefaultItemDao) ctx.getBean("jpaDefaultItem"));
    }

    @ApiOperation("Получить случайный предмет")
    @RequestMapping(
            value = {"/random"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    public ItemModel random() {
        ItemModel result = iu.getRandomItem();
        System.out.println("return item id:"+result.getId());
        return result;
    }

    @ApiOperation("Получить сет случайных предметов на мощность")
    @RequestMapping(
            value = "/power",
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    public Set<ItemModel> randomSet(@RequestParam("power") int power){
        Set<ItemModel> randomSet = new HashSet<>();
        int totalPower = 0;
        while (totalPower <= power) {
            ItemModel randModel = random();
            iu.addItemPath(randModel,randomSet);
            totalPower += randModel.getDemage() + randModel.getHeal();
        }
        System.out.println(totalPower+"<="+power);
        return randomSet;
    }

    @ApiOperation("Получить предмет по имени")
    @RequestMapping(
            value = "{name}",
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    public EntityDefaultItem getItem(@PathVariable(name = "name")String name) throws Exception {
        return iu.getByName(name);
    }

    @ApiOperation("Добавить предметы пользователю")
    @RequestMapping(
            value = "/add",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    public List<EntityItems> addAll(List<EntityItems> items){
        return itemDao.savaAll(items);
    }
}

