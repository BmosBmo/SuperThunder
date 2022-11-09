package frame;


import editor.MyImageIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class signText extends JFrame {
    private JPanel contentpane;
    private int frameWidth = 1200, frameHeight = 800;
    private JLabel backButton,drawpane;
    Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
    public signText() {
        setTitle("SuperTunder");
        setBounds(ss.width / 2 - frameWidth / 2, ss.height / 2 - frameHeight / 2, frameWidth, frameHeight);
        backButton = new JLabel(new MyImageIcon("resources/button/BACK.png").resize(200,80));
        backButton.setBounds(500,600,200,80);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        setResizable(false);
        setVisible(true);
        contentpane = (JPanel)getContentPane();
        contentpane.setLayout( new BorderLayout() );
        drawpane = new JLabel();
        drawpane.setIcon(new MyImageIcon("resources/object/signtext.png").resize(frameWidth, frameHeight));
        drawpane.add(backButton);
        contentpane.add(drawpane, BorderLayout.CENTER);
        validate();
    }
}
