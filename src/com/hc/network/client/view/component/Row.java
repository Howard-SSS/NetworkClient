package com.hc.network.client.view.component;

import javax.swing.*;

public class Row extends JComponent {
    protected int headNum;
    protected String name;
    public Row(int headNum, String name) {
        this.headNum = headNum;
        this.name = name;
    }
    public int componentHeight() {
        return 0;
    }
    public int componentWidth() {
        return 0;
    }
}

