package entity;

import Main.GamePanel;
import Main.KeyHandler;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;


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
        setDefaultValues();
       // getPlayerImg();
        direction ="down";
    }

    public void setDefaultValues(){
        worldX = gp.tileSize *23;
        worldY = gp.tileSize *21;
        speed =4;
    }

    public void getPlayerImg()
    {
        numOfSprit=2;
        try{
            walkUpImgs = new BufferedImage[numOfSprit];
            for(int i =0; i > walkUpImgs.length; i++){
                walkUpImgs[i] =ImageIO.read(getClass().getResourceAsStream("/player/boy_up" +i +".png"));
            }

            walkDownImgs = new BufferedImage[numOfSprit];
            for(int i =0; i > walkDownImgs.length; i++){
                walkDownImgs[i] =ImageIO.read(getClass().getResourceAsStream("/player/boy_down" +i+".png"));
            }

            walkLeftImgs = new BufferedImage[numOfSprit];
            for(int i =0; i > walkLeftImgs.length; i++){
                walkLeftImgs[i] =ImageIO.read(getClass().getResourceAsStream("/player/boy_left" + i + ".png"));
            }

            walkRightImgs = new BufferedImage[numOfSprit];
            for(int i =0; i > walkRightImgs.length; i++){
                walkRightImgs[i] =ImageIO.read(getClass().getResourceAsStream("/player/boy_Right" + i + ".png"));
            }

        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void update()
    {
        if(keyH.upPressed ||keyH.downPressed ||keyH.leftPressed||keyH.rightPressed)
        {
            if(keyH.upPressed ==true){
                direction ="up";
               worldY -= speed;       
            }
            else if(keyH.downPressed ==true){
                direction ="down";
                worldY += speed;
            }
            else if(keyH.leftPressed ==true){
                direction ="left";
                worldX -= speed;
            }
            else if(keyH.rightPressed ==true){
                direction ="right";
                worldX += speed;
            }

            spriteCounter++;

            if(spriteCounter >12){
                spriteNum++;
                if(spriteNum> numOfSprit){
                    spriteNum =0;
                }
            }

        }
    
    }


    public void drow(Graphics2D g2){
        BufferedImage image = null;
       /* switch (direction) {
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
           
        } */
        g2.setColor(Color.white);
        g2.fillRect( screenX,screenY,gp.tileSize,gp.tileSize);
       // g2.drawImage(image,screenX ,screenY,gp.tileSize,gp.tileSize,null);
    }
}
