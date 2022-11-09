package frame;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;

import editor.MyImageIcon;
import editor.MyLabel;
import editor.MySoundEffect;
import frame.dungeon.Dungeon;

public class FirstFrame extends JFrame implements KeyListener{
    private JPanel contentpane;
	private JLabel playerLabel, drawpane, objectdoor, objectcave;
	private MyImageIcon door,cave, playerUp1Img,playerUp2Img,playerUp3Img, playerDown1Img,playerDown2Img,playerDown3Img, playerLeft1Img,playerLeft2Img,playerLeft3Img, playerRight1Img,playerRight2Img,playerRight3Img,playerDownmovementImg;
	private int playerWidth = 60, playerHeight = 60;
	private int frameWidth = 1200, frameHeight = 800;
	private int playerCurX, playerCurY ;
	private boolean playerAlive = true,playerrunning = false, playerUp = false,playerDown= false,playerLeft= false,playerRight= false,dungeon;
    private MyLabel signLabel;
    private String name,skin;
    private int level;
    private JLabel boat;
    private Escape escape;
    private MySoundEffect heroThemeSound;
    private int[] monsterAlive;

    Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();

	public FirstFrame(int _level, String _name, String _skin,boolean _dungeon,int position){
        dungeon = _dungeon;
        level=_level;
        monsterAlive = new int[level];
        escape = new Escape(this);
        Arrays.fill(monsterAlive, 1);
        skin = _skin;
        name = _name;
        System.out.println(skin);
        setTitle("SuperTunder");
        setBounds(ss.width / 2 - frameWidth / 2, ss.height / 2 - frameHeight / 2, frameWidth, frameHeight);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                heroThemeSound.stop();
            }
        });
        contentpane = (JPanel)getContentPane();
        contentpane.setLayout( new BorderLayout() );
		setResizable(false);
		boat = new JLabel(new MyImageIcon("resources/object/boat.png").resize(70,70));
        boat.setBounds(200,550,120,120);
        playerDown1Img = new MyImageIcon("resources/player/"+ skin +"/D1.png").resize(playerWidth, playerHeight);
        playerDown2Img = new MyImageIcon("resources/player/"+ skin +"/D2.png").resize(playerWidth, playerHeight);
        playerDown3Img = new MyImageIcon("resources/player/"+ skin +"/D3.png").resize(playerWidth, playerHeight);
        playerUp1Img = new MyImageIcon("resources/player/"+ skin +"/U1.png").resize(playerWidth, playerHeight);
        playerUp2Img = new MyImageIcon("resources/player/"+ skin +"/U2.png").resize(playerWidth, playerHeight);
        playerUp3Img = new MyImageIcon("resources/player/"+ skin +"/U3.png").resize(playerWidth, playerHeight);
        playerLeft1Img = new MyImageIcon("resources/player/"+ skin +"/L1.png").resize(playerWidth, playerHeight);
        playerLeft2Img = new MyImageIcon("resources/player/"+ skin +"/L2.png").resize(playerWidth, playerHeight);
        playerLeft3Img = new MyImageIcon("resources/player/"+ skin +"/L3.png").resize(playerWidth, playerHeight);
        playerRight1Img = new MyImageIcon("resources/player/"+ skin +"/R1.png").resize(playerWidth, playerHeight);
        playerRight2Img = new MyImageIcon("resources/player/"+ skin +"/R2.png").resize(playerWidth, playerHeight);
        playerRight3Img = new MyImageIcon("resources/player/"+ skin +"/R3.png").resize(playerWidth, playerHeight);
        playerDownmovementImg = new MyImageIcon("resources/player/"+ skin +"/STOP.png").resize(playerWidth, playerHeight);
        MyImageIcon mapbg = new MyImageIcon("resources/map/MAP1.png").resize(frameWidth, frameHeight);
        door = new MyImageIcon("resources/object/Door.png").resize(52, 52);
        cave = new MyImageIcon("resources/object/Cavemouth.png").resize(52, 52);
        objectcave = new JLabel(cave);
        objectdoor = new JLabel(door);
        objectdoor.setBounds(120,145,52,52);
        objectcave.setBounds(1125,15,37,37);
        playerLabel = new JLabel(playerDown1Img);
        heroThemeSound = new MySoundEffect("resources/Firstframe.wav");
        heroThemeSound.playLoop();
        if (position==0){
            playerCurX = 97;
            playerCurY = 177;
        }else {
            playerCurX = 1110;
            playerCurY = 7;
        }
	    playerLabel.setBounds(playerCurX,playerCurY,playerWidth,playerHeight);
	    playerLabel.setVisible(true);
        drawpane = new JLabel();
        if (dungeon){
            signLabel = new MyLabel("resources/object/sign.png").setLabel(47,425,50,50);
            drawpane.add(signLabel);
            signLabel.addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent e) {
                    new signText();
                }
                public void mousePressed(MouseEvent e) {}
                public void mouseReleased(MouseEvent e) {}
                public void mouseEntered(MouseEvent e) {}
                public void mouseExited(MouseEvent e) {}
            });
	    }
        drawpane.setIcon(mapbg);
        drawpane.add(playerLabel);
        drawpane.add(boat);
        addKeyListener(this);
	    setPlayerThread();
	    contentpane.add(drawpane, BorderLayout.CENTER);
	    validate();
	}
	public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_RIGHT) { playerRight = true;}
        if (e.getKeyCode()==KeyEvent.VK_UP) { playerUp = true;}
        if (e.getKeyCode()==KeyEvent.VK_DOWN) { playerDown = true;}
        if (e.getKeyCode()==KeyEvent.VK_LEFT) { playerLeft = true;}
        if (e.getKeyCode()==KeyEvent.VK_SHIFT){ playerrunning = true;}
        if (e.getKeyCode()== KeyEvent.VK_ESCAPE){
            escape.setVisible(true);
            playerrunning = false;
            playerRight = false;
            playerDown = false;
            playerLeft = false;
            playerUp = false;
        }
        if (e.getKeyCode()==KeyEvent.VK_SPACE){ actionPlayer();}
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_RIGHT) { playerRight = false;}
        if (e.getKeyCode()==KeyEvent.VK_UP) { playerUp = false;}
        if (e.getKeyCode()==KeyEvent.VK_DOWN) { playerDown = false;}
        if (e.getKeyCode()==KeyEvent.VK_LEFT) { playerLeft = false;}
        if (e.getKeyCode()==KeyEvent.VK_SHIFT){ playerrunning = false;}
    }
    public void actionPlayer(){
        if (playerLabel.getBounds().intersects(boat.getBounds())){
            boat.setVisible(!boat.isVisible());
            if (boat.isVisible()){
                playerLabel.setIcon(playerDownmovementImg);
                playerCurX = 170;
                playerCurY = 550;
            }else {
                playerCurX = 222;
                playerCurY = 570;
            }
        }
	    if (playerLabel.getBounds().intersects(objectdoor.getBounds())){
            new Ban(name,skin,dungeon,level);
            playerUp = false;
            heroThemeSound.stop();
            playerAlive=false;
            this.dispose();
        }
        if (playerLabel.getBounds().intersects(objectcave.getBounds())){
            if (level==0){
            }else {
                new Dungeon(1, monsterAlive, level, 0,name,skin);
                playerAlive=false;
                heroThemeSound.stop();
                this.dispose();
            }
        }
    }
    public void setPlayerThread()
    {
        // end run
        Thread playerThread = new Thread(() -> {
            int moveMent = 0;
            int speed;
            while (playerAlive)
            {
                if (!this.isDisplayable()){
                    heroThemeSound.stop();
                    playerAlive = false;
                }
                if (playerrunning){
                    speed = 2;
                }else {
                    speed = 1;
                }
                if (boat.isVisible()){
                    if (playerLeft) {
                        if (moveMent % 100 == 75) {
                            playerLabel.setIcon(playerLeft1Img);
                        } else if (moveMent % 100 == 50) {
                            playerLabel.setIcon(playerLeft2Img);
                        } else if (moveMent % 100 == 25) {
                            playerLabel.setIcon(playerLeft1Img);
                        } else if (moveMent % 100 == 0) {
                            playerLabel.setIcon(playerLeft3Img);
                        }
                        if (playerCurX > 0) {
                            playerCurX = playerCurX - speed;
                        }
                        if (playerCurX <= 90 && playerCurY <= 280) {
                            playerCurX = playerCurX + speed;
                        }
                        if ((playerCurX <= 1100 && playerCurX >= 190) && playerCurY <= 240) {
                            playerCurX = playerCurX + speed;
                        }
                        if (playerCurY > 430 && playerCurX < 40) {
                            playerCurX = playerCurX + speed;
                        }
                    } else if (playerRight) {
                        if (moveMent % 100 == 75) {
                            playerLabel.setIcon(playerRight1Img);
                        } else if (moveMent % 100 == 50) {
                            playerLabel.setIcon(playerRight2Img);
                        } else if (moveMent % 100 == 25) {
                            playerLabel.setIcon(playerRight1Img);
                        } else if (moveMent % 100 == 0) {
                            playerLabel.setIcon(playerRight3Img);
                        }
                        if (playerCurX + playerWidth + (playerWidth / 2) < frameWidth) {
                            playerCurX = playerCurX + speed;
                        }
                        if ((playerCurX >= 135 && playerCurX <= 1100) && playerCurY <= 280) {
                            playerCurX = playerCurX - speed;
                        }
                        if (((playerCurY > 430 && playerCurY < 530) || (playerCurY > 555)) && playerCurX > 65) {
                            playerCurX = playerCurX - speed;
                        }
                        if ((playerCurY > 530 && playerCurY < 560) && playerCurX > 175) {
                            playerCurX = playerCurX - speed;
                        }
                    } else if (playerUp) {
                        if (moveMent % 100 == 75) {
                            playerLabel.setIcon(playerUp1Img);
                        } else if (moveMent % 100 == 50) {
                            playerLabel.setIcon(playerUp2Img);
                        } else if (moveMent % 100 == 25) {
                            playerLabel.setIcon(playerUp1Img);
                        } else if (moveMent % 100 == 0) {
                            playerLabel.setIcon(playerUp3Img);
                        }
                        if (playerCurY > 0) {
                            playerCurY = playerCurY - speed;
                        }
                        if (playerCurY < 183 && playerCurX < 135) {
                            playerCurY = playerCurY + speed;
                        }
                        if ((playerCurX <= 80 || (playerCurX >= 135 && playerCurX <= 1100)) && playerCurY <= 280) {
                            playerCurY = playerCurY + speed;
                        }
                        if (playerCurY > 430 && (playerCurX < 40 || playerCurX > 75)) {
                            playerCurY = playerCurY + speed;
                        }
                    } else if (playerDown) {
                        if (moveMent % 100 == 75) {
                            playerLabel.setIcon(playerDown1Img);
                        } else if (moveMent % 100 == 50) {
                            playerLabel.setIcon(playerDown2Img);
                        } else if (moveMent % 100 == 25) {
                            playerLabel.setIcon(playerDown1Img);
                        } else if (moveMent % 100 == 0) {
                            playerLabel.setIcon(playerDown3Img);
                        }
                        if (dungeon) {
                            if (playerCurY < 417) {
                                playerCurY = playerCurY + speed;
                            }
                        } else {
                            if (playerCurY < 655) {
                                playerCurY = playerCurY + speed;
                            }
                            if (playerCurY > 430 && (playerCurX < 40 || playerCurX > 65)) {
                                playerCurY = playerCurY - speed;
                            }
                        }
                    } else {
                        if (moveMent % 200 == 100) {
                            playerLabel.setIcon(playerDownmovementImg);
                        } else if (moveMent % 200 == 0) {
                            playerLabel.setIcon(playerDown1Img);
                        }
                    }
                }
                else {

                    if (playerLeft) {
                        playerLabel.setIcon(new MyImageIcon("resources/player/"+skin+"/Boat/B4.png").resize(playerWidth,playerHeight));
                        if (playerCurX > 200) {
                            playerCurX = playerCurX - speed;
                        }

                    } else if (playerRight) {
                        playerLabel.setIcon(new MyImageIcon("resources/player/"+skin+"/Boat/B3.png").resize(playerWidth,playerHeight));
                        if (playerCurX  < frameWidth) {
                            playerCurX = playerCurX + speed;
                        }else {
                            this.dispose();
                            new River(name, skin);
                            heroThemeSound.stop();
                            playerAlive = false;
                        }
                    } else if (playerUp) {
                        playerLabel.setIcon(new MyImageIcon("resources/player/"+skin+"/Boat/B1.png").resize(playerWidth,playerHeight));
                        if (playerCurY > 500) {
                            playerCurY = playerCurY - speed;
                        }
                    } else if (playerDown) {
                        playerLabel.setIcon(new MyImageIcon("resources/player/"+skin+"/Boat/B2.png").resize(playerWidth,playerHeight));
                        if (playerCurY < frameHeight-playerHeight) {
                            playerCurY = playerCurY + speed;
                        }
                    } else {
                        playerLabel.setIcon(new MyImageIcon("resources/player/"+skin+"/Boat/B3.png").resize(playerWidth,playerHeight));
                    }
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
