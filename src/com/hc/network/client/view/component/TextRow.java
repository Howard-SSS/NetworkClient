package com.hc.network.client.view.component;

import com.hc.network.client.util.Collector;

import javax.swing.*;
import java.awt.*;

public class TextRow extends Row {
    private String text;
    public TextRow(int headNum, String name, String text) {
        super(headNum, name);
        this.text = text;
    }
    public TextRow(String name, String text) {
        this(0, name, text);
    }
    public TextRow(String text) {
        this("Robot", text);
    }
    @Override
    protected void paintComponent(Graphics g){
        int maxWidth = getWidth();
        g.drawImage(new ImageIcon("resource/head/Ak" + headNum + ".png").getImage(), 5, 0, 45, 45, Color.yellow, this);
        g.setFont(Collector.nameFont);
        FontMetrics fm = g.getFontMetrics();
        int nameHeight = fm.getHeight();
        g.setColor(Color.gray);
        g.drawString(name, 5 + 45 + 4, nameHeight);


        fm = g.getFontMetrics();
        int textHeight = fm.getHeight();
        int textWidth = fm.stringWidth(text);

        int cols = textWidth > maxWidth ? maxWidth : textWidth;
        int rows = (textWidth / maxWidth+1) * textHeight;
        g.setColor(Color.green);
        g.fillRoundRect( 54, 3 + nameHeight, cols+6, rows+6, 2, 2);

        g.setColor(Color.black);
        g.drawString(text, 58, 3+nameHeight+textHeight);
    }
}
