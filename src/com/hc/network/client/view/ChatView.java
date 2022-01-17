package com.hc.network.client.view;

import com.hc.network.client.model.MessageAbstract;
import com.hc.network.client.model.User;
import com.hc.network.client.model.content.*;
import com.hc.network.client.network.NetworkManage;
import com.hc.network.client.network.NetworkPost;
import com.hc.network.client.util.Collector;
import com.hc.network.client.util.Translate;
import com.hc.network.client.view.component.ImageRow;
import com.hc.network.client.view.component.Row;
import com.hc.network.client.view.component.TextRow;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatView extends JFrame{
    private NetworkPost networkPost;

    private JButton pictureButton;
    private JButton sendButton;
    private JButton uploadButton;
    private javax.swing.JList<User> userList;
    private JMenuBar menuBar;
    private JMenu editMenu;
    private JMenu fileMenu;
    private JMenuItem setMenuItem;
    private JPanel chatPanel;
    private JScrollPane inputScrollPane;
    private JScrollPane userScrollPane;
    private JScrollPane outPutScrollPane;
    private JTextArea inputTextArea;
    public JTextPane outPutTextPane;
    private JToolBar handleToolbar;

    public ChatView() {
        initComponents();
        networkPost = new NetworkPost() {
            private NetworkManage manage = new NetworkManage();
            private ExecutorService mService = Executors.newSingleThreadExecutor();
            private Thread mThread = null;
            @Override
            public void msocketAccept() {
                MulticastSocket socket = getMulticastSocket();
                if (socket == null)
                    return;
                mThread = new Thread(() -> {
                    byte[] bytes = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
                    while (true) {
                        try {
                            socket.receive(packet);
                            Object object = Translate.ByteToObject(bytes);
                            User user = manage.getUser(object.toString());
                            Row row = null;
                            if (object instanceof TextModel) {
                                TextModel model = (TextModel) object;
                                row = new TextRow(user.getHeadNum(), model.getName(), model.getText());
                            } else if (object instanceof LoginModel) {
                                LoginModel model = (LoginModel) object;
                                row = new TextRow(user.getHeadNum(), model.getName(), model.getText());
                                loginUser(user);
                                manage.setUser(user);
                            } else if (object instanceof QuitModel) {
                                QuitModel model = (QuitModel) object;
                                row = new TextRow(user.getHeadNum(), model.getName(), model.getText());
                                quitUser(user);
                                manage.removeUser(user);
                            } else if (object instanceof ImageModel) {
                                ImageModel model = (ImageModel) object;
                                ImageIcon image = (ImageIcon) Translate.ByteToObject(model.getBytes());
                                if (image == null)
                                    continue;
                                row = new ImageRow(user.getHeadNum(), model.getName(), image);
                            }
                            outPutTextPane.insertComponent(row);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                mService.submit(mThread);
            }

            @Override
            public void bsocketAccept() {

            }
        };
        userList.setModel(new DefaultListModel<>());
        networkPost.msocketAccept();
        loginUser(new User(Collector.id, Collector.name, Collector.headNum));
    }

    /**
     * list界面插入user
     * @param user
     */
    private void loginUser(User user) {
        DefaultListModel model = (DefaultListModel)userList.getModel();
        model.addElement(user);
        userList.setModel(model);
    }

    /**
     * list界面推出user
     * @param user
     */
    private void quitUser(User user) {
        DefaultListModel model = (DefaultListModel)userList.getModel();
        for (int i = 0; i < model.size(); i++) {
            if (user.toString().equals(model.toString())) {
                model.remove(i);
                break;
            }
        }
        userList.setModel(model);
    }
    private void initComponents() {
        chatPanel = new JPanel();
        sendButton = new JButton();
        inputScrollPane = new JScrollPane();
        inputTextArea = new JTextArea();
        userScrollPane = new JScrollPane();
        userList = new JList<>();
        handleToolbar = new JToolBar();
        pictureButton = new JButton();
        outPutScrollPane = new JScrollPane();
        outPutTextPane = new JTextPane();
        uploadButton = new JButton();
        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        editMenu = new JMenu();
        setMenuItem = new JMenuItem();

        initMenuBar();
        initInputTextArea();
        initOutputTextPane();
        initHandleToolbar();
        initSendButton();
        initUserList();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 102, 51));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                networkPost.sendQuit();
            }
        });

        chatPanel.setBackground(new java.awt.Color(153, 255, 51));

        GroupLayout jPanel1Layout = new GroupLayout(chatPanel);
        chatPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(inputScrollPane, GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                                        .addComponent(outPutScrollPane))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(userScrollPane, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(userScrollPane, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
                                                .addGap(11, 11, 11)
                                                .addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addComponent(outPutScrollPane, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(inputScrollPane, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(19, Short.MAX_VALUE))
        );


        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(chatPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(handleToolbar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(handleToolbar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chatPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }
    private void initMenuBar() {
        fileMenu.setText("文件");
        editMenu.setText("编辑");
        setMenuItem.setIcon(new ImageIcon("resource/tool/Gear.png"));
        setMenuItem.setText("设置");
        setMenuItem.addActionListener(evt -> {

        });
        fileMenu.add(setMenuItem);
        menuBar.add(fileMenu).add(editMenu);

        setJMenuBar(menuBar);
    }
    private void initHandleToolbar() {
        handleToolbar.setBackground(new java.awt.Color(204, 204, 204));
        handleToolbar.setRollover(true);

        pictureButton.setIcon(new ImageIcon("resource/tool/photo.png"));
        pictureButton.setFocusable(false);
        pictureButton.setHorizontalTextPosition(SwingConstants.CENTER);
        pictureButton.setOpaque(false);
        pictureButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        pictureButton.addActionListener(evt -> {
            // 新建界面
        });
        uploadButton.setIcon(new ImageIcon("resource/tool/upload.png"));
        uploadButton.setFocusable(false);
        uploadButton.setHorizontalTextPosition(SwingConstants.CENTER);
        uploadButton.setOpaque(false);
        uploadButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        uploadButton.addActionListener(evt -> {
            // 新建界面
        });
        handleToolbar.add(pictureButton);
        handleToolbar.add(uploadButton);
    }
    private void initSendButton() {
        sendButton.setText("发送");
        sendButton.setFocusable(false);
        sendButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String text = inputTextArea.getText();
                if (text.equals(""))
                    return;
                inputTextArea.setText("");
                networkPost.sendText(text);
            }
        });
    }
    private void initInputTextArea() {
        inputTextArea.setColumns(20);
        inputTextArea.setLineWrap(true);
        inputTextArea.setTabSize(0);
        inputScrollPane.setViewportView(inputTextArea);
    }
    private void initUserList() {
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userList.setCellRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                User user = (User)value;
                setText(user.getName());
                setIcon(new ImageIcon("resource/head/Ak" + user.getHeadNum() + ".png"));
                Color background;
                Color foreground;
                JList.DropLocation dropLocation = list.getDropLocation();
                if (dropLocation != null&& !dropLocation.isInsert()&& dropLocation.getIndex() == index) {
                    background = Color.BLUE;
                    foreground = Color.WHITE;
                }else if (isSelected) {
                    background=new Color(152, 245, 255);
                    foreground=list.getSelectionForeground();
                } else {
                    background=list.getBackground();
                    foreground=list.getForeground();
                }
                setBackground(background);
                setForeground(foreground);
                return this;
            }
        });
        userList.setFocusable(false);
        userList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(evt.getClickCount() == 2){

                }
            }
        });
        userScrollPane.setViewportView(userList);
    }
    private void initOutputTextPane() {
        outPutTextPane.setEditable(false);
        outPutTextPane.setBorder(null);
        outPutTextPane.setFocusable(false);
        outPutTextPane.setMaximumSize(new java.awt.Dimension(437, 280));
        outPutTextPane.setMinimumSize(new java.awt.Dimension(437, 280));
        outPutScrollPane.setViewportView(outPutTextPane);
    }
}
