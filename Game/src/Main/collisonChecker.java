package Main;

import entity.Entity;
public class collisonChecker {

    GamePanel gp;
    public collisonChecker(GamePanel gp){
        this.gp =gp;
    }

    public void checkTile(Entity  entity)
    {
        int entityLeftWorldX= entity.worldX + entity.soidArea.x;
        int entityRightWorldX = entity.worldX + entity.soidArea.x +entity.soidArea.width;
        int entityTopWorldY= entity.worldY + entity.soidArea.y;
        int entityBottomWorldY = entity.worldY + entity.soidArea.y +entity.soidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tile_1 ,tile_2;   

        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tile_1 =gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tile_2 =gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tile_1].collision || gp.tileM.tile[tile_2].collision)
                {
                    entity.collisiOn =true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tile_1 =gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tile_2 =gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tile_1].collision || gp.tileM.tile[tile_2].collision)
                {
                    entity.collisiOn =true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX- entity.speed)/gp.tileSize;
                tile_1 =gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tile_2 =gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tile_1].collision || gp.tileM.tile[tile_2].collision)
                {
                    entity.collisiOn =true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tile_1 =gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tile_2 =gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tile_1].collision || gp.tileM.tile[tile_2].collision)
                {
                    entity.collisiOn =true;
                }
                break;    
        }
    }



    public int checkObj(Entity e ,boolean player)
    {
        int index = 999;
        for(int i =0; i < gp.obj.length; i++){
            if(gp.obj[i]!=null){
                e.soidArea.x = e.worldX + e.soidArea.x;
                e.soidArea.y = e.worldY + e.soidArea.y;

                gp.obj[i].soidArea.x = gp.obj[i].worldX + gp.obj[i].soidArea.x;
                gp.obj[i].soidArea.y = gp.obj[i].worldY + gp.obj[i].soidArea.y;  

                switch(e.direction){
                    case "up":
                        e.soidArea.y -=e.speed;
                        if(e.soidArea.intersects(gp.obj[i].soidArea)){
                            if(gp.obj[i].collision){
                                e.collisiOn=true;
                            }
                            if(player){
                                index =i;
                            }
                        }
                        break;
                    case "down":
                        e.soidArea.y +=e.speed;
                        if(e.soidArea.intersects(gp.obj[i].soidArea)){
                            if(gp.obj[i].collision){
                                e.collisiOn=true;
                            }
                            if(player){
                                index =i;
                            }
                        }
                        break;
                    case "left":
                        e.soidArea.x -=e.speed;
                        if(e.soidArea.intersects(gp.obj[i].soidArea)){
                            if(gp.obj[i].collision){
                                e.collisiOn=true;
                            }
                            if(player){
                                index =i;
                            }
                        }
                        break;
                    case "right":
                        e.soidArea.x +=e.speed;
                        if(e.soidArea.intersects(gp.obj[i].soidArea)){
                            if(gp.obj[i].collision){
                                e.collisiOn=true;
                            }
                            if(player){
                                index =i;
                            }
                        }
                        break;   
                }
                e.soidArea.x =e.SolidAreaDefaultX;
                e.soidArea.y =e.SolidAreaDefaultY;
                gp.obj[i].soidArea.x =gp.obj[i].SolidAreaDefaultX;
                gp.obj[i].soidArea.y =gp.obj[i].SolidAreaDefaultY;
            }
        }
        return index;
    }
  
}
