package com.hc.network.client.view.component;

import com.hc.network.client.util.Collector;
import sun.font.FontDesignMetrics;

import javax.swing.*;
import java.awt.*;

public class ImageRow extends Row {
    private ImageIcon image;
    public ImageRow(int headNum, String name, ImageIcon image) {
        super(headNum, name);
        this.image = image;
    }
    public ImageRow(String name, ImageIcon image) {
        this(Collector.defaultHeadNum, name, image);
    }
    public ImageRow(ImageIcon image) {
        this(Collector.defaultName, image);
    }
    @Override
    protected void paintComponent(Graphics g){
        g.drawImage(new ImageIcon("resource/head/Ak" + headNum + ".png").getImage(), 5, 0, 45, 45, Color.yellow, this);
        g.setFont(Collector.nameFont);
        int nameHeight = g.getFontMetrics().getHeight();
        g.setColor(Collector.nameColor);
        g.drawString(name, 5 + 45 + 5, nameHeight);
        int[] res = scaleSize();
        int width = res[0], height = res[1];
        g.drawImage(image.getImage(),5 + 45 + 5, 3 + nameHeight, width, height,this);
    }

    /**
     *
     * @return {width, height}
     */
    private int[] scaleSize() {
        return new int[]{image.getIconWidth(), image.getIconHeight()};
    }
    @Override
    public int componentHeight() {
        FontMetrics fm = FontDesignMetrics.getMetrics(Collector.nameFont);
        int nameHeight = fm.getHeight();
        int contentHeight = scaleSize()[1] + 3 + nameHeight + 5, headHeight = 45;
        return contentHeight > headHeight ? contentHeight : headHeight;
    }
    @Override
    public int componentWidth() {
        return scaleSize()[0] + 5 + 45 + 5;
    }
}
