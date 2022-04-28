package entity;

import java.awt.image.BufferedImage;

import java.util.Random;



import Main.GamePanel;
import java.awt.Rectangle;


public class  NPC_oldMan extends NPC_{
    
    public NPC_oldMan(GamePanel gp){
        super(gp);

        direction ="down";
        speed =1;
        speedSprit =7;
     
        getNPCImg();
        SetDialogues();
    }


    public void getNPCImg()
    {
        numOfSprit=3;
        walkUpImgs = new BufferedImage[numOfSprit];
        walkDownImgs = new BufferedImage[numOfSprit];
        walkLeftImgs = new BufferedImage[numOfSprit];
        walkRightImgs = new BufferedImage[numOfSprit];
        for(int i =0; i < numOfSprit; i++){
            walkUpImgs[i] = setUp("/player/Up/player_" + i + ".png",1);
            walkDownImgs[i] = setUp("/player/Down/player_" + i +".png",1);
            walkLeftImgs[i] =  setUp("/player/Left/player_" + i + ".png",1);
            walkRightImgs[i] = setUp("/player/Right/player_" + i + ".png",1);
        }
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