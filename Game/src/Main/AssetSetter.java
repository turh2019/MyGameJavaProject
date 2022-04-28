package Main;

import Objects.OBJ_KEY;
import entity.NPC_oldMan;
import entity.Monsters.Mon_Slime;

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
        SetMonster();
    }


    public void setObject()
    {
        gp.objs[0] = new OBJ_KEY(gp);
        gp.objs[0].worldX = gp.tileSize * 2;
        gp.objs[0].worldY = gp.tileSize * 2;
    }

    public void SetNPC(){
        gp.npc[0] = new NPC_oldMan(gp);
        gp.npc[0].worldX = gp.tileSize*25;
        gp.npc[0].worldY = gp.tileSize*28;
    }
    public void SetMonster(){
        gp.monsters[0] = new Mon_Slime(gp);
        gp.monsters[0].worldX = gp.tileSize*20;
        gp.monsters[0].worldY = gp.tileSize*20;
    }
}
