package ru.ccoders.utill;

import org.junit.Test;
import ru.ccoders.model.PlayerModel;

public class AIUtilTest {

    AIUtil aiUtil = new AIUtil(controller);
    @Test
    public void createEnemy() {
        PlayerModel pm = aiUtil.createEnemy(100);
        System.out.println("health:"+ pm.getHealth());
    }
}