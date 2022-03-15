package Objects;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import Main.GamePanel;
import entity.Entity;

public class OBJ_Boots extends Entity{

    public OBJ_Boots(GamePanel gp)
    {
        super(gp);
        name = "Boots";
        img = setUp("/Objects/Key.png");
    }
}

