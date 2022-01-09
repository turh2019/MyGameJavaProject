package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import entity.Player;
import tile.TileManger;

public class GamePanel extends JPanel implements Runnable{
    
    final int originalTileSize =16; //16x16 tile
    final int scale =3; 

   public final int tileSize = originalTileSize *scale; //48x48
   public final int maxScreenCol =16;
   public final int maxScreenRow =12;
   public final int screenWidth = tileSize * maxScreenCol; //768 pixels
   public final int screenHeight = tileSize * maxScreenRow; //576 pixels




    int FPS =60;
    
    TileManger tileM = new TileManger(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    Player player = new Player(this, keyH);
    // set Players default Position

  

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread()
    {
            gameThread = new Thread(this);
            gameThread.start();
    }

    @Override
    public void run() {

        double drowInterval=1000000000/FPS;
        double delta =0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer =0;
        int DrowCount=0;
        while(gameThread!=null)
        {

           currentTime = System.nanoTime();

           delta += (currentTime-lastTime)/drowInterval;
           timer += (currentTime-lastTime);
           lastTime =currentTime;  
           
           if(delta>=1)
           {
                update();
                repaint();
                delta--;
                DrowCount++;
           }

            if(timer >= 1000000000){
                System.out.println("FPS: " + DrowCount);
                DrowCount = 0;
                timer =0;
            }

          
           
           
        }
        
    }
 
    public void update()
    {
        player.update();
    }

    @Override
    protected void paintComponent(Graphics g) {
      
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);

        player.drow(g2);

        g2.dispose();
    }
 
}
