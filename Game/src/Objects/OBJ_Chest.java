package Objects;

import java.io.IOException;

import javax.imageio.ImageIO;
import Main.GamePanel;
public class OBJ_Chest  extends Object
{
    GamePanel gp;
    public OBJ_Chest (GamePanel gp){
        this.gp = gp;
        name = "Chest";
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/Objects/"));
            img = uTool.scaleImage(img, gp.tileSize, gp.tileSize);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
