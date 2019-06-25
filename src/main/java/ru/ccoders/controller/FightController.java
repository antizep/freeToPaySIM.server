//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.ccoders.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import ru.ccoders.model.Fight;
import ru.ccoders.model.PlayerModel;
import ru.ccoders.utill.FightUtil;

@Api(
        value = "/fight"
)
@RestController
@RequestMapping({"/fight"})
public class FightController {
    private final ApplicationContext ctx;
    private FightUtil util = new FightUtil();
    private PlayerController playerController;
    private ItemController itemController;

    @Autowired
    public FightController(ApplicationContext ctx) {
        this.ctx = ctx;
        itemController = new ItemController(ctx);
        playerController = new PlayerController(ctx);
    }

    @ApiOperation("Для сохранения версий")
    @RequestMapping(
            value = {"/searchFight"},
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"}
    )
    public boolean searchEnemy(@RequestParam(name = "id") String id) {


        Fight myFight = util.searchFight(playerController.load(Integer.parseInt(id)));
        return (myFight.getPlayer1() != null && myFight.getPlayer2() != null);

    }

    @ApiOperation("Использовать предмет")
    @RequestMapping(value = "/{userId}/{id}",
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public boolean useItem(@PathVariable(name = "userId") int userId, @PathVariable(name = "id") int itemId) throws Exception {
        PlayerModel playerModel = playerController.load(userId);

        util.useItem(playerModel, playerModel.getItemByItemId(itemId));
        return false;

    }


}
