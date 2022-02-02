package Objects;

import java.io.IOException;

import javax.imageio.ImageIO;
import Main.GamePanel;
public class OBJ_Boots extends Object{
    GamePanel gp;
    public OBJ_Boots(GamePanel gp)
    {
        this.gp = gp;
        name = "Boots";
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/Objects/Key.png"));
            img = uTool.scaleImage(img, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

