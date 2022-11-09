package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;

public class RiverScene extends JPanel implements ActionListener {
    private Image img;
    private Timer time;
    public RiverScene(){
        ImageIcon i = new ImageIcon("resources/ending/1.png");
        img = i.getImage();
        setFocusable(true);
        time = new Timer(5,this);
        time.start();
    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(img,0,0,null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
