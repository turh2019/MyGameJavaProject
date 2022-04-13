package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.NPC_;
import entity.Player;
import tile.TileManger;




public class GamePanel extends JPanel implements Runnable{
    
    public enum Gamestate{
       Title,
        GamePlay,
        Pause,
        dialogue
    }
 
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

    //Tile Manger
    TileManger tileM = new TileManger(this);

    //collison Checker
    public collisonChecker checker = new collisonChecker(this);

    //set objs
    public AssetSetter assetSetter =new AssetSetter(this);
    public Sound sound = new Sound();//sound display
    public Sound music = new Sound();//sound music
    Thread gameThread;

    //UI Handler
    public UI ui = new UI(this);

    //Handler Event
    public EventHandler eHandler = new EventHandler(this); //Handler Event

    //Key Handler
    KeyHandler keyH = new KeyHandler(this); //Key Handler

    // entity and obj
    public Player player = new Player(this, keyH);  //player
    public Entity objs[] = new Entity[10];//objs
    public NPC_ npc[] = new NPC_[10]; // npcs
    public Entity monsters[] = new Entity[20]; // npcs
    ArrayList<Entity> entityList =  new ArrayList<>();
    //GameState
    public Gamestate gamestate ;
  

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame()
    {

        assetSetter.update();

        //playMusic(i); // when i have Music fot the game
        gamestate = Gamestate.Title;
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
        if(gamestate == Gamestate.GamePlay){

            //player
            player.update();

            //NPC
            for(int i =0; i < npc.length ;i++){
                if(npc[i]!=null){
                    npc[i].update();
                } 
            }

                        //NPC
            for(int i =0; i < monsters.length ;i++)
            {
                if(monsters[i]!=null){
                    monsters[i].update();
                } 
            }
        }  
        
        if(gamestate == Gamestate.Pause){
           
        }  
    }

    @Override
    protected void paintComponent(Graphics g) {
      
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;



        if(gamestate == Gamestate.Title)
        {
            ui.draw(g2); 
            return;
        }

        


        //tile
        tileM.draw(g2);

        //draw Entity
        DrawEntity(g2);

        //UI 
        ui.draw(g2); 

        g2.dispose();
    }
 
    public void DrawEntity(Graphics2D g2)
    {
        //add entity to the list

        //add player
        entityList.add(player);
        
        //add npcs
        for( int i =0; i < npc.length; i++){
            if(npc[i] != null){
                entityList.add(npc[i]);
            }
        }

        
        //add objects
        for( int i =0; i < objs.length; i++){
            if(objs[i] != null){
                entityList.add(objs[i]);
            }
        }

        
        //add monsters
        for( int i =0; i < monsters.length; i++){
            if(monsters[i] != null){
                entityList.add(monsters[i]);
            }
        }

        //sort List
        Collections.sort(entityList, new Comparator<Entity>() {

            @Override
            public int compare(Entity o1, Entity o2) {
                
                int result =Integer.compare(o1.worldX, o2.worldX);
                return result;
                
            }
            
        });

        //drow Entity
        for(int i =0; i < entityList.size(); i++){
            entityList.get(i).Drow(g2);
        }

        //empty entity list
        for(int i =0; i < entityList.size(); i++){
            entityList.remove(i);
        }
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
