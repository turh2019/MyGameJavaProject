package Main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;


import Main.GamePanel.Gamestate;
import Objects.OBJ_Heart;

import entity.Entity;

public class UI {
    GamePanel gp;
    Font Arial_40;

    Graphics2D g2;

    public boolean messageOn = false;
    public String message="";
    int messageCounter =0;


    double playTime = 0;
    DecimalFormat Dformt = new DecimalFormat("#0.00");

    public String CurrentDialogue = " ";

    public int commandNum = 0;


    //PlayerShow  
    BufferedImage Heart_full,Heart_half,Heart_balnck;
    
    public UI(GamePanel gp)
    {
        this.gp =gp;
        Arial_40 =new Font("Arial",Font.PLAIN,40);
        
         //create HUD OBj
         Entity heart = new OBJ_Heart(gp);
         if(heart !=null){
          System.out.println("s");
            Heart_full = heart.img; 
            Heart_half = heart.img2; 
            Heart_balnck = heart.img3; 
         }

    }
    
    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(Arial_40);
        g2.setColor(Color.white);


        //Title State
        if(gp.gamestate == Gamestate.Title)
        {
            DrowTitleScreen();
        }


        //Play State
        if(gp.gamestate == Gamestate.GamePlay)
        {
            DrowPlayerLife();
        }

        //pause state
        if(gp.gamestate == Gamestate.Pause)
        { 
            drawPauseScreen();
            DrowPlayerLife();
        }


        //pause dialogue
        if(gp.gamestate == Gamestate.dialogue)
        { 
            DrowDialogue();
            DrowPlayerLife();
        }

        //pause dialogue
        if(gp.gamestate == Gamestate.CharracterStatse)
        { 
            DrowCharracterStatse();
            DrowPlayerLife();
        }

    }

    public void  DrowCharracterStatse()
    {
        //create Frame
        final int  frameX = gp.tileSize * 2;
        final int  frameY = gp.tileSize;
        final int  farmWidth = gp.tileSize * 5;
        final int  farmHeight = gp.tileSize * 10;
        DrowWindow(frameX,frameY,farmWidth,farmHeight);

        //Text
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32f));

        int textX = frameX + 20;
        int textY = frameY  + gp.tileSize;
        final int lineHeight = 35;

        g2.drawString("level", textX, textY);
        textY  += lineHeight;
        g2.drawString("Life", textX, textY);
        textY  += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY  += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY  += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY  += lineHeight;
        g2.drawString("Defens", textX, textY);
        textY  += lineHeight;
        g2.drawString("Exp", textX, textY);
        textY  += lineHeight;
        g2.drawString("Next Level", textX, textY);
        textY  += lineHeight;
        g2.drawString("Coins", textX, textY);
        textY  += lineHeight;
        g2.drawString("Weapon", textX, textY);
        textY  += lineHeight;
        g2.drawString("Shield", textX, textY);

        //values
        int tailX =(frameX + farmWidth) - 30;
        textY = frameY  + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = GetXForAlignToRightText(value,tailX);
        g2.drawString(value, textX, textY);
        textY  += lineHeight;

        value = String.valueOf(gp.player.life +" / " + gp.player.MaxLife);
        textX = GetXForAlignToRightText(value,tailX);
        g2.drawString(value, textX, textY);
        textY  += lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = GetXForAlignToRightText(value,tailX);
        g2.drawString(value, textX, textY);
        textY  += lineHeight;
        
        value = String.valueOf(gp.player.dexterity);
        textX = GetXForAlignToRightText(value,tailX);
        g2.drawString(value, textX, textY);
        textY  += lineHeight;

        value = String.valueOf(gp.player.attack_);
        textX = GetXForAlignToRightText(value,tailX);
        g2.drawString(value, textX, textY);
        textY  += lineHeight;

        value = String.valueOf(gp.player.defens);
        textX = GetXForAlignToRightText(value,tailX);
        g2.drawString(value, textX, textY);
        textY  += lineHeight;
        
        value = String.valueOf(gp.player.exp);
        textX = GetXForAlignToRightText(value,tailX);
        g2.drawString(value, textX, textY);
        textY  += lineHeight;
        
        value = String.valueOf(gp.player.nextLevelExp);
        textX = GetXForAlignToRightText(value,tailX);
        g2.drawString(value, textX, textY);
        textY  += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = GetXForAlignToRightText(value,tailX);
        g2.drawString(value, textX, textY);
        textY  += lineHeight ;

        g2.drawImage(gp.player.currentWeapon.img, tailX - gp.tileSize, textY - 35,null);
        textY  += lineHeight ;
        g2.drawImage(gp.player.currentShield.img, tailX - gp.tileSize, textY - 35,null);
    }   
    

    public void drawPauseScreen()
    {
        String text ="Pause";
        int x =getscreenX(text) ;
        
       
        int y = gp.screenHeight/2;
        g2.drawString(text, x, y);
    }

    public int getscreenX(String text){
        int x;
        int length =(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x =gp.screenWidth/2 -length/2;
        return x;
    }


    void DrowDialogue()
    {
        //window
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        DrowWindow(x,y,width,height);


        Color c = new Color(250,250,250); 
        g2.setColor(c);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28f));

        x += gp.tileSize;
        y += gp.tileSize;
        for  (String line :CurrentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += gp.tileSize;
        }
        

    }

    void DrowTitleScreen()
    {
        g2.setColor(new Color(70,120,80));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        //title Name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,85));
        String text = "Adventure Game";
        int x = getscreenX(text);
        int y =  gp.tileSize * 3;

        //shadow
        g2.setColor(Color.gray);
        g2.drawString(text, x+5, y+5);
        //main color
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //Show Player
        x = gp.screenWidth / 2 -(gp.tileSize * 2) / 2;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.walkDownImgs[0], x,y , gp.tileSize *2 , gp.tileSize*2, null);


        //Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40f));

        text = "New game";
        x =getscreenX(text);
        y += gp.tileSize * 3.5f;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString("> ", x - gp.tileSize , y);
        }

        text = "Load Game";
        x =getscreenX(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString("> ", x - gp.tileSize , y);
        }


        text = "Quit";
        x =getscreenX(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2){
            g2.drawString("> ", x - gp.tileSize , y);
        }
    }

    public void DrowWindow(int x, int y, int width,int height )
    {
        Color c = new Color(250,250,250); 
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height,35,35);       
        
        c =  new Color(0,0,0);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.fillRoundRect(x + 5, y + 5, width - 10, height -10, 25, 25); 

    }



    public void showMessage(String text)
    {
        message =text;
        messageOn =true;
    }



    public void DrowPlayerLife()
    {
        

        if(Heart_balnck == null){
            System.out.println("aa");
        }

        int x  = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;
        while(i < gp.player.MaxLife / 2){
            g2.drawImage(Heart_balnck, x, y, null);
            i++;
            x += gp.tileSize;
        }

        //reset
        x  = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;
        //drow CurrentLife
        while(i < gp.player.life)
        {
            g2.drawImage(Heart_half, x, y, null);
            i++;

            if(i < gp.player.life)
            {
                g2.drawImage(Heart_full, x, y, null);
                
            }

            i++;
            x += gp.tileSize;
        }
    }

    public int GetXForAlignToRightText(String Text,int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(Text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
}
