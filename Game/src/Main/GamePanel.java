package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import entity.Player;
import tile.TileManger;
import Objects.Object;

enum Gamestate{
    GamePlay,
    Pause,
}

public class GamePanel extends JPanel implements Runnable{
    
    
 
    //screen Settings
    final int originalTileSize =16; //16x16 tile
    final int scale =3; 

   public  int tileSize = originalTileSize *scale; //48x48
   public  int maxScreenCol =16;
   public  int maxScreenRow =12;
   public  int screenWidth = tileSize * maxScreenCol; //768 pixels
   public  int screenHeight = tileSize * maxScreenRow; //576 pixels

    //worldMap SETTINGS
    public final int maxWorldCol =50; 
    public final int maxWorldRow =50;
    public final int WorldWidth = tileSize* maxWorldCol;
    public final int WorldHeight = tileSize* maxScreenRow;
    //fps
    int FPS =60;
    
    TileManger tileM = new TileManger(this);//Tile Manger
    public collisonChecker checker = new collisonChecker(this);//collison Checker
    public AssetSetter assetSetter =new AssetSetter(this);//set objs
    public Sound sound = new Sound();//sound display
    public Sound music = new Sound();//sound music
    Thread gameThread;

    public UI ui = new UI(this);//ui

    KeyHandler keyH = new KeyHandler(this); //Key Handler
    public Player player = new Player(this, keyH);  //player
    public Object obj[] = new Object[10];//objs
    
    //GameState
    public Gamestate Gamestate ;
  

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame()
    {

        assetSetter.setObject();

        //playMusic(i); // when i have Music fot the game
        Gamestate = Gamestate.GamePlay;
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
              //  System.out.println("FPS: " + DrowCount);
                DrowCount = 0;
                timer =0;
            }

          
           
           
        }
        
    }
 
    public void update()
    {
        if(Gamestate == Gamestate.GamePlay){
            player.update();
        }  
        
        if(Gamestate == Gamestate.Pause){
           
        }  
    }

    @Override
    protected void paintComponent(Graphics g) {
      
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        //tile
        tileM.draw(g2);

        //object
        for(int i =0; i < obj.length ;i++)
        {
            if(obj[i]!=null){
                obj[i].drow(g2, this);
            }
        }

        //player
        player.drow(g2);

        //UI 
        ui.draw(g2); 

        g2.dispose();
    }
 

        public  void playMusic(int i){
            music.setFile(i);
            music.play();
            music.loop();
        }

        public void stopMusic(){
            music.stop();
        }

        public void PlaySound(int i)
        {
            sound.setFile(i);
            sound.play();
        }

}
