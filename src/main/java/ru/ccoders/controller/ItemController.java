//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.ccoders.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.ccoders.jpa.dao.DefaultItemDao;
import ru.ccoders.model.ItemModel;
import ru.ccoders.utill.ItemUtil;

@Api(
        value = "/item",
        description = "Item Controller"
)
@RestController
@RequestMapping({"/item"})
public class ItemController {
    private final ApplicationContext ctx;

    @Autowired
    public ItemController(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @ApiOperation("Получить случайный предмет")
    @RequestMapping(
            value = {"/random"},
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"}
    )
    public ItemModel random() {
        ItemUtil itemUtil = new ItemUtil((DefaultItemDao) ctx.getBean("jpaDefaultItem"));
        return itemUtil.getRandomItem();
    }
}
