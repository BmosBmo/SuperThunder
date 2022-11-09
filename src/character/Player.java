package character;

import editor.MyImageIcon;
import frame.EndingFrame;
import frame.FirstFrame;
import frame.dungeon.Dungeon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends JLabel{
    private int playerWidth = 33, playerHeight = 47;
    private MyImageIcon playerAttackRight3Img,playerAttackRight2Img,playerAttackRight1Img,playerAttackLeft3Img,playerAttackLeft2Img,playerAttackLeft1Img,playerAttackUp3Img,playerAttackUp2Img,playerAttackUp1Img,playerAttackDown3Img,playerAttackDown2Img,playerAttackDown1Img,playerUp1Img, playerUp2Img, playerUp3Img, playerDown1Img, playerDown2Img, playerDown3Img, playerLeft1Img, playerLeft2Img, playerLeft3Img, playerRight1Img, playerRight2Img, playerRight3Img, playerDownmovementImg;
    private boolean playerAlive = true,attack = false, playerrunning = false, playerUp = false, playerDown = false, playerLeft = false, playerRight = false;
    private int cdHit = 0,cd = 0,level,position;
    private int playerCurX, playerCurY;
    private int frameWidth,frameHeight;
    private JFrame frame;
    private JLabel playerLabel;
    private JLabel drawpane;
    private String playername;
    private String skin;
    private boolean run = false;
    public Player(String _skin,JLabel _drawpane){
        drawpane = _drawpane;
        skin = _skin;
        loadImage();
        playerLabel = new JLabel(playerDown1Img);
    }
    public void loadImage(){
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
        playerAttackDown1Img = new MyImageIcon("resources/player/"+skin+"/Attack/Ds1.png").resize(playerWidth, playerHeight);
        playerAttackDown2Img = new MyImageIcon("resources/player/"+skin+"/Attack/Ds2.png").resize(playerWidth, playerHeight);
        playerAttackDown3Img = new MyImageIcon("resources/player/"+skin+"/Attack/Ds3.png").resize(playerWidth, playerHeight);
        playerAttackUp1Img = new MyImageIcon("resources/player/"+skin+"/Attack/Us1.png").resize(playerWidth, playerHeight);
        playerAttackUp2Img = new MyImageIcon("resources/player/"+skin+"/Attack/Us2.png").resize(playerWidth, playerHeight);
        playerAttackUp3Img = new MyImageIcon("resources/player/"+skin+"/Attack/Us3.png").resize(playerWidth, playerHeight);
        playerAttackLeft1Img = new MyImageIcon("resources/player/"+skin+"/Attack/Ls1.png").resize(playerWidth, playerHeight);
        playerAttackLeft2Img = new MyImageIcon("resources/player/"+skin+"/Attack/Ls2.png").resize(playerWidth, playerHeight);
        playerAttackLeft3Img = new MyImageIcon("resources/player/"+skin+"/Attack/Ls3.png").resize(playerWidth, playerHeight);
        playerAttackRight1Img = new MyImageIcon("resources/player/"+skin+"/Attack/Rs1.png").resize(playerWidth, playerHeight);
        playerAttackRight2Img = new MyImageIcon("resources/player/"+skin+"/Attack/Rs2.png").resize(playerWidth, playerHeight);
        playerAttackRight3Img = new MyImageIcon("resources/player/"+skin+"/Attack/Rs3.png").resize(playerWidth, playerHeight);
    }
    public void setPlayerThread() {
        run = true;
        playerLabel.setBounds(playerCurX,playerCurY,playerWidth,playerHeight);
        playerLabel.setVisible(true);
        drawpane.add(playerLabel);
        frame.validate();
        drawpane.repaint();
        Thread playerThread = new Thread(() -> {
            int moveMent = 0;
            int speed;
            int hp = 10/level;
            while (run) {
                if (!attack&&cd<=0)
                {
                    if (playerrunning) {
                        speed = 5;
                    } else {
                        speed = 1;
                    }
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
                        if (playerCurX < frameWidth - playerWidth) {
                            playerCurX = playerCurX + speed;
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
                        if (playerCurY < 710) {
                            playerCurY = playerCurY + speed;
                        }
                    } else {
                        if (moveMent % 200 == 100) {
                            playerLabel.setIcon(playerDownmovementImg);
                        } else if (moveMent % 200 == 0) {
                            playerLabel.setIcon(playerDown1Img);
                        }
                    }
                }else {
                    {
                        playerLabel.setBounds(playerCurX, playerCurY, playerWidth, playerHeight * 2);
                        if (playerUp) {
                            if (moveMent % 40 >= 30) {
                                playerLabel.setIcon(playerAttackUp1Img);
                            } else if (moveMent % 40 >= 20) {
                                playerLabel.setIcon(playerAttackUp2Img);
                            } else if (moveMent % 40 >= 10) {
                                playerLabel.setIcon(playerAttackUp3Img);
                            } else if (moveMent % 40 == 0) {
                                playerLabel.setIcon(playerUp1Img);
                            }
                        }
                        if (playerLeft) {
                            if (moveMent % 40 >= 30) {
                                playerLabel.setIcon(playerAttackLeft1Img);
                            } else if (moveMent % 40 >= 20) {
                                playerLabel.setIcon(playerAttackLeft2Img);
                            } else if (moveMent % 40 >= 10) {
                                playerLabel.setIcon(playerAttackLeft3Img);
                            } else if (moveMent % 40 == 0) {
                                playerLabel.setIcon(playerLeft1Img);
                            }
                        }
                        if (playerDown) {
                            if (moveMent % 40 >= 30) {
                                playerLabel.setIcon(playerAttackDown1Img);
                            } else if (moveMent % 40 >= 20) {
                                playerLabel.setIcon(playerAttackDown2Img);
                            } else if (moveMent % 40 >= 10) {
                                playerLabel.setIcon(playerAttackDown3Img);
                            } else if (moveMent % 40 == 0) {
                                playerLabel.setIcon(playerDown1Img);
                            }
                        }
                        if (playerRight) {
                            if (moveMent % 40 >= 30) {
                                playerLabel.setIcon(playerAttackRight1Img);
                            } else if (moveMent % 40 >= 20) {
                                playerLabel.setIcon(playerAttackRight2Img);
                            } else if (moveMent % 40 >= 10) {
                                playerLabel.setIcon(playerAttackRight3Img);
                            } else if (moveMent % 40 == 0) {
                                playerLabel.setIcon(playerRight1Img);
                            }
                        }
                    }
                }

                if (playerCurY>520){
                    playerCurY = 520;
                }
                if (playerCurY<170){
                    playerCurY = 170;
                }
                playerLabel.setLocation(playerCurX, playerCurY);
                drawpane.repaint();
                if (hp<=0){
                    playerAlive = false;
                    new EndingFrame(playername,skin,true,level);
                    changeScene();
                }
                System.out.println(playerCurX);
                System.out.println(playerCurY);
                try {
                    Thread.sleep(7);
                    cd-=1;
                    cdHit-=1;
                    if (cd<=0){
                        attack=false;
                    }
                    moveMent += 1;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } // end while
        }); // end thread creation
        playerThread.start();
    }
    public void setPlayerListener(JFrame _frame){
        frame = _frame;
        frameWidth = frame.getWidth();
        frameHeight = frame.getHeight();
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    playerRight = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    playerUp = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    playerDown = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    playerLeft = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    playerrunning = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    attack = true;
                    cd=30;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    playerRight = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    playerUp = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    playerDown = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    playerLeft = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    playerrunning = false;
                }
            }
        });
    }
    public void changeScene(){
        run = false;
    }
    public void playerDead(){
        playerAlive = false;
    }
    public void setSkin(String _skin){
        skin = _skin;
    }
    public void setPlayerName(String _playername){
        playername = _playername;
    }
    public void setPlayerPosition(int _playerCurX,int _playerCurY){
        playerCurX = _playerCurX;
        playerCurY = _playerCurY;
    }

    public void setLevel(int _level) {
        level = _level;
    }
}
