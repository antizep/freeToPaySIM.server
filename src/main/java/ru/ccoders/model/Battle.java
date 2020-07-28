package ru.ccoders.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class Battle {

    private Fight fight;
    private Date startBattle = new Date();

    private List<Round> round = new ArrayList<>();
    private boolean ended = false;

    public Date getStartBattle() {
        return startBattle;
    }

}
