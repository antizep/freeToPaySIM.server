package ru.ccoders.utill;

import ru.ccoders.controller.ItemController;
import ru.ccoders.controller.PlayerController;
import ru.ccoders.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class FightUtil {

    public static final long ROUND_LENGTH = 300000;
    private static List<Fight> fights = new LinkedList<>();
    private static List<Battle> battles = new ArrayList<>();
    private static boolean aiMode = true;
    private float differencePercent = 0.1F;
    private final ItemController itemController;
    private final PlayerController playerController;
    private final AIUtil aiUtil;
    public FightUtil(ItemController itemController, PlayerController playerController) {
        this.itemController = itemController;
        aiUtil = new AIUtil(itemController);
        this.playerController = playerController;
    }

    public Fight searchFight(PlayerModel myModel) {
        Fight myFight = null;
        float myPower = power(myModel);
        for (Fight fight : fights) {

            if (fight.getPlayer1().equals(myModel))
             {
                return fight;
            }

            if (fight.getPlayer2() != null && fight.getPlayer2().equals(myModel)){
                return fight;
            }

        }
        if(aiMode){
            myFight = new Fight();
            myFight.setPlayer1(myModel);

            myFight.setPlayer2(aiUtil.createEnemy(myPower));
            fights.add(myFight);

            System.out.println(power(myFight.getPlayer2())+":"+myPower);
        }else {
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
        }

        if (myFight == null) {

            myFight = new Fight();
            myFight.setPlayer1(myModel);
            fights.add(myFight);
        }

        return myFight;

    }

    public boolean useItem(PlayerModel model, ItemModel item){
        Fight fight = searchFight(model);
        // TODO: 06.08.2019 Удалить использованный продемет из сущьности БД 
        if(fight == null){
            return false;
        }

        Round actualRound = getLastRoundFromFight(fight);

        if(fight.getPlayer2().equals(model)) {

            actualRound.setPlayer2(item);

        }

        if(fight.getPlayer1().equals(model)){
            actualRound.setPlayer1(item);
        }

        return true;
    }

    public ItemModel isFinal(PlayerModel model){

        Fight fight = searchFight(model);
        if(fight == null){
            return null;
        }

        Round round = getLastRoundFromFight(fight);
        if(aiMode){
            round.setPlayer2(aiUtil.getRandMove(fight.getPlayer2()));
        }

        boolean twoUse = (round.getPlayer2() != null && round.getPlayer1() != null);
        long et = round.getStartRound().getTime() + ROUND_LENGTH;
        Date thisTime = new Date();
        if(twoUse || et <= thisTime.getTime()){
            Battle battle = getBattleFromFight(fight);
            Round newRound = new Round();
            newRound.setStartRound(new Date());
            battle.getRound().add(newRound);
            ItemModel usedEnemy = null;
            ItemModel myUse = null;
            PlayerModel enemy = null;
            if(fight.getPlayer1().equals(model)) {
                enemy = fight.getPlayer2();
                usedEnemy =  round.getPlayer2();

                myUse = round.getPlayer1();
            }
            if(fight.getPlayer2().equals(model)){
                enemy = fight.getPlayer1();
                usedEnemy =  round.getPlayer1();
                myUse = round.getPlayer2();
            }

            if(!round.isComplete()){

                round.fin();
                int myHeals = model.getHealth() + myUse.getHeal() - usedEnemy.getDemage();
                int enemyHeals = enemy.getHealth() + usedEnemy.getHeal() - myUse.getDemage();

                model.setHealth(myHeals);
                enemy.setHealth(enemyHeals);
                playerController.updateHeal(model.getId(), String.valueOf(myHeals));
                if(!aiMode) {
                    playerController.updateHeal(enemy.getId(), String.valueOf(enemyHeals));
                }

                /*todo 1)
                       2)определить завершился ли бой
                       3)начислить вознаграждение победителю*/

            }

            if(usedEnemy == null) return null;
            usedEnemy =  ItemModel.clone(usedEnemy);
            return usedEnemy;
        }
        return null;

    }
    private Battle getBattleFromFight(Fight fight){

        Battle battle = null;
        for (Battle b:battles){
            if(b.getFight().equals(fight)){
                battle = b;
                break;
            }
        }

        if(battle == null){
            battle = new Battle();
            battle.setFight(fight);
            battles.add(battle);
        }
        return battle;
    }

    private Round getLastRoundFromFight(Fight fight){

        Battle battle = getBattleFromFight(fight);

        List<Round> rounds = battle.getRound();
        Round lastRound;
        if(rounds.size() > 0) {
             lastRound = rounds.get(rounds.size() - 1);
        }else {
            lastRound = new Round();
            lastRound.setStartRound(battle.getStartBattle());
            rounds.add(lastRound);
            return lastRound;
        }

        return lastRound;
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
