package entity;

import Main.GamePanel;
import Main.KeyHandler;
import Main.UtilityTool;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Rectangle;

import java.awt.image.BufferedImage;

public class Player extends Entity
{ 
    GamePanel gp;
    KeyHandler keyH;  
    

    public final int screenX;
    public final int screenY;

   
    public Player(GamePanel gp ,KeyHandler keyH){
        this.gp =gp;
        this.keyH = keyH;
        screenX =gp.screenWidth/2 -(gp.tileSize/2);
        screenY = gp.screenHeight/2-(gp.tileSize/2);
        soidArea = new Rectangle();
        soidArea.x =8;
        soidArea.y =10;
        SolidAreaDefaultX = soidArea.x;
        SolidAreaDefaultY =  soidArea.y;
        soidArea.width =32;
        soidArea.height=32;
        
        setDefaultValues();
        getPlayerImg();
        direction ="down";
    }

    public void setDefaultValues(){
        worldX = gp.tileSize *23;
        worldY = gp.tileSize *21;
        speed =4;
        
    }

    public void getPlayerImg()
    {
        numOfSprit=3;

        walkUpImgs = new BufferedImage[numOfSprit];
        setUp("walkUpImgs",0,"Up/player_0");
        setUp("walkUpImgs",1,"Up/player_1");
        setUp("walkUpImgs",2,"Up/player_2");
        walkDownImgs = new BufferedImage[numOfSprit];
        setUp("walkDownImgs",0,"Down/player_0");
        setUp("walkDownImgs",1,"Down/player_1");
        setUp("walkDownImgs",2,"Down/player_2");
        walkLeftImgs = new BufferedImage[numOfSprit];
        setUp("walkLeftImgs",0,"Left/player_0");
        setUp("walkLeftImgs",1,"Left/player_1");
        setUp("walkLeftImgs",2,"Left/player_2");
        walkRightImgs = new BufferedImage[numOfSprit];
        setUp("walkRightImgs",0,"Right/player_0");
        setUp("walkRightImgs",1,"Right/player_1");
        setUp("walkRightImgs",2,"Right/player_2");
    }

    public void setUp (String arr,int index, String ImgPath)
    {
        UtilityTool uTool = new UtilityTool();
        try {
            if(arr =="walkUpImgs")
            {
              
                walkUpImgs[index]  = ImageIO.read(getClass().getResourceAsStream("/player/" + ImgPath +".png"));
                walkUpImgs[index] = uTool.scaleImage(walkUpImgs[index], gp.tileSize, gp.tileSize);
                return;
            }
            if(arr =="walkDownImgs")
            {
                walkDownImgs[index]  = ImageIO.read(getClass().getResourceAsStream("/player/" + ImgPath +".png"));
                walkDownImgs[index] = uTool.scaleImage(walkDownImgs[index], gp.tileSize, gp.tileSize);
                return;
            }
            if(arr =="walkLeftImgs")
            {
                walkLeftImgs[index]  = ImageIO.read(getClass().getResourceAsStream("/player/" + ImgPath +".png"));
                walkLeftImgs[index] = uTool.scaleImage(walkLeftImgs[index], gp.tileSize, gp.tileSize);
                return;
            }
            if(arr =="walkRightImgs")
            {
                walkRightImgs[index]  = ImageIO.read(getClass().getResourceAsStream("/player/" + ImgPath +".png"));
                walkRightImgs[index] = uTool.scaleImage(walkRightImgs[index], gp.tileSize, gp.tileSize);
                return;
            }
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update()
    {
        if(keyH.upPressed ||keyH.downPressed ||keyH.leftPressed||keyH.rightPressed)
        {
            if(keyH.upPressed ==true){
                direction ="up";
                     
            }
            else if(keyH.downPressed ==true){
                direction ="down";
                
            }
            else if(keyH.leftPressed ==true){
                direction ="left";
               
            }
            else if(keyH.rightPressed ==true){
                direction ="right";
                
            }
            //check tileCollision
            collisiOn =false;
            gp.checker.checkTile(this);

            //check ObjCollision
           int objIndex = gp.checker.checkObj(this, true);
           PickUpItem(objIndex);

            if(!collisiOn){
                switch(direction){
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed;break;
                    case "left":worldX -= speed;break;
                    case "right":worldX += speed;break;   
                }
               
                
            }

            spriteCounter++;
            if(spriteCounter >7){
                spriteNum++;
                if(spriteNum >= numOfSprit){
                    spriteNum =0;
                }
                spriteCounter =0;
            }

        }else{
            spriteNum =0;
        }
    
    }
    public void PickUpItem(int index)
    {
        if(index !=999){
                
           
        }
    }

    public void drow(Graphics2D g2){
        BufferedImage image = null;
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

        g2.drawImage(image,screenX ,screenY,null);
        g2.setColor(Color.red);
        g2.drawRect(screenX+ soidArea.x ,screenY+ soidArea.y,soidArea.width,soidArea.height);
    }
}
