package Objects;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Shield_Wood  extends Entity{

    public OBJ_Shield_Wood(GamePanel gp) {
        super(gp);
        name = "Wood Shield";
        img = setUp("/Items/Shield/Shield.png", 1);
        defeonseValue = 1;
    }
    
}
