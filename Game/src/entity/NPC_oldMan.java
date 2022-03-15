package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.UtilityTool;

import java.awt.Rectangle;



public class  NPC_oldMan extends NPC_{
    
    public NPC_oldMan(GamePanel gp){
        super(gp);

        direction ="down";
        speed =1;
        getNPCImg();
        SetDialogues();
    }


    public void getNPCImg()
    {
        numOfSprit=3;
        walkUpImgs = new BufferedImage[numOfSprit];
        walkUpImgs[0] = setUp("/player/Up/player_0.png");
        walkUpImgs[1] = setUp("/player/Up/player_1.png");
        walkUpImgs[2] = setUp("/player/Up/player_2.png");
        walkDownImgs = new BufferedImage[numOfSprit];
        walkDownImgs[0] = setUp("/player/Down/player_0.png");
        walkDownImgs[1] = setUp("/player/Down/player_1.png");
        walkDownImgs[2] = setUp("/player/Down/player_2.png");
        walkLeftImgs = new BufferedImage[numOfSprit];
        walkLeftImgs[0] =  setUp("/player/Left/player_0.png");
        walkLeftImgs[1] = setUp("/player/Left/player_1.png");
        walkLeftImgs[2] = setUp("/player/Left/player_2.png");
        walkRightImgs = new BufferedImage[numOfSprit];
        walkRightImgs[0] = setUp("/player/Right/player_0.png");
        walkRightImgs[1] = setUp("/player/Right/player_1.png");
        walkRightImgs[2] = setUp("/player/Right/player_2.png");
    }

 
    public void setAction()
    {

        actionLockCounter++;
        if(actionLockCounter < 120)  return;



        Random random = new Random();
        int i = random.nextInt(100) + 1;
    
        if(i <= 25)
             direction ="up";
        if(i > 25 && i <= 50)
            direction ="down";
         if(i > 50 && i <= 75)
            direction ="left";
        if(i > 75 && i <= 100)
            direction ="right";

        actionLockCounter =0;

        
        


      
    }

    public void SetDialogues()
    {
            MaxDialog = 5;
            Dialogues = new String[5];
            Dialogues[0] ="Hello, lad.";
            Dialogues[1] ="Hello,\nhow are you?";
    }


    public void Speak()
    {
         super.Speak();
    }
}