package com.test;

import java.awt.Graphics;
import java.applet.Applet;
import java.awt.*;


public class MyApplet extends Applet {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public void init()
    {
        setBackground(Color.gray);
    }
    public void paint(Graphics g)
    {
        g.setColor(Color.blue);
        g.drawString("Hello world!",30,60);
        g.setColor(Color.red);
        g.drawString("This is my first Applet code", 30, 80);
    }
}