package Objects;


import Main.GamePanel;
import entity.Entity;

public class OBJ_Chest  extends Entity
{
    public OBJ_Chest (GamePanel gp){
        super(gp);
        name = "Chest";
        img = setUp("/ObjectsImg/Key.png",1);
        
    }
    
}
