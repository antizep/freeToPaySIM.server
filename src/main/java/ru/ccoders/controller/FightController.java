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
            value = {"/save"},
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"}
    )
    void saveVersion() {
    }
}
