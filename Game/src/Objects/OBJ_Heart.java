package Objects;


import Main.GamePanel;
import entity.Entity;

public class OBJ_Heart  extends Entity{
    

    public OBJ_Heart(GamePanel gp){
        super(gp);
        name = "Heart";
        img = setUp("/ObjectsImg/Life/Heart_Full.png",1);//Heart_Full
        img2 = setUp("/ObjectsImg/Life/Heart_Half.png",1);//Heart_Half
        img3 = setUp("/ObjectsImg/Life/Heart_blank.png",1);//Heart_blank

      
    }
}
