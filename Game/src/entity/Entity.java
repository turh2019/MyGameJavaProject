package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.UtilityTool;
import java.awt.Graphics2D;
import java.awt.Rectangle;





import java.awt.AlphaComposite;
import java.awt.Color;

public class Entity
{   
    public GamePanel gp;
    public int worldX,worldY;
    
    
    public BufferedImage[] walkUpImgs ,walkDownImgs , walkLeftImgs ,walkRightImgs;
    public BufferedImage[] attackLeftImgs ,attacRightkImgs ;
    public int numOfSprit;
    public String direction = "down";

    
    public int spriteNum =0;
    public int speedSprit ;
    public Rectangle  soidArea = new Rectangle(0,0,48,48);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int SolidAreaDefaultX ,SolidAreaDefaultY;

    public boolean collisiOn =false;
    public boolean hpBarOn;
   

    boolean attack = false;
    //obj
    public BufferedImage img, img2, img3;
    
    public boolean collision = false;


    public boolean invincible = false;
    
    //Counters
    public int invincibleCounter = 0;
    public int actionLockCounter = 0;
    public int spriteCounter = 0;
    public int dyingCounter = 0;
    public int hpBarCounter = 0;


    public boolean alive = true;
    public boolean dying = false;

    // Entity Status
    public String name;
    public int type; // 0 = player , 1 = npc , 2 = monster
    public int MaxLife;
    public int life;      
    public int speed;
    public int level;
    public int strength;
    public int dexterity;
    public int attack_;
    public int defens;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;

    ///item Attribute
    public int attackValue;
    public int defeonseValue;
    
    public Entity(GamePanel gp)
    {
        this.gp = gp;
    }

    public void setAction() {}

    public void DamgeReaction(){}

    public void update() 
        {
            setAction();

            collisiOn =false;
            gp.checker.checkTile(this);
            gp.checker.checkObj(this,false);
            gp.checker.CheckEntity(this,gp.npc);
            gp.checker.CheckEntity(this,gp.monsters);
            boolean contactPlayer = gp.checker.checkPlayer(this);
            
            if(type == 2 && contactPlayer)
            {
                 if(gp.player.invincible == false)
                 {
                      // gp.PlaySound(i);
                    int dmg  = attack_ - gp.player.defens;
                    if(dmg < 0)
                    dmg =0;
                    gp.player.life -= dmg;
                    gp.player.invincible = true;
                 }
            }

            if(!collisiOn){// if he did not hit somting move
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

            if(collisiOn){
                spriteNum=0;
            }

            if(invincible){
                invincibleCounter ++;
                if(invincibleCounter >= 60){
                    invincible = false;
                    invincibleCounter = 0;
                }
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
                    case "up":image = walkUpImgs[spriteNum]; break;
                    case "down": image = walkDownImgs[spriteNum]; break;
                    case "left": image = walkLeftImgs[spriteNum]; break;
                    case "right":image = walkRightImgs[spriteNum]; break;
                    case "Chest":
        
                        break;
                   
                } 
               
                if(invincible == true)
                {
                    hpBarOn = true;
                    hpBarCounter = 0;
                    ChcngeAlpha(g2,0.4f);
                    
               
                }

                // life bar
                if(type == 2 && hpBarOn)
                {
                    double oneScale = (double)gp.tileSize/MaxLife;
                    double hpBar =oneScale *life;
                    g2.setColor(new Color(35,35,35));
                    g2.fillRect((int)screenX -1, (int)screenY - 16, gp.tileSize + 2, 12);
                    g2.setColor(new Color(255,0,30));
                    g2.fillRect((int)screenX, (int)screenY - 15, (int)hpBar, 10);
                    hpBarCounter ++;
                    if(hpBarCounter > 600){
                        hpBarCounter = 0;
                        hpBarOn =false;
                    }
                }
                


                if(dying){
                    dyingAnimtion(g2);
                }



                //box colider
                g2.setColor(Color.red);
                g2.drawRect( (int)screenX + soidArea.x, (int)screenY  + soidArea.y, soidArea.width, soidArea.height);
//
                g2.drawImage(image, (int)screenX, (int)screenY , gp.tileSize, gp.tileSize, null);
              
                ChcngeAlpha(g2,1f);
            }
    }

    public void  dyingAnimtion(Graphics2D g2){
        dyingCounter++;
        int i = 5;
        if(dyingCounter > i ) ChcngeAlpha(g2, 0f);

        for(int j = 1; j <6; j++)
        {
            if(dyingCounter > i * j && dyingCounter <= i * (j+1)) ChcngeAlpha(g2, (j+1) % 2);
            
        }
        if(dyingCounter > i * 7){
            dying =false;
            alive = false;
        }
       
       

    }

    public void ChcngeAlpha(Graphics2D g2 , float value)
    {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,value));
    }
    
    public BufferedImage setUp (String ImgPath ,int Size)
    {
        UtilityTool uTool = new UtilityTool();
        BufferedImage Img = null;
        try { 
                Img = ImageIO.read(getClass().getResourceAsStream(ImgPath));
                Img = uTool.scaleImage(Img, gp.tileSize * Size, gp.tileSize * Size);
                

            } catch (IOException e) {
                e.printStackTrace();
               
            }
            return Img;
    }

}
