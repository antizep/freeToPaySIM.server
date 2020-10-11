//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.ccoders.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.ccoders.model.Fight;
import ru.ccoders.model.ItemModel;
import ru.ccoders.model.PlayerModel;
import ru.ccoders.model.ResultRound;
import ru.ccoders.utill.FightUtil;

@Api(
        value = "/fight"
)
@RestController
@RequestMapping({"/fight"})
@Slf4j
public class FightController {
    private final ApplicationContext ctx;
    private PlayerController playerController;
    private ItemController itemController;
    private FightUtil fightUtil;
    private PlayerModel playerModel;
    @Autowired
    public FightController(ApplicationContext ctx) {
        this.ctx = ctx;
        itemController = new ItemController(ctx);
        playerController = new PlayerController(ctx);
        fightUtil =  new FightUtil(itemController, playerController);
    }

    @ApiOperation("Найти битву")
    @RequestMapping(
            value = {"/search"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    public boolean searchEnemy(@RequestParam(name = "id") String id) {
    	log.info("searchEnemy(@RequestParam(name = \"id\") String "+id+")");
    	try {
    	playerModel = playerController.load(Integer.parseInt(id));
    	}catch (Exception e) {
    		log.warn("не найден пользователь:"+id+"",e);
		}
        Fight myFight = fightUtil.searchFight(playerModel);
        return (myFight.getPlayer1() != null && myFight.getPlayer2() != null);

    }

    @ApiOperation("Использовать предмет")
    @RequestMapping(value = "/{userId}/{id}",
            method = {RequestMethod.POST},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public boolean useItem(@PathVariable(name = "userId") int userId, @PathVariable(name = "id") int itemId) throws Exception {
    	log.info("useItem(@PathVariable(name = \"userId\") int "+userId+", @PathVariable(name = \"id\") int "+itemId+")");
    	playerModel = playerController.load(userId);

        return fightUtil.useItem(playerModel, playerModel.getItemByItemId(itemId));

    }
    @ApiOperation("Окончен ли раунд")
    @RequestMapping(value = "/isEnd/{userId}",
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    public ResultRound endRound(@PathVariable(name = "userId")int userId){
    	log.info("endRound(@PathVariable(name = \"userId\")int "+userId+")");
    	playerModel = playerController.load(userId);

        return fightUtil.isFinal(playerModel);
    }
}
