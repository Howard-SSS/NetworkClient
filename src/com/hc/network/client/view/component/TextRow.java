package com.hc.network.client.view.component;

import com.hc.network.client.util.Collector;
import sun.font.FontDesignMetrics;

import javax.swing.*;
import java.awt.*;

public class TextRow extends Row {
    private String text;
    public TextRow(int headNum, String name, String text) {
        super(headNum, name);
        this.text = text;
    }
    public TextRow(String name, String text) {
        this(Collector.defaultHeadNum, name, text);
    }
    public TextRow(String text) {
        this(Collector.defaultName, text);
    }
    private int[] textCalculate(String text) {
        int maxWidth = Collector.chatViewWidth - 5 - 45 - 5 - 2 * 3;
        FontMetrics fm = FontDesignMetrics.getMetrics(Collector.textFont);
        int textHeight = fm.getHeight();
        int textWidth = fm.stringWidth(text);
        int cols = textWidth > maxWidth ? maxWidth : textWidth;
        int rows = (int)Math.ceil((double)textWidth / cols) * textHeight;
        return new int[]{rows, cols};
    }
    public int calculateHeight(String text) {
        FontMetrics fm = FontDesignMetrics.getMetrics(Collector.nameFont);
        int nameHeight = fm.getHeight();
        return textCalculate(text)[0] + 2 * 3 + nameHeight;
    }
    @Override
    protected void paintComponent(Graphics g){
        g.drawImage(new ImageIcon("resource/head/Ak" + headNum + ".png").getImage(), 5, 0, 45, 45, Color.yellow, this);
        g.setColor(Color.black);
        g.setFont(Collector.nameFont);
        int nameHeight = g.getFontMetrics().getHeight();
        g.setColor(Color.gray);
        g.drawString(name, 5 + 45 + 5, nameHeight);

        int[] res = textCalculate(text);
        int cols = res[1];
        int rows = res[0];
        g.setColor(Color.green);
        g.fillRoundRect( 55, 3 + nameHeight, cols + 6, rows + 6, 2, 2);// 3 rect 3

        g.setColor(Color.black);
        g.setFont(Collector.textFont);
        int textHeight = g.getFontMetrics().getHeight();
        g.drawString(text, 55 + 3, 3 + nameHeight+textHeight);// 3 str 3
    }
}
