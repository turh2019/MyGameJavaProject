package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Main.GamePanel;
import java.awt.Graphics2D;


public class TileManger {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];
    public TileManger(GamePanel gp){

        this.gp =gp;

        tile = new Tile[10];
        mapTileNum = new  int [gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/map_01.txt");
    }

    public void getTileImage()
    {
            try{
                tile[0] = new Tile();
                tile[0].img = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

                tile[1] = new Tile();
                tile[1].img = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
     

            }catch(IOException e)
            {
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
                    if(num!=0)
                    System.out.println("num : " +num);

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
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX 
            && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
            && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY 
            && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
            {
                if(tileNum !=0)
                System.out.println(tileNum);
                g2.drawImage(tile[tileNum].img, screenX, screenY , gp.tileSize, gp.tileSize, null);
            }
            Worldcol++;  
            if(Worldcol == gp.maxWorldCol){
                Worldcol = 0;
                Worldrow++;
                
            }
        }

            
    }
}
