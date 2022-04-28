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
        int maxmonster =5;
        for(int i = 0 ; i < maxmonster;i++){
            gp.monsters[i] = new Mon_Slime(gp);
            gp.monsters[i].worldX = gp.tileSize* (20 + i);
            gp.monsters[i].worldY = gp.tileSize* (20 + i);
        }

    }
}
