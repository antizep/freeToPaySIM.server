//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.ccoders.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ccoders.model.PlayerModel;
import ru.ccoders.utill.PlayerUtill;

@Api(
        value = "/player",
        description = "Player Controller"
)
@RestController
@RequestMapping({"/player"})
public class PlayerController {
    private final ApplicationContext ctx;
    private PlayerUtill playerUtill = new PlayerUtill();

    @Autowired
    public PlayerController(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @ApiOperation("Load game profile")
    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"}
    )
    private PlayerModel load(@PathVariable int id, @RequestParam String tocken) {
        return this.playerUtill.loadPlayer();
    }

    @ApiOperation("Создать профиль")
    @RequestMapping(
            value = {"/reg"},
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"}
    )
    private PlayerModel registration(@RequestParam int mail) {
        return new PlayerModel();
    }
}
