package frame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import editor.MyImageIcon;
import editor.MySoundEffect;

public class EndingFrame extends JFrame {
    private JPanel contentpane;
    private JLabel playerLabel, drawpane;
    private MyImageIcon endingbg, playerDown1Img,playerDownmovementImg;
    private int playerWidth = 60, playerHeight = 60;

    private int frameWidth = 1200, frameHeight = 800;
    private int playerCurX = frameWidth / 2 - playerWidth / 2, playerCurY = frameHeight / 2 - playerHeight / 2;
    private String name,skin;
    private JLabel menuButton, homeButton;
    private JLabel textEnding;
    private boolean dungeon;
    private MySoundEffect heroThemeSound;
    Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();

    public EndingFrame(String name,String skin,boolean died,int level){
        setTitle("SuperTunder");
        setBounds(ss.width / 2 - frameWidth / 2, ss.height / 2 - frameHeight / 2, frameWidth, frameHeight);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentpane = (JPanel)getContentPane();
        contentpane.setLayout( new BorderLayout() );
        drawpane = new JLabel();
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("prstart.ttf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
            if (!died) {
                textEnding = new JLabel("<html>!!!CONGRATULATION!!!<br/> PLAYER : "+name.toUpperCase()+"<br/>YOU BEAT THE GAME<br/>WE HOPE YOU ENJOY<br/>THANK YOU FOR PLAYING</html>");
                textEnding.setBounds(400,450,450,250);
                textEnding.setFont(font.deriveFont(Font.BOLD, 18));
                textEnding.setForeground(Color.white);
                drawpane.add(textEnding);
            }
        }catch (FontFormatException | IOException e){
            e.printStackTrace();
        }
        menuButton = new JLabel(new MyImageIcon("resources/button/MENU.png").resize(200,80));
        menuButton.setBounds(100,600,200,80);
        menuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Home();
                heroThemeSound.stop();
            }
        });
        homeButton = new JLabel(new MyImageIcon("resources/button/BAN.png").resize(200,80));
        homeButton.setBounds(frameWidth-(300),600,200,80);
        homeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Ban(name,skin,dungeon,level);
                heroThemeSound.stop();
            }
        });
        if (!died){
            dungeon = false;
            endingbg = new MyImageIcon("resources/gameresult/victory.png").resize(frameWidth, frameHeight);
            heroThemeSound = new MySoundEffect("resources/victory.wav");
            heroThemeSound.playOnce();
            playerDown1Img = new MyImageIcon("resources/player/"+skin+"/D1.png").resize(playerWidth, playerHeight);
            playerDownmovementImg = new MyImageIcon("resources/player/"+skin+"/STOP.png").resize(playerWidth, playerHeight);
        }else {
            dungeon = true;
            endingbg = new MyImageIcon("resources/gameresult/gameover.png").resize(frameWidth, frameHeight);
            heroThemeSound = new MySoundEffect("resources/gameover.wav");
            heroThemeSound.playOnce();
            playerDown1Img = new MyImageIcon("resources/player/dead1.png").resize(playerWidth, playerHeight);
            playerDownmovementImg = new MyImageIcon("resources/player/dead2.png").resize(playerWidth, playerHeight);
        }
        playerLabel = new JLabel(playerDown1Img);
        playerLabel.setBounds(playerCurX,playerCurY,playerWidth,playerHeight);
        playerLabel.setVisible(true);
        drawpane.setIcon(endingbg);
        drawpane.add(playerLabel);
        drawpane.add(menuButton);
        drawpane.add(homeButton);
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
            int moveMent=0;
            while (true)
            {
                    if (moveMent%200==100){
                        playerLabel.setIcon(playerDownmovementImg);
                    }else if(moveMent%200==0){
                        playerLabel.setIcon(playerDown1Img);
                    }
                playerLabel.setLocation(playerCurX, playerCurY);
                repaint();
                try { Thread.sleep(7); moveMent += 1;}
                catch (InterruptedException e) { e.printStackTrace(); }
            } // end while
        }); // end thread creation
        playerThread.start();
    }
}
