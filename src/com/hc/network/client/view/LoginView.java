package com.hc.network.client.view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    private JButton loginButton = null;
    private JTextField nameTextField = null;
    private JComboBox headComboBox = null;
    private int pictureNum = 1;
    public LoginView() {
        setupUI();
        for(int i = 1; i <= 27; i++){
            headComboBox.addItem(Integer.toString(i));
        }
    }
    private void setupUI() {
        JPanel jPanel = new javax.swing.JPanel();
        nameTextField = new javax.swing.JTextField();
        headComboBox = new javax.swing.JComboBox<>();
        loginButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 0));
        setResizable(false);

        jPanel.setBackground(new java.awt.Color(204, 255, 255));

        headComboBox.setFocusable(false);
        headComboBox.setRenderer(new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList list,Object value,int index,boolean isSelected,boolean cellHasFocus){
                setText("");
                setIcon(new ImageIcon("E://NetworkClient/src/com/hc/network/resource/head/Ak" + Integer.parseInt((String)value) + ".png"));
                Color background;
                Color foreground;
                JList.DropLocation dropLocation = list.getDropLocation();
                if (dropLocation != null&& !dropLocation.isInsert()&& dropLocation.getIndex() == index) {
                    background = Color.BLUE;
                    foreground = Color.WHITE;
                } else if (isSelected) {
                    background = Color.RED;
                    foreground = Color.WHITE;
                } else {
                    background = Color.WHITE;
                    foreground = Color.BLACK;
                }
                setBackground(background);
                setForeground(foreground);
                return this;
            }
        });
        headComboBox.addActionListener(evt -> {
            String temp = (String)headComboBox.getSelectedItem();
            pictureNum = Integer.parseInt(temp);
        });

        loginButton.setText("登录");
        loginButton.setFocusable(false);
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dispose();
                ChatView view = new ChatView();
                view.setVisible(true);
                view.setTitle("聊天界面");
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(160, 160, 160)
                                                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(41, 41, 41)
                                                .addComponent(headComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(138, 138, 138)
                                                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(headComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(15, 15, 15)))
                                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(53, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }
}
