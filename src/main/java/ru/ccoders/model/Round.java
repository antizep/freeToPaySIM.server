package ru.ccoders.model;

import java.util.Date;

public class Round {
    private ItemModel player1;
    private ItemModel player2;
    private Boolean isEnd;
    private Date startRound;

    public ItemModel getPlayer1() {
        return player1;
    }

    public void setPlayer1(ItemModel player1) {
        this.player1 = player1;
    }

    public ItemModel getPlayer2() {
        return player2;
    }

    public void setPlayer2(ItemModel player2) {
        this.player2 = player2;
    }
}
