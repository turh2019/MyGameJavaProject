package entity;

import java.awt.image.BufferedImage;

public class Entity
{   
    public int x,y;
    public int speed;

    public  BufferedImage[] walkUpImgs ,walkDownImgs,walkLeftImgs,walkRightImgs;
    public int numOfSprit;
    public String direction;

    public int spriteCounter =0;
    public int spriteNum =0;
}
