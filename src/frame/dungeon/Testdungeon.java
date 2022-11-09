package frame.dungeon;


import character.Player;
import editor.MyImageIcon;
import frame.EndingFrame;
import frame.FirstFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Testdungeon extends JFrame {
    private JPanel contentpane;
    private JLabel playerLabel, drawpane, monsterLabel;
    private MyImageIcon mapbg, monsterLeftImg, monsterRightImg, playerUp1Img, playerUp2Img, playerUp3Img, playerDown1Img, playerDown2Img, playerDown3Img, playerLeft1Img, playerLeft2Img, playerLeft3Img, playerRight1Img, playerRight2Img, playerRight3Img, playerDownmovementImg;
    private int playerWidth = 33, playerHeight = 47;
    private int frameWidth = 1200, frameHeight = 800;
    private int state,cdHit = 0,cd = 0,level,position,monsterHp;
    private int playerCurX, playerCurY = frameHeight / 2 - playerHeight / 2;
    private int monsterCurX = (frameWidth / 2 - playerWidth / 2) + 100, monsterCurY = (frameHeight / 2 - playerHeight / 2) + 100;
    private boolean playerAlive = true,attack = false, playerrunning = false, playerUp = false, playerDown = false, playerLeft = false, playerRight = false;
    private int[] monsterAlive;
    private String name,skin;
    private FirstFrame _firstFrame;
    private Player player;
    Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();

    public Testdungeon(int _state, int[] _monsterAlive ,int _level,int _position,String _name,String _skin) {
        mapbg = new MyImageIcon("resources/map/Dungeon/BG/" + state + ".png").resize(frameWidth, frameHeight);
        drawpane = new JLabel();
        drawpane.setIcon(mapbg);
        player = new Player(_skin,drawpane);
        player.setPlayerListener(this);
        player.setPlayerName(_name);
        player.setLevel(_level);
        monsterAlive=_monsterAlive;
        state = _state;
        position=_position;
        monsterHp = 5*level;
        contentpane = (JPanel) getContentPane();
        contentpane.setLayout(new BorderLayout());
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setBounds(ss.width / 2 - frameWidth / 2, ss.height / 2 - frameHeight / 2, frameWidth, frameHeight);
        if (position==0){
            player.setPlayerPosition(playerCurX,playerCurY);
        }else {
            playerCurX = frameWidth-50;
            player.setPlayerPosition(playerCurX,playerCurY);
        }
        if (monsterAlive[state-1]==1)
        {
            setMonsterThread(drawpane);
        }
//        player.setPlayerThread();
        contentpane.add(drawpane, BorderLayout.CENTER);
        validate();
    }
    public void setMonsterThread(JLabel drawpane) {
        monsterLeftImg = new MyImageIcon("resources/map/Dungeon/Monster/" + state + "10.png").resize(50, 50);
        monsterRightImg = new MyImageIcon("resources/map/Dungeon/Monster/" + state + "20.png").resize(50, 50);
        monsterLabel = new JLabel(monsterLeftImg);
        monsterLabel.setBounds(monsterCurX, monsterCurY, 50, 50);
        monsterLabel.setVisible(true);
        drawpane.add(monsterLabel);
        monsterLabel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                monsterCurX = monsterCurX+e.getX();
                monsterCurY = monsterCurY+e.getY();
                monsterLabel.setLocation(monsterCurX,monsterCurY);
                if (monsterCurX<0) monsterHp=0;
                if (monsterCurY<0) monsterHp=0;
                if (monsterCurX>frameWidth) monsterHp=0;
                if (monsterCurY>frameHeight) monsterHp=0;
            }
            @Override
            public void mouseMoved(MouseEvent e) {}
        });
        Thread monsterThread = new Thread(() -> {
            while (true) {
                if (playerCurX != monsterCurX) {
                    if (playerCurX > monsterCurX){
                        monsterCurX += 1;
                        monsterLabel.setIcon(monsterRightImg);
                    }else {
                        monsterCurX -= 1;
                        monsterLabel.setIcon(monsterLeftImg);
                    }
                }
                if (playerCurY != monsterCurY) {
                    monsterCurY = playerCurY > monsterCurY ? monsterCurY + 1 : monsterCurY - 1;
                }
                if (attack) {
                    if (getBoundatt().intersects(monsterLabel.getBounds())) {
                        monsterHp-=1;
                        System.out.println("Monster = "+monsterHp);
                        monsterCurX = monsterCurX + (monsterCurX - playerCurX);
                        monsterCurY = monsterCurY + (monsterCurY - playerCurY);
                    }
                }
                monsterLabel.setLocation(monsterCurX, monsterCurY);
                repaint();
                if (monsterHp<=0) {
                    drawpane.remove(monsterLabel);
                    monsterLabel.setBounds(-monsterCurX, -monsterCurY, 50, 50);
                    monsterAlive[state-1] = 0;
                    break;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        monsterThread.start();
    }

    public Rectangle getBoundatt() {
        Rectangle T = playerLabel.getBounds();
        T.width = 30;
        T.height = 30;
        if (playerUp) {
            System.out.println("playerUp");
            T.y = (int) (T.getY() - 25);
        } else if (playerDown) {
            System.out.println("playerDown");
            T.y = (int) (T.getY() + 25+playerHeight);
        } else if (playerRight) {
            System.out.println("playerRight");
            T.x = (int) (T.getX() + 25+playerWidth);
        } else if (playerLeft) {
            System.out.println("playerLeft");
            T.x = (int) (T.getX() - 25);
        }
        return T;
    }
}



