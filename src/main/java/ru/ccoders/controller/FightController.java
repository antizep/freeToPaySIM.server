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
import ru.ccoders.utill.FightUtil;

@Api(
        value = "/fight",
        description = "Player Controller"
)
@RestController
@RequestMapping({"/fight"})
public class FightController {
    private final ApplicationContext ctx;

    @Autowired
    public FightController(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @ApiOperation("Для сохранения версий")
    @RequestMapping(
            value = {"/searchFight"},
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"}
    )
    public boolean searchEnemy(@RequestParam(name = "id") String id) {
        FightUtil util = new FightUtil();
        PlayerController controller = new PlayerController(ctx);

        Fight myFight = util.searchFight(controller.load(Integer.parseInt(id)));
        return (myFight.getPlayer1() != null && myFight.getPlayer2() != null);

    }
    @ApiOperation("Использовать предмет")
    @RequestMapping(value = "/{userId}/{id}")
    public boolean useItem(@PathVariable(name = "userId") int userId,@PathVariable(name = "id") int id){



        return false;
    }


}
