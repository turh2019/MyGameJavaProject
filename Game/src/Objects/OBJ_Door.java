package Objects;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import Main.GamePanel;
import entity.Entity;

public class OBJ_Door extends Entity {
    
    public OBJ_Door(GamePanel gp){
        super(gp);
        name = "Door";
        img = setUp("/Objects/Key.png");
    }
}
