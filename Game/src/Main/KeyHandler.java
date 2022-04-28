package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Main.GamePanel.Gamestate;



public class KeyHandler implements KeyListener
{
    public boolean upPressed , downPressed , leftPressed , rightPressed , spacePressed , EnterPressed;

    GamePanel gp;
   public KeyHandler(GamePanel gp){
    this.gp =gp;
   }

    @Override
    public void keyTyped(KeyEvent e) {
        
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code =e.getKeyCode();
    





    if(gp.gamestate == Gamestate.Title)
    {
        if(code == KeyEvent.VK_W)
        {
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0)
            gp.ui.commandNum = 2;
        }

        if(code == KeyEvent.VK_S)
        {
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 2)
            gp.ui.commandNum = 0;
        }

        if(code == KeyEvent.VK_SPACE||code == KeyEvent.VK_ENTER)
        {
            if(gp.ui.commandNum == 0){
                gp.gamestate = Gamestate.GamePlay;
               // gp.playMusic(0);
            }

            if(gp.ui.commandNum == 1){
                //add later
            }

            if(gp.ui.commandNum == 2){
                System.exit(0);
            }
        }

    }    
    else if(gp.gamestate == Gamestate.GamePlay) //player state
    { 
        if(code == KeyEvent.VK_W)
        {
            upPressed =true;
        }

        if(code == KeyEvent.VK_S)
        {
            downPressed =true;
        }

        if(code == KeyEvent.VK_A)
        {
            leftPressed =true;
        }

        if(code == KeyEvent.VK_D)
        {
            rightPressed = true;
        }

        if(code == KeyEvent.VK_SPACE)
        {
            spacePressed = true;
        }

        if(code == KeyEvent.VK_ENTER)
        {
            EnterPressed =true;
        }
        if(code == KeyEvent.VK_C)
        {
            gp.gamestate = Gamestate.CharracterStatse;
        }

      }
               

      
        


        //Dialog System
        if(code == KeyEvent.VK_SPACE){
            gp.player.isinteractNPC = true;
            gp.gamestate = Gamestate.GamePlay;
        }




    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code =e.getKeyCode();

        //player state
            if(code == KeyEvent.VK_W)
            {
                upPressed =false;
            }

            if(code == KeyEvent.VK_S)
            {
                downPressed =false;
            }

            if(code == KeyEvent.VK_A)
            {
                leftPressed =false;
            }

            if(code == KeyEvent.VK_D)
            {
                rightPressed = false;
            }

            if(code == KeyEvent.VK_ENTER)
            {
                EnterPressed = false;
            }
        //Dialog System
        if(code == KeyEvent.VK_SPACE){
            
            spacePressed =false;
            gp.player.isinteractNPC = false;
        }
        // Game State
        switch (gp.gamestate) 
        {
            case GamePlay:
                if(code == KeyEvent.VK_P) 
                    gp.gamestate = Gamestate.Pause;
                break;
            case Pause:
                if(code == KeyEvent.VK_P) 
                    gp.gamestate = Gamestate.GamePlay;
                break;
            case dialogue:
                if(code == KeyEvent.VK_P) 
                    gp.gamestate = Gamestate.GamePlay;
                break;
            case CharracterStatse:
                if(code == KeyEvent.VK_C) 
                    gp.gamestate = Gamestate.GamePlay;
                break;
            case Title:
                break;
            default:
                break;
        }

    }
    
}
