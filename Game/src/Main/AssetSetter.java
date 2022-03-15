package Main;

import Objects.OBJ_KEY;
import entity.NPC_oldMan;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp)
    {
            this.gp =gp;
    }


    public void update()
    {
        setObject();
        SetNPC();
    }


    public void setObject()
    {
      gp.objs[0] = new OBJ_KEY(gp);
      gp.objs[0].worldX = gp.tileSize * 21;
      gp.objs[0].worldY = gp.tileSize * 22;
    }

    public void SetNPC(){
        gp.npc[0] = new NPC_oldMan(gp);
        gp.npc[0].worldX = gp.tileSize*25;
        gp.npc[0].worldY = gp.tileSize*28;
    }
}
