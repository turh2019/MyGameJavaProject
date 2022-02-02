package tile;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.swing.text.Utilities;

import Main.GamePanel;
import Main.UtilityTool;

import java.awt.Graphics2D;


import java.awt.image.BufferedImage;
public class TileManger {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    public TileManger(GamePanel gp){

        this.gp =gp;

        tile = new Tile[100];
        mapTileNum = new  int [gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/map_01.txt");
    }

    public void getTileImage()
    {
        //Grasse
        for(int i =1; i < 16; i ++)
        {
            String Path ="/tiles/Grasse/Grasse"+String.format("%02d", i) +".png";
            System.out.println(Path);
            setUp(i-1+10,Path, false);
        }
       
          //Ground
          for(int i =30; i < 43; i ++)
          {
              String Path ="/tiles/Ground/Ground"+String.format("%02d", i-29) +".png";
              System.out.println(Path);
              setUp(i,Path, false);
          }
          //water
          for(int i =50; i < 63; i ++)
          {
              String Path ="/tiles/water/water"+String.format("%02d", i-49) +".png";
              System.out.println(Path);
              setUp(i,Path, true);
          }

    }

    public void setUp (int index, String ImgPath,boolean Collision)
    {
        
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].img = ImageIO.read(getClass().getResourceAsStream(ImgPath));
            tile[index].img = uTool.scaleImage(tile[index].img, gp.tileSize, gp.tileSize);
            tile[index].collision = Collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePathe) 
    {
        try {
            InputStream is = getClass().getResourceAsStream(filePathe);
            BufferedReader br = new  BufferedReader(new InputStreamReader(is));

            int col =0;
            int row =0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line;
                
                    line = br.readLine();
            

                while(col<gp.maxWorldCol){
                    String numbers[] =line.split(" ");
                    int num =Integer.parseInt(numbers[col]);
                

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col =0;
                    row++;
                }

            }
            br.close();
    } catch (IOException e) {
       
        e.printStackTrace();
    }
    }




    public void draw(Graphics2D g2)
    {
        int Worldcol = 0;
        int Worldrow = 0;
      
        while (Worldcol <gp.maxWorldCol && Worldrow< gp.maxWorldRow) 
        {
            int tileNum =mapTileNum[Worldcol][Worldrow];

            int worldX= Worldcol * gp.tileSize;
            int worldY= Worldrow * gp.tileSize;
            double screenX = worldX - gp.player.worldX + gp.player.screenX;
            double screenY = worldY - gp.player.worldY + gp.player.screenY;
            


            //Stop Moving the Camera at the edge
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX 
            && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
            && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY 
            && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
            {
               
                if(tileNum == 3){
                    g2.drawImage(tile[0].img, (int)screenX, (int)screenY , null);
                }
                g2.drawImage(tile[tileNum].img, (int)screenX, (int)screenY ,  null);
            }
            Worldcol++;  
            if(Worldcol == gp.maxWorldCol){
                Worldcol = 0;
                Worldrow++;
                
            }
        }

            
    }
}
