package Objects;



import java.awt.image.BufferedImage;

import Main.GamePanel;
import entity.Entity;

public class OBJ_KEY extends Entity {

    public OBJ_KEY(GamePanel gp){
        super(gp);
        name = "key";
        walkDownImgs = new BufferedImage[1];
        walkDownImgs[0] = setUp("/ObjectsImg/Key.png");

    }
}
