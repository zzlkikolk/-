package com.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author:智霸霸
 * @Date 2019/8/31
 */
public class Image {
    private BufferedImage bimg;
    static String VCode[]=new String[5];
    private final  static String charset="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private Font font1;
    public BufferedImage getImage()
    {
        bimg=new BufferedImage(150,40,BufferedImage.TYPE_4BYTE_ABGR);

        Graphics2D g=bimg.createGraphics();
        for(int i=0;i<5;i++)
        {
            font1=new Font("myfont",Font.BOLD,getRandomSize());
            g.setColor(getRandomColor());
            g.setFont(font1);
            String me=String.valueOf(getRandomChar());
            g.drawString(""+me,20*i+10,25);//drawString(String str,int x,int y)
            VCode[i]=me;
        }
        return bimg;
    }
    //随机大小
    private int getRandomSize() {
        int a=(int)(Math.random()*15)+20;
        return a;
    }
    //随机字符
    private char getRandomChar() {

        int a=(int)(Math.random()*charset.length());
        return charset.charAt(a);
    }
    //随机颜色
    private Color getRandomColor() {

        int r,g,b;
        r=(int)(Math.random()*155)+100;
        g=(int)(Math.random()*155)+100;
        b=(int)(Math.random()*155)+100;
        return new Color(r,g,b);
    }
    public String getVcode()
    {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < VCode.length; i++){
            sb. append(VCode[i]);
        }
        String me=sb.toString();
        return me;
    }
}
