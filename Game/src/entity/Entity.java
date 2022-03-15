package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.UtilityTool;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Entity
{   
    GamePanel gp;
    public int worldX,worldY;
    public int speed;

    public BufferedImage[] walkUpImgs ,walkDownImgs , walkLeftImgs ,walkRightImgs;
    public int numOfSprit;
    public String direction = "down";

    public int spriteCounter =0;
    public int spriteNum =0;

    public Rectangle soidArea = new Rectangle(0,0,48,48);
    public int SolidAreaDefaultX ,SolidAreaDefaultY;

    public boolean collisiOn =false;

    public int actionLockCounter = 0;

    //obj
    public BufferedImage img, img2, img3;
    public String name;
    public boolean collision= false;


    // Entity Status
    public int MaxLife;
    public int life;      



    public Entity(GamePanel gp)
    {
        this.gp = gp;
    }

    public void setAction() {}

    public void update() 
        {
            setAction();

            collisiOn =false;
            gp.checker.checkTile(this);
          //  gp.checker.checkObj(this,false);
            gp.checker.checkPlayer(this);
  
            if(!collisiOn){// if he did not hit somting move
                switch(direction){
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed;break;
                    case "left":worldX -= speed;break;
                    case "right":worldX += speed;break;   
                }
               
                spriteCounter++;
                if(spriteCounter >7){
                    spriteNum++;
                    if(spriteNum >= numOfSprit){
                        spriteNum = 0;
                    }
                    spriteCounter = 0;
                }
            }

            if(collisiOn){
                spriteNum=0;
            }
        }

    public void Drow(Graphics2D g2)
        {
            BufferedImage image = null;
            double screenX = worldX - gp.player.worldX + gp.player.screenX;
            double screenY = worldY - gp.player.worldY + gp.player.screenY;
    
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX 
            && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
            && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY 
            && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
            {
              
                switch (direction) {
                    case "up":
                        image =walkUpImgs[spriteNum];
                        break;
                    case "down":
                        image =walkDownImgs[spriteNum];
                        break;
                    case "left":
                        image =walkLeftImgs[spriteNum];
                        break;
                    case "right":
                        image =walkRightImgs[spriteNum];
                        break;
                    case "Chest":
        
                        break;
                   
                } 
                g2.drawImage(image, (int)screenX, (int)screenY , gp.tileSize, gp.tileSize, null);
            }
        }

    public BufferedImage setUp (String ImgPath)
    {
        UtilityTool uTool = new UtilityTool();
        BufferedImage Img = null;
        try { 
                Img = ImageIO.read(getClass().getResourceAsStream(ImgPath));
                Img = uTool.scaleImage(Img, gp.tileSize, gp.tileSize);
                

            } catch (IOException e) {
                e.printStackTrace();
               
            }
            return Img;
    }

}
