package frame;

import editor.MyImageIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Credits extends JFrame {

    private JPanel contentpane;
    private JLabel drawpane;
    private MyImageIcon credit;
    private int frameWidth = 1200, frameHeight = 800;
    private JLabel menuButton;

    Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();

    public Credits(){
        setTitle("SuperTunder");
        setBounds(ss.width / 2 - frameWidth / 2, ss.height / 2 - frameHeight / 2, frameWidth, frameHeight);
        setResizable(false);
        setVisible(true);
        menuButton = new JLabel(new MyImageIcon("resources/button/MENU.png").resize(200,80));
        menuButton.setBounds(20,20,200,80);
        menuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Home();
            }
        });
        contentpane = (JPanel)getContentPane();
        contentpane.setLayout( new BorderLayout() );
        setResizable(false);
        credit = new MyImageIcon("resources/ending/credit.png").resize(frameWidth, frameHeight);
        drawpane = new JLabel();
        drawpane.setIcon(credit);
        getContentPane().add(menuButton);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        contentpane.add(drawpane, BorderLayout.CENTER);
        validate();
    }
}
