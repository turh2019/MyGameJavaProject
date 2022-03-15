package Objects;

import java.awt.image.BufferedImage;
import java.io.Console;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Heart  extends Entity{
    

    public OBJ_Heart(GamePanel gp){
        super(gp);
        name = "Heart";
        img = setUp("/Objects/Life/Heart_Full.png");//Heart_Full
        img2 = setUp("/Objects/Life/Heart_Half.png");//Heart_Half
        img3 = setUp("/Objects/Life/Heart_blank.png");//Heart_blank

       System.out.println("a");
    }
}
