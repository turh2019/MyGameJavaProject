package Objects;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Door extends Entity {
    
    public OBJ_Door(GamePanel gp){
        super(gp);
        name = "Door";
        img = setUp("/ObjectsImg/Key.png");

        collision = true;
        soidArea.x =0;
        soidArea.y =15;
        soidArea.width = 40;
        soidArea.height = 32;
        SolidAreaDefaultX = soidArea.x;
        SolidAreaDefaultY = soidArea.y;
    }
}
