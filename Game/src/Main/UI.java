package Main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import Objects.OBJ_KEY;

public class UI {
    GamePanel gp;
    Font Arial_40;

    Graphics2D g2;

    public boolean messageOn = false;
    public String message="";
    int messageCounter =0;


    double playTime = 0;
    DecimalFormat Dformt = new DecimalFormat("#0.00");
    public UI(GamePanel gp)
    {
        this.gp =gp;
        Arial_40 =new Font("Arial",Font.PLAIN,40);
        
         
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(Arial_40);
        g2.setColor(Color.white);

        if(gp.Gamestate == Gamestate.GamePlay)
        {

        }
        else if(gp.Gamestate == Gamestate.Pause)
        { 
            drawPauseScreen();
        }
    }

    public void drawPauseScreen(){
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


    public void showMessage(String text)
    {
        message =text;
        messageOn =true;
    }


}
