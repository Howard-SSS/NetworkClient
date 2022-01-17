package com.hc.network.client.view.component;

import com.hc.network.client.util.Collector;

import javax.swing.*;
import java.awt.*;

public class ImageRow extends Row {
    private ImageIcon image;
    public ImageRow(int headNum, String name, ImageIcon image) {
        super(headNum, name);
        this.image = image;
    }
    public ImageRow(String name, ImageIcon image) {
        this(0, name, image);
    }
    public ImageRow(ImageIcon image) {
        this("Robot", image);
    }
    @Override
    protected void paintComponent(Graphics g){
        g.drawImage(new ImageIcon("resource/7724/Ak" + headNum + ".png").getImage(), 5, 0, 45, 45, Color.yellow, this);
        g.setFont(Collector.nameFont);
        FontMetrics fm = g.getFontMetrics();
        int nameHeight = fm.getHeight();
        g.setColor(Color.gray);
        g.drawString(name, 5 + 45 + 4, nameHeight);
        g.drawImage(image.getImage(),5 + 45 + 4, 3 + nameHeight, image.getIconWidth(), image.getIconHeight(),this);
    }
}
