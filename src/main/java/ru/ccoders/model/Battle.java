package ru.ccoders.model;

import java.util.ArrayList;
import java.util.List;

public class Battle {

    private Fight fight;
    private List<Round> round = new ArrayList<>();

    public Fight getFight() {
        return fight;
    }

    public void setFight(Fight fight) {
        this.fight = fight;
    }

    public List<Round> getRound() {
        return round;
    }

}
