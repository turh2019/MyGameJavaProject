package entity;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;
public class Entity
{   
    public int worldX,worldY;
    public int speed;

    public  BufferedImage[] walkUpImgs ,walkDownImgs,walkLeftImgs,walkRightImgs;
    public int numOfSprit;
    public String direction;

    public int spriteCounter =0;
    public int spriteNum =0;
    public Rectangle soidArea;
    public boolean collision;
}
