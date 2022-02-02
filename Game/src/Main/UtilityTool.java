package Main;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
public class UtilityTool {
    
    public BufferedImage scaleImage(BufferedImage Originl ,int width,int height){
        BufferedImage scaledImage = new BufferedImage(width, height, Originl.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(Originl, 0, 0,width,height,null);
        g2.dispose();
        return scaledImage;
    }
}
