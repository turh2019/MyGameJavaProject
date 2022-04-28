package Objects;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Sword_Normal  extends  Entity{

    public OBJ_Sword_Normal(GamePanel gp){
        super(gp);
        name = "Normal Sword";
        img = setUp("/Items/Sword/Sword.png", 1);
        attackValue =1;
    }
}
