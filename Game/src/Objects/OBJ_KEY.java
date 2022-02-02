package Objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_KEY extends Object {
    GamePanel gp;
    public OBJ_KEY(GamePanel gp){
        this.gp = gp;
        name = "key";
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/Objects/Key.png"));
            img = uTool.scaleImage(img, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
