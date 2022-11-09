package frame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import editor.MyImageIcon;
import editor.MySoundEffect;

public class Ban extends JFrame implements KeyListener{
    private JPanel contentpane;
    private JLabel playerLabel, drawpane;
    private MyImageIcon mapbg,playerUp1Img,playerUp2Img,playerUp3Img, playerDown1Img,playerDown2Img,playerDown3Img, playerLeft1Img,playerLeft2Img,playerLeft3Img, playerRight1Img,playerRight2Img,playerRight3Img,playerDownmovementImg;
    private int playerWidth = 60, playerHeight = 60;
    private int frameWidth = 1200, frameHeight = 800;
    private int playerCurX = 137, playerCurY = 690;
    private int level;
    private boolean playerAlive = true,playerrunning = false, playerUp = false,playerDown= false,playerLeft= false,playerRight= false;
    private String name,skin;
    private boolean dungeon;
    private MySoundEffect heroThemeSound;
    private Escape escape;

    Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
    public Ban(String _name,String _skin,boolean _dungeon,int _level){
        name = _name;
        level = _level;
        dungeon = _dungeon;
        skin = _skin;
        escape = new Escape(this);
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
        mapbg = new MyImageIcon("resources/map/ban.png").resize(frameWidth, frameHeight);
        playerLabel = new JLabel(playerDown1Img);
        playerLabel.setBounds(playerCurX,playerCurY,playerWidth,playerHeight);
        playerLabel.setVisible(true);
        heroThemeSound = new MySoundEffect("resources/Ban.wav");
        heroThemeSound.playLoop();
        drawpane = new JLabel();
        drawpane.setIcon(mapbg);
        drawpane.add(playerLabel);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
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
    public void actionPlayer(){}
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
                    speed = 1;
                }else {
                    speed = 1;
                }
                if (playerLeft)
                {
                    if (moveMent%100==75){
                        playerLabel.setIcon(playerLeft1Img);
                    }else if(moveMent%100==50){
                        playerLabel.setIcon(playerLeft2Img);
                    }else if (moveMent%100==25){
                        playerLabel.setIcon(playerLeft1Img);
                    }else if (moveMent%100==0){
                        playerLabel.setIcon(playerLeft3Img);
                    }
                    if (playerCurX>0) {
                        playerCurX = playerCurX - speed;
                    }
                    if (playerCurX<=380&&(playerCurY>=346&&playerCurY<=477)){
                        playerCurX = playerCurX + 1;
                    }
                    if ((playerCurX>=450&&playerCurX<=517)&&(playerCurY>=148&&playerCurY<=487)){
                        playerCurX = playerCurX + 1;
                    }
                    if (playerCurX<=205&&(playerCurY>=148&&playerCurY<=346)){
                        playerCurX = playerCurX + 1;
                    }
                    if (playerCurX<=90&&playerCurY>=0){
                        playerCurX = playerCurX + 1;
                    }
                    if ((playerCurX>=700&&playerCurX<=765)&&(playerCurY>=280&&playerCurY<=477)){
                        playerCurX = playerCurX + 1;
                    }
                    if ((playerCurX>=280&&playerCurX<=410)&&(playerCurY>=551&&playerCurY<=660)){
                        playerCurX = playerCurX + 1;
                    }
                }else
                if(playerRight)
                {
                    if (moveMent%100==75){
                        playerLabel.setIcon(playerRight1Img);
                    }else if(moveMent%100==50){
                        playerLabel.setIcon(playerRight2Img);
                    }else if (moveMent%100==25){
                        playerLabel.setIcon(playerRight1Img);
                    }else if (moveMent%100==0){
                        playerLabel.setIcon(playerRight3Img);
                    }
                    if (playerCurX+playerWidth+(playerWidth/2)<frameWidth) {
                        playerCurX = playerCurX + speed;
                    }
                    if ((playerCurX>=620&&playerCurX<=755)&&(playerCurY>=280&&playerCurY<=410)){
                        playerCurX = playerCurX - 1;
                    }
                    if ((playerCurX>=450&&playerCurX<=517)&&(playerCurY>=148&&playerCurY<=346)){
                        playerCurX = playerCurX - 1;
                    }
                    if ((playerCurX>=380&&playerCurX<=400)&&(playerCurY>=346&&playerCurY<=477)){
                        playerCurX = playerCurX - 1;
                    }
                    if ((playerCurX>=656&&playerCurX<=755)&&(playerCurY>=410&&playerCurY<=708)){
                        playerCurX = playerCurX - 1;
                    }
                    if (playerCurX>=985&&(playerCurY>=280&&playerCurY<=708)){
                        playerCurX = playerCurX - 1;
                    }
                    if ((playerCurX>=280&&playerCurX<=410)&&(playerCurY>=551&&playerCurY<=660)){
                        playerCurX = playerCurX - 1;
                    }
                    if (playerCurX==655&&(playerCurY>=628&&playerCurY<=678)){
                        playerCurX = 805;
                        playerCurY = 430;
                    }

                }else
                if(playerUp)
                {
                    if (moveMent%100==75){
                        playerLabel.setIcon(playerUp1Img);
                    }else if(moveMent%100==50){
                        playerLabel.setIcon(playerUp2Img);
                    }else if (moveMent%100==25){
                        playerLabel.setIcon(playerUp1Img);
                    }else if (moveMent%100==0){
                        playerLabel.setIcon(playerUp3Img);
                    }
                    if (playerCurY>0) {
                        playerCurY = playerCurY - speed;
                    }
                    if (playerCurY<183&&playerCurX<135){
                        playerCurY = playerCurY + 1;
                    }
                    if ((playerCurX>=517&&playerCurX<=620)&&playerCurY<=280) {
                        playerCurY = playerCurY + 1;
                    }
                    if ((playerCurX>=755&&playerCurX<=985)&&playerCurY<=280){
                        playerCurY = playerCurY + 1;
                    }
                    if (playerCurX>=0&&playerCurY<=148){
                        playerCurY = playerCurY + 1;
                    }
                    if(((playerCurX>=0&&playerCurX<=380)||(playerCurX>=400&&playerCurX<=517))&&(playerCurY>=340&&playerCurY<=487)) {
                        playerCurY = playerCurY + 1;
                    }
                    if((playerCurX>=625&&playerCurX<=755)&&playerCurY<=410) {
                        playerCurY = playerCurY + 1;
                    }
                    if ((playerCurX>=280&&playerCurX<=410)&&(playerCurY>=551&&playerCurY<=660)){
                        playerCurY = playerCurY + 1;
                    }
                }else
                if(playerDown)
                {
                    if (moveMent%100==75){
                        playerLabel.setIcon(playerDown1Img);
                    }else if(moveMent%100==50){
                        playerLabel.setIcon(playerDown2Img);
                    }else if (moveMent%100==25){
                        playerLabel.setIcon(playerDown1Img);
                    }else if (moveMent%100==0){
                        playerLabel.setIcon(playerDown3Img);
                    }
                    if (playerCurY<=706) {
                        playerCurY = playerCurY + speed;
                    }
                    if ((playerCurX>=110&&playerCurX<=150)&&(playerCurY>=700&&playerCurY<=730)){
                        if (dungeon) {
                            heroThemeSound.stop();
                            new FirstFrame(level, name, skin,dungeon,0);
                        }else {
                            heroThemeSound.stop();
                            new FirstFrame(level, name, skin,dungeon,0);
                        }
                        playerDown = false;
                        this.dispose();
                    }
                    if(playerCurX>=755&&playerCurY>=430) {
                        playerCurY = playerCurY - 1;
                    }
                    if ((playerCurX>=450&&playerCurX<=517)&&playerCurY==346){
                        playerCurY = playerCurY - 1;
                    }
                    if (((playerCurX>=0&&playerCurX<380)||(playerCurX>400&&playerCurX<=517))&&(playerCurY>=340&&playerCurY<=487)){
                        playerCurY = playerCurY - 1;
                    }
                    if ((playerCurX>=280&&playerCurX<=410)&&(playerCurY>=550&&playerCurY<=551)){
                        playerCurY = playerCurY - 1;
                    }
                    if ((playerCurX>=793&&playerCurX<=813)&&(playerCurY>=428&&playerCurY<=430)){
                        playerCurX = 655;
                        playerCurY = 642;
                    }
                }else
                {
                    if (moveMent%200==100){
                        playerLabel.setIcon(playerDownmovementImg);
                    }else if(moveMent%200==0){
                        playerLabel.setIcon(playerDown1Img);
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
