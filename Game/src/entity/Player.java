package entity;

import Main.GamePanel;
import Main.KeyHandler;

import Main.GamePanel.Gamestate;
import Objects.OBJ_Shield_Wood;
import Objects.OBJ_Sword_Normal;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.awt.image.BufferedImage;



import java.awt.AlphaComposite;
import java.awt.Color;
public class Player extends Entity
{ 
  
    KeyHandler keyH;  
    

    public final int screenX;
    public final int screenY;

    String LastDiraction ="";
   public boolean attackCancel;
    public Player(GamePanel gp ,KeyHandler keyH){
        super(gp);
        this.keyH = keyH;

        screenX =gp.screenWidth/2 -(gp.tileSize/2);
        screenY = gp.screenHeight/2-(gp.tileSize/2);

        soidArea = new Rectangle();
        soidArea.x =25;
        soidArea.y =25;
        
        SolidAreaDefaultX = soidArea.x;
        SolidAreaDefaultY =  soidArea.y;

        soidArea.width =40;
        soidArea.height=45;
        
       attackArea.width = 36;
       attackArea.height =45;
       attackArea.y =35;

        speedSprit =9;

        setDefaultValues();
        getPlayerImg();
        getPlayerAttack();
        direction ="left";
        LastDiraction = "left";
    }

    public void setDefaultValues(){

        worldX = gp.tileSize *23;
        worldY = gp.tileSize *21;
        speed = 4;

        //player Statuts
        level = 1;
        MaxLife = 6;
        life = MaxLife;
        strength = 1;
        dexterity =1; 
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        attack_ = getAttack();
        defens = getDefns();
        
        
    }

  
    private int getDefns() {
        return defens = dexterity * currentShield.defeonseValue;

    }

    private int getAttack() {
        return attack_ = strength * currentWeapon.attackValue;
    }

    public void getPlayerImg()
    {
        numOfSprit=5;
        walkUpImgs = new BufferedImage[numOfSprit];
        walkDownImgs = new BufferedImage[numOfSprit];
        walkLeftImgs = new BufferedImage[numOfSprit];
        walkRightImgs = new BufferedImage[numOfSprit];
        for(int i =0; i < numOfSprit; i++){
            walkUpImgs[i] = setUp("/player/Right/player_" + i + ".png",2);
            walkDownImgs[i] = setUp("/player/Right/player_" + i + ".png",2);
            walkLeftImgs[i] = setUp("/player/Left/player_" + i + ".png",2);
            walkRightImgs[i] = setUp("/player/Right/player_" + i + ".png",2);
        }
    }
    
    public void getPlayerAttack()
    {
        attacRightkImgs = new BufferedImage[4]; 
        attackLeftImgs = new BufferedImage[4]; 
        for(int i =0; i < 4; i++)
        {
            attacRightkImgs[i] = setUp("/player/Attack/Right/Attack_" + i + ".png",2);
            attackLeftImgs[i] = setUp("/player/Attack/Left/Attack_" + i + ".png",2);

        }    
    }


  
    public void update()
    {
        if(attack){
            Attacking();
            return;
        }


        if(keyH.upPressed ||keyH.downPressed ||keyH.leftPressed||keyH.rightPressed || keyH.spacePressed)
        {
            
            if(keyH.upPressed ==true){
               
                direction ="up";
                     
            }
            else if(keyH.downPressed ==true){
                
             
                direction ="down";
                
            }
            else if(keyH.leftPressed ==true){
                LastDiraction = direction;
                direction ="left";
               
            }
            else if(keyH.rightPressed ==true){
                LastDiraction = direction;
                direction ="right";
                
            }
            //check tile Collision
            collisiOn =false;
            gp.checker.checkTile(this);

            //check Obj Collision
           int objIndex = gp.checker.checkObj(this, true);
           PickUpItem(objIndex);


           //Check NPC Collision
            int npcIndex =gp.checker.CheckEntity(this, gp.npc);
            interactNPC(npcIndex);

            int monsterIndex = gp.checker.CheckEntity(this,gp.monsters);
            contactMonster(monsterIndex);
            //Chack Event
            gp.eHandler.ChackEvent();

            if(!collisiOn && !keyH.spacePressed){
                switch(direction){
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed;break;
                    case "left":worldX -= speed;break;
                    case "right":worldX += speed;break;   
                }
               
                spriteCounter++;
                if(spriteCounter > speedSprit){
                    spriteNum++;
                    if(spriteNum >= numOfSprit){
                        spriteNum = 0;
                    }
                    spriteCounter = 0;
                }
            }
            if(keyH.spacePressed ==true &&  attackCancel == false) // attack sound
            {
               attack = true;
               spriteCounter =0;
            }
            attackCancel = false;
            keyH.spacePressed = false;
            if(collisiOn){
                spriteNum=0;
            }
 

        }else{
            spriteNum =0;
        }
        if(invincible){
            invincibleCounter ++;
            if(invincibleCounter >= 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
 
    }

    public void Attacking()
    {

        spriteCounter++;
        if(spriteCounter > speedSprit){
            spriteNum++;
            if(spriteNum == 1){

                // save the current world X world y solidArea
                int currentWorldX =worldX;
                int currentWorldy =worldY;
                int solidAreaWidth = soidArea.width;
                int solidAreaHeight = soidArea.height;

                //adjust player`s worldx/y for attackArea
                switch (LastDiraction) {
                    case "left":
                    worldX -= attackArea.height;
                    break;
                    case "right":
                    worldX += attackArea.height;
                    break;
                }
                
                //attack = soidArea
                soidArea.width = attackArea.width;
                soidArea.height = attackArea.height;
                //check if he hit monster
                int monsterIndex =gp.checker.CheckEntity(this, gp.monsters);
                DamgeMonster(monsterIndex);



                worldX = currentWorldX;
                worldY = currentWorldy;
                soidArea.width = solidAreaWidth;
                soidArea.height = solidAreaHeight;
            }
            


            if(spriteNum > 3){
                spriteNum = 0;
                spriteCounter =0;
                attack = false;
            }
            spriteCounter = 0;
        }

       
    }

    private void contactMonster(int index) 
    {
        if(index != -1){
            if(!invincible)
            {
                // gp.PlaySound(i);

                int dmg  = gp.monsters[index].attack_ - defens;
                if(dmg < 0)
                    dmg =0;
    

                life -= dmg;
                invincible = true;
            }

           
        }
    }

    public void DamgeMonster(int index)
    {
        if(index == -1)return; 
        

        if(gp.monsters[index].invincible == false){
            // gp.PlaySound(i);

            int dmg  = attack_ - gp.monsters[index].defens;
            if(dmg < 0)
                dmg =0;

            gp.monsters[index].life -= dmg;
            gp.ui.AddMessage(dmg + " damage!");

            gp.monsters[index].invincible = true;
            gp.monsters[index].DamgeReaction();

            if(gp.monsters[index].life <= 0){

                gp.monsters[index].dying = true;
                gp.ui.AddMessage("killed the monster: " + gp.monsters[index].name);
                exp += gp.monsters[index].exp;
                gp.ui.AddMessage("Exp +" + gp.monsters[index].exp);
                checkLevelUp();
            }
        }

    }
    
    public void checkLevelUp()
    {
        if(exp >= nextLevelExp){
             level++;
             nextLevelExp = nextLevelExp *2;
             MaxLife += 2;
             strength++;
             dexterity++;
             attack_ = getAttack();
             defens = getDefns();
             
             gp.gamestate = Gamestate.dialogue;
             gp.ui.CurrentDialogue = "you are level " + level + " now\n"+
             "you feel stronger!";

             
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
        if(!keyH.spacePressed) return;
        if(index != -1) {
            attackCancel =true;
            gp.gamestate = Gamestate.dialogue;
            gp.npc[index].Speak();
        }
        
     
    }

  
  @Override
  public void Drow(Graphics2D g2) {
     
            BufferedImage image = null;
        switch (direction) {
            case "up":
            if(attack == false)
            {
                if(LastDiraction == "left")
                image = walkLeftImgs[spriteNum];
                    if(LastDiraction == "right")  
                image = walkRightImgs[spriteNum];
                break;
            } 
                if(LastDiraction == "left")
                    image = attackLeftImgs[spriteNum];
                if(LastDiraction == "right")  
                    image = attacRightkImgs[spriteNum];
                    break;
            case "down":       
            if(attack == false)
            {    
                if(LastDiraction == "left")
                    image = walkLeftImgs[spriteNum];
                if(LastDiraction == "right")  
                    image = walkRightImgs[spriteNum];
                    break;
            }
                if(LastDiraction == "left")
                    image = attackLeftImgs[spriteNum];
                if(LastDiraction == "right")  
                    image = attacRightkImgs[spriteNum];
            break;
            case "left":
                if(attack == false)
                {
                    image = walkLeftImgs[spriteNum];
                    break;
                }
                    image = attackLeftImgs[spriteNum];
                    break;
            case "right": 
            if(attack == false)
            {
                image = walkRightImgs[spriteNum];
                break;
            }
                image = attacRightkImgs[spriteNum];   
                break;
            case "Chest":
            break;
        } 

       
        if(invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3f));
         
        }

        g2.setColor(Color.red);
        g2.drawRect(screenX+ soidArea.x ,screenY+ soidArea.y,soidArea.width,soidArea.height);

        int currentWorldX =screenX;
        int currentWorldy = screenY;
        int solidAreaWidth = attackArea.width;
        int solidAreaHeight = attackArea.height;

        //adjust player`s worldx/y for attackArea
        switch (LastDiraction) {
            case "left":
            break;
            case "right":
            currentWorldX += attackArea.height;
            break;
        }

        g2.setColor(Color.red);
        g2.drawRect(currentWorldX + attackArea.x ,currentWorldy + attackArea.y,solidAreaWidth,solidAreaHeight);


        g2.drawImage(image,screenX ,screenY,null);
      
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));

  }
    // public void drow(Graphics2D g2){
  
    // }
}
