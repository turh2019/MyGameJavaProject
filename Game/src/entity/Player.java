package entity;

import Main.GamePanel;
import Main.KeyHandler;

import Main.GamePanel.Gamestate;

import java.awt.Color;
import java.awt.Graphics2D;

import entity.NPC_;

import java.awt.Rectangle;

import java.awt.image.BufferedImage;

public class Player extends Entity
{ 
  
    KeyHandler keyH;  
    

    public final int screenX;
    public final int screenY;

   
    public Player(GamePanel gp ,KeyHandler keyH){
        super(gp);
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
        speed = 4;

        //player Statuts
        MaxLife = 6;
        life = MaxLife;
        
    }

    public void getPlayerImg()
    {
        numOfSprit=3;
        walkUpImgs = new BufferedImage[numOfSprit];
        walkDownImgs = new BufferedImage[numOfSprit];
        walkLeftImgs = new BufferedImage[numOfSprit];
        walkRightImgs = new BufferedImage[numOfSprit];
        for(int i =0; i < numOfSprit; i++){
            walkUpImgs[i] = setUp("/player/Up/player_" + i + ".png");
            walkDownImgs[i] = setUp("/player/Down/player_" + i +".png");
            walkLeftImgs[i] =  setUp("/player/Left/player_" + i + ".png");
            walkRightImgs[i] = setUp("/player/Right/player_" + i + ".png");
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
            //check tile Collision
            collisiOn =false;
            gp.checker.checkTile(this);

            //check Obj Collision
         //  int objIndex = gp.checker.checkObj(this, true);
           //PickUpItem(objIndex);


           //Check NPC Collision
            int npcIndex =gp.checker.CheckEntity(this, gp.npc);
            interactNPC(npcIndex);

            //Chack Event
            gp.eHandler.ChackEvent();

            if(!collisiOn){
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
         
            
                

        }else{
            spriteNum =0;
        }
    
    }

    public void PickUpItem(int index)
    {
        if(index != -1){
                
           
        }
    }


    public boolean isinteractNPC = false;

    public void interactNPC(int index)
    {
        if(index == -1) { return;}

        if(!keyH.spacePressed) return;

        gp.gamestate = Gamestate.dialogue;
        gp.npc[index].Speak();
     
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
