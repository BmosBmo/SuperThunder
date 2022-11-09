package frame;

import editor.MyImageIcon;
import editor.MySoundEffect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class River extends JFrame {

    private JPanel contentpane;
    private JLabel playerLabel, drawpane, riverLabel;
    private MyImageIcon riverScene, playerDown1Img;
    private int playerWidth = 60, playerHeight = 60;
    private int frameWidth = 1200, frameHeight = 800;
    private int playerCurX = 0, playerCurY = 600, riverCurX;
    private String name,skin;
    private MySoundEffect heroThemeSound;
    private JLabel skipButton;
    private boolean run = true;
    Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();

    public River(String _name,String _skin){
        name = _name;
        skin = _skin;
        setTitle("SuperTunder");
        setBounds(ss.width / 2 - frameWidth / 2, ss.height / 2 - frameHeight / 2, frameWidth, frameHeight);
        setResizable(false);
        setVisible(true);
        heroThemeSound = new MySoundEffect("resources/River.wav");
        heroThemeSound.playLoop();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("prstart.ttf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
        }catch (FontFormatException | IOException e){
            e.printStackTrace();
        }
        contentpane = (JPanel)getContentPane();
        contentpane.setLayout( new BorderLayout() );
        skipButton = new JLabel(new MyImageIcon("resources/button/SKIP.png").resize(100,40));
        skipButton.setBounds(1050,700,100,40);
        skipButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Credits();
                heroThemeSound.stop();
                run = false;
            }
        });
        playerDown1Img = new MyImageIcon("resources/player/"+skin+"/Boat/B5.png").resize(playerWidth*2, playerHeight*2);
        riverScene = new MyImageIcon("resources/ending/river.png").resize(frameWidth*3, frameHeight);
        riverLabel = new JLabel(riverScene,SwingConstants.LEFT);
        riverLabel.setBounds(0,0,frameWidth*3,frameHeight);
        playerLabel = new JLabel(playerDown1Img);
        playerLabel.setBounds(playerCurX, playerCurY,playerWidth*2,playerHeight*2);
        playerLabel.setVisible(true);
        drawpane = new JLabel();
        drawpane.add(playerLabel);
        drawpane.add(skipButton);
        drawpane.add(riverLabel);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setPlayerThread();
        contentpane.add(drawpane, BorderLayout.CENTER);
        validate();
    }

    public void setPlayerThread()
    {
        // end run
        Thread playerThread = new Thread(() -> {
            while (run)
            {
                if (playerCurX<frameWidth/2) {
                    playerCurX += 1;
                    playerLabel.setLocation(playerCurX, playerCurY);
                    repaint();
                }else {
                    if (Math.abs(riverCurX)!=frameWidth*2) {
                        riverCurX -= 1;
                        riverLabel.setLocation(riverCurX, 0);
                        repaint();
                    }else {
                        playerCurX += 1;
                        playerLabel.setLocation(playerCurX, playerCurY);
                        repaint();
                    }
                }
                if (playerCurX>frameWidth){
                    this.dispose();
                    new Credits();
                    run = false;
                    heroThemeSound.stop();
                    break;
                }
                try { Thread.sleep(7);}
                catch (InterruptedException e) { e.printStackTrace(); }
            } // end while
        }); // end thread creation
        playerThread.start();
    }
}
