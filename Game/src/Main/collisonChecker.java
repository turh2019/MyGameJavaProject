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



  /*  public int checkObj(Entity entity ,boolean player)
    {
        int index = -1;
        for(int i =0; i < gp.obj.length; i++){
            if(gp.obj[i]!=null){
                entity.soidArea.x = entity.worldX + entity.soidArea.x;
                entity.soidArea.y = entity.worldY + entity.soidArea.y;

                gp.obj[i].soidArea.x = gp.obj[i].worldX + gp.obj[i].soidArea.x;
                gp.obj[i].soidArea.y = gp.obj[i].worldY + gp.obj[i].soidArea.y;  

                switch(entity.direction){
                    case "up":
                    entity.soidArea.y -=entity.speed;
                        if(entity.soidArea.intersects(gp.obj[i].soidArea)){
                            if(gp.obj[i].collision){
                                entity.collisiOn=true;
                            }
                            if(player){
                                index =i;
                            }
                        }
                        break;
                    case "down":
                    entity.soidArea.y += entity.speed;
                        if(entity.soidArea.intersects(gp.obj[i].soidArea)){
                            if(gp.obj[i].collision){
                                entity.collisiOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                    entity.soidArea.x -= entity.speed;
                        if(entity.soidArea.intersects(gp.obj[i].soidArea)){
                            if(gp.obj[i].collision){
                                entity.collisiOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                    entity.soidArea.x +=entity.speed;
                        if(entity.soidArea.intersects(gp.obj[i].soidArea)){
                            if(gp.obj[i].collision){
                                entity.collisiOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;   
                }
                entity.soidArea.x = entity.SolidAreaDefaultX;
                entity.soidArea.y = entity.SolidAreaDefaultY;
                gp.obj[i].soidArea.x =gp.obj[i].SolidAreaDefaultX;
                gp.obj[i].soidArea.y =gp.obj[i].SolidAreaDefaultY;
            }
        }
        return index;
    }*/


    public int CheckEntity(Entity entity, Entity[]  target){
        int index = -1;
        for(int i =0; i < target.length; i++){
            if(target[i] != null)
            {
                entity.soidArea.x = entity.worldX + entity.soidArea.x;
                entity.soidArea.y = entity.worldY + entity.soidArea.y;

                target[i].soidArea.x = target[i].worldX + target[i].soidArea.x;
                target[i].soidArea.y =target[i].worldY + target[i].soidArea.y;  

                switch(entity.direction){
                    case "up":
                    entity.soidArea.y -=entity.speed;
                        if(entity.soidArea.intersects(target[i].soidArea)){
                                entity.collisiOn=true;
                                index =i;
                        }
                        break;
                    case "down":
                    entity.soidArea.y += entity.speed;
                        if(entity.soidArea.intersects(target[i].soidArea)){
                            entity.collisiOn=true;
                            index =i;
                        }
                        break;
                    case "left":
                    entity.soidArea.x -= entity.speed;
                        if(entity.soidArea.intersects(target[i].soidArea)){
                            entity.collisiOn=true;
                            index =i;
                        }
                        break;
                    case "right":
                    entity.soidArea.x +=entity.speed;
                        if(entity.soidArea.intersects(target[i].soidArea)){
                            entity.collisiOn=true;
                            index =i;
                        }
                        break;   
                }
                entity.soidArea.x = entity.SolidAreaDefaultX;
                entity.soidArea.y = entity.SolidAreaDefaultY;
                target[i].soidArea.x = target[i].SolidAreaDefaultX;
                target[i].soidArea.y = target[i].SolidAreaDefaultY;
            }
        }
        return index;
    }
  

    public void checkPlayer(Entity entity){
        
        entity.soidArea.x = entity.worldX + entity.soidArea.x;
        entity.soidArea.y = entity.worldY + entity.soidArea.y;

        gp.player.soidArea.x = gp.player.worldX + gp.player.soidArea.x;
        gp.player.soidArea.y = gp.player.worldY + gp.player.soidArea.y;  

        switch(entity.direction){
            case "up":
            entity.soidArea.y -= entity.speed;
                if(entity.soidArea.intersects(gp.player.soidArea)){
                        entity.collisiOn=true;
                }
                break;
            case "down":
            entity.soidArea.y += entity.speed;
                if(entity.soidArea.intersects(gp.player.soidArea)){
                    entity.collisiOn=true;
                   
                }
                break;
            case "left":
            entity.soidArea.x -= entity.speed;
                if(entity.soidArea.intersects(gp.player.soidArea)){
                    entity.collisiOn=true; 
                }
                break;
            case "right":
            entity.soidArea.x +=entity.speed;
                if(entity.soidArea.intersects(gp.player.soidArea)){
                    entity.collisiOn=true;
                }
                break;   
        }
        entity.soidArea.x = entity.SolidAreaDefaultX;
        entity.soidArea.y = entity.SolidAreaDefaultY;
        gp.player.soidArea.x = gp.player.SolidAreaDefaultX;
        gp.player.soidArea.y = gp.player.SolidAreaDefaultY;
    }

}
