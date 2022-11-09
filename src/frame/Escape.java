package frame;

import editor.MyImageIcon;
import frame.dungeon.Dungeon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Escape extends JFrame {


    private int frameWidth = 1200, frameHeight = 800;
    private JLabel menuButton, backButton;

    Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
    public Escape(JFrame parant){
        setTitle("SuperTunder");
        setBounds(ss.width / 2 - frameWidth / 2, ss.height / 2 - frameHeight / 2, frameWidth, frameHeight);
        setResizable(false);
        setVisible(false);
        backButton = new JLabel(new MyImageIcon("resources/button/BACK.png").resize(200,80));
        backButton.setBounds(500,750,200,80);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parant.setVisible(true);
                setVisible(false);
            }
        });
        menuButton = new JLabel(new MyImageIcon("resources/button/MENU.png").resize(200,80));
        menuButton.setBounds(500,450,200,80);
        menuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parant.dispose();
                dispose();
                new Home();
            }
        });
        setResizable(false);
        getContentPane().add(menuButton);
        getContentPane().add(backButton);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        validate();
    }
}
