package com.util;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author:智霸霸
 * @Date 2019/10/8
 */
public class ImageTwo {
    private BufferedImage bimg;
    private final static String CODE="NMSL,WSND,CNMD,死狗三鹿";
    private Font font1;
    public BufferedImage getImage()
    {
        bimg=new BufferedImage(150,40,BufferedImage.TYPE_4BYTE_ABGR);

        Graphics2D g=bimg.createGraphics();
            font1=new Font("myfont",Font.BOLD,getRandomSize());
            g.setColor(getRandomColor());
            g.setFont(font1);
            g.drawString(""+getRandomChar(),20+10,25);//drawString(String str,int x,int y)

        return bimg;
    }
    private String getRandomChar() {
        String [] strs=CODE.split(",");
        int a=(int)(Math.random()*strs.length);
        return strs[a];
    }
    //随机大小
    private int getRandomSize() {
        int a=(int)(Math.random()*15)+20;
        return a;
    }
    //随机颜色
    private Color getRandomColor() {

        int r,g,b;
        r=(int)(Math.random()*155)+100;
        g=(int)(Math.random()*155)+100;
        b=(int)(Math.random()*155)+100;
        return new Color(r,g,b);
    }
}
