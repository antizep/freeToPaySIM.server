package ru.ccoders.model;

import java.util.List;

public class Battle {

    private Fight fight;
    private List<Round> round;

    public Fight getFight() {
        return fight;
    }

    public void setFight(Fight fight) {
        this.fight = fight;
    }

    public List<Round> getRound() {
        return round;
    }

    public void setRound(List<Round> round) {
        this.round = round;
    }

}
