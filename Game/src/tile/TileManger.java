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
        mapTileNum = new  int [gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap("/maps/map_01.txt");
    }

    public void getTileImage()
    {
            try{
                tile[0] = new Tile();
                tile[0].img = ImageIO.read(getClass().getResourceAsStream("/res/tiles/grass.png"));

     

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

            while(col <gp.maxScreenCol&& row<gp.maxScreenRow){
                String line;
                
                    line = br.readLine();
            

                while(col<gp.maxScreenCol){
                    String numbers[] =line.split(" ");
                    int num =Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol){
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
        int col = 0;
        int row = 0;
        int x = 0;
        int y= 0;
        while (col <gp.maxScreenCol && row< gp.maxScreenRow) 
        {
            int tileNum =mapTileNum[col][row];
            g2.drawImage(tile[tileNum].img, x, y ,gp.tileSize,gp.tileSize,null);
            col++;
            x +=gp.tileSize;
            if(col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }

            
    }
}
