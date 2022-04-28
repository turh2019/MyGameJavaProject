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



   public int checkObj(Entity entity ,boolean player)
    {
        int index = -1;
        for(int i =0; i < gp.objs.length; i++){
            if(gp.objs[i]!=null){
                entity.soidArea.x = entity.worldX + entity.soidArea.x;
                entity.soidArea.y = entity.worldY + entity.soidArea.y;

                gp.objs[i].soidArea.x = gp.objs[i].worldX + gp.objs[i].soidArea.x;
                gp.objs[i].soidArea.y = gp.objs[i].worldY + gp.objs[i].soidArea.y;  

                switch(entity.direction){
                    case "up": entity.soidArea.y -=entity.speed;  break;
                    case "down":  entity.soidArea.y += entity.speed; break;
                    case "left": entity.soidArea.x -= entity.speed;  break;
                    case "right": entity.soidArea.x +=entity.speed; break;   
                }

                if(entity.soidArea.intersects(gp.objs[i].soidArea)){
                    if(gp.objs[i].collision){
                        entity.collisiOn = true;
                    }
                    if(player){
                        index = i;
                    }
                }

                entity.soidArea.x = entity.SolidAreaDefaultX;
                entity.soidArea.y = entity.SolidAreaDefaultY;
                gp.objs[i].soidArea.x =gp.objs[i].SolidAreaDefaultX;
                gp.objs[i].soidArea.y =gp.objs[i].SolidAreaDefaultY;
            }
        }
        return index;
    }


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
                    case "up": entity.soidArea.y -=entity.speed; break;
                    case "down": entity.soidArea.y += entity.speed; break;
                    case "left":  entity.soidArea.x -= entity.speed; break;
                    case "right":  entity.soidArea.x +=entity.speed; break;   
                }

            if(entity.soidArea.intersects(target[i].soidArea)){
                if(target[i] !=entity){
                    entity.collisiOn=true;
                    index =i;
                }
            }
                entity.soidArea.x = entity.SolidAreaDefaultX;
                entity.soidArea.y = entity.SolidAreaDefaultY;
                target[i].soidArea.x = target[i].SolidAreaDefaultX;
                target[i].soidArea.y = target[i].SolidAreaDefaultY;
            }
        }
        return index;
    }
  

    public boolean checkPlayer(Entity entity){
        
        boolean contactPlayer =false;

        entity.soidArea.x = entity.worldX + entity.soidArea.x;
        entity.soidArea.y = entity.worldY + entity.soidArea.y;

        gp.player.soidArea.x = gp.player.worldX + gp.player.soidArea.x;
        gp.player.soidArea.y = gp.player.worldY + gp.player.soidArea.y;  

        switch(entity.direction){
            case "up": entity.soidArea.y -= entity.speed;  break;
            case "down": entity.soidArea.y += entity.speed;  break;
            case "left": entity.soidArea.x -= entity.speed;  break;
            case "right": entity.soidArea.x +=entity.speed; break;   
        }
        if(entity.soidArea.intersects(gp.player.soidArea))
        {
            entity.collisiOn=true;
            contactPlayer = true ;
        }

        entity.soidArea.x = entity.SolidAreaDefaultX;
        entity.soidArea.y = entity.SolidAreaDefaultY;
        gp.player.soidArea.x = gp.player.SolidAreaDefaultX;
        gp.player.soidArea.y = gp.player.SolidAreaDefaultY;

        return contactPlayer;
    }

}
