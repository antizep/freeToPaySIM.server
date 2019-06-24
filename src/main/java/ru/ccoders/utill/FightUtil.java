package ru.ccoders.utill;

import ru.ccoders.model.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FightUtil {

    private static List<Fight> fights = new LinkedList<>();
    private static List<Battle> battles = new ArrayList<>();

    private float differencePercent = 0.1F;

    public Fight searchFight(PlayerModel myModel) {
        Fight myFight = null;
        float myPower = power(myModel);
        for (Fight fight : fights) {

            if (fight.getPlayer1().equals(myModel)) {
                return fight;
            }

            if (fight.getPlayer2() != null && fight.getPlayer2().equals(myModel)){
                return fight;
            }

        }

        for (Fight fight : fights) {
            if (fight.getPlayer2() != null) {
                continue;
            }
            float enemyPower = power(fight.getPlayer1());
            float difference = Math.abs(myPower - enemyPower);
            float percent = difference / myPower;

            if (percent <= differencePercent) {

                fight.setPlayer2(myModel);
                myFight = fight;

                Battle battle = new Battle();
                battle.setFight(myFight);

                battles.add(battle);
                break;
            }

        }
        if (myFight == null) {

            myFight = new Fight();
            myFight.setPlayer1(myModel);
            fights.add(myFight);
        }

        return myFight;

    }

    public Battle useItem(PlayerModel model, ItemModel item){
        Fight fight = searchFight(model);
        Battle battle = null;

        for (Battle b:battles){
            if(b.getFight().equals(fight)){
                battle = b;
                break;
            }
        }

        List<Round> rounds = battle.getRound();
        Round lastRound = rounds.get(rounds.size()-1);

        Round actualRound;
        if(lastRound.getPlayer1()!=null && lastRound.getPlayer2() != null){
            actualRound = new Round();
        }else{
            actualRound = lastRound;
        }

        if(battle.getFight().getPlayer2().equals(model)){
            actualRound.setPlayer2(item);
        }

        if(battle.getFight().getPlayer1().equals(model)){
            actualRound.setPlayer1(item);
        }

        return battle;
    }

    private float power(PlayerModel playerModel) {
        int pow = playerModel.getHealth();
        for (ItemModel item : playerModel.getItems()) {
            pow += item.getDemage() * item.getCount();
            pow += item.getHeal() * item.getCount();
        }
        return pow;
    }

}
