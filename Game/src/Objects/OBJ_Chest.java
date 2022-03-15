package Objects;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import Main.GamePanel;
import entity.Entity;

public class OBJ_Chest  extends Entity
{
    public OBJ_Chest (GamePanel gp){
        super(gp);
        name = "Chest";
        img = setUp("/Objects/Key.png");
        
    }
    
}
