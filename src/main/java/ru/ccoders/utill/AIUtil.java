package ru.ccoders.utill;

import ru.ccoders.controller.ItemController;
import ru.ccoders.model.ItemModel;
import ru.ccoders.model.PlayerModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class AIUtil {

    private ItemController controller;

    private Random random = new Random();

    public AIUtil(ItemController controller) {
        this.controller = controller;
    }

    public PlayerModel createEnemy(float power){

        PlayerModel enemyAi = new PlayerModel();
        enemyAi.setId(-1);
        float percentHealth = (float) (50 + random.nextInt(30) )/ 100F;
        int health = (int) (power * percentHealth);
        enemyAi.setHealth(health);
        System.out.println((power - health));
        enemyAi.setItems(controller.randomSet((int) (power - health)));

        return enemyAi;
    }
    public ItemModel getRandMove(PlayerModel enemyAi){
        List<ItemModel> itemModelList = new ArrayList<>(enemyAi.getItems());
        return itemModelList.get(random.nextInt(itemModelList.size()-1));
    }
}
