package Monsters;

import java.awt.image.BufferedImage;
import java.util.Random;

import Main.GamePanel;
import entity.Entity;

public class Mon_Slime  extends Entity{

    public Mon_Slime(GamePanel gp) {
        super(gp);
        name = "Green Slime";
        speed =1;
        MaxLife = 4;
        life =MaxLife;

        soidArea.x = 3;
        soidArea.y = 18;
        soidArea.width = 42;
        soidArea.height = 30;
        SolidAreaDefaultX =  soidArea.x;
        SolidAreaDefaultY =  soidArea.y;
        getImage();
    }


    void  getImage()
    {
        numOfSprit=5;
        walkUpImgs = new BufferedImage[numOfSprit];
        walkDownImgs = new BufferedImage[numOfSprit];
        walkLeftImgs = new BufferedImage[numOfSprit];
        walkRightImgs = new BufferedImage[numOfSprit];
        for(int i =0; i < numOfSprit; i++){
            walkUpImgs[i] = setUp("/Monsters/Slime/Jump/png/Slime_Jump" + i + ".png");
            walkDownImgs[i] = setUp("/Monsters/Slime/Jump/png/Slime_Jump" + i +".png");
            walkLeftImgs[i] =  setUp("/Monsters/Slime/Jump/png/Slime_Jump" + i + ".png");
            walkRightImgs[i] = setUp("/Monsters/Slime/Jump/png/Slime_Jump" + i + ".png");
        }
    }
    

    @Override
    public void setAction() {
        
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

}
