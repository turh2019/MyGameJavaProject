package Objects;
import java.awt.image.BufferedImage;

import Main.GamePanel;
import Main.UtilityTool;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Object {
    
    public BufferedImage img;
    public String name;
    public boolean collision= false;
    public int worldX,worldY;
    public Rectangle soidArea = new Rectangle(0,0,48,48);
    public int SolidAreaDefaultX = 0 ,SolidAreaDefaultY = 0;
    UtilityTool uTool = new UtilityTool();  

    public void drow(Graphics2D g2 ,GamePanel gp)
    {   
        double screenX = worldX - gp.player.worldX + gp.player.screenX;
        double screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX 
        && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
        && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY 
        && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
        {
        
            g2.drawImage(img, (int)screenX, (int)screenY , gp.tileSize, gp.tileSize, null);
        }
    }
}
