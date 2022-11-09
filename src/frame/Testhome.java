package frame;

import editor.MyImageIcon;
import editor.MySoundEffect;
import scene.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Testhome extends Scene {

    private JPanel contentpane;
    private JLabel drawpane;
    private int level;
    private String skin;
    private JButton start, setting, exit;
    private MyImageIcon indoorImg;
    private MySoundEffect heroThemeSound;
    private OptionFrame _optionFrame;
    private String name;

    private int frameWidth = 1200, frameHeight = 800;
    Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();

    public Testhome() {
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
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        AddComponents();
    }

    //////////////////////////////////////////////////////////////////////////
    public void AddComponents() {
        _optionFrame = new OptionFrame();
        _optionFrame.setVisible(false);
        _optionFrame.setBounds(ss.width / 2 - frameWidth / 2, ss.height / 2 - frameHeight / 2, frameWidth, frameHeight);
        indoorImg = new MyImageIcon("resources/indoor.jpg").resize(frameWidth, frameHeight);
        drawpane = new JLabel();
        drawpane.setIcon(indoorImg);
        drawpane.setLayout(null);
        start = new JButton("Start");
        setting = new JButton("Setting");
        exit = new JButton("Exit");
        start.setBounds(frameWidth / 2-125, frameHeight - 450, 250, 80);
        setting.setBounds(frameWidth / 2-125, frameHeight - 350, 250, 80);
        exit.setBounds(frameWidth / 2-125, frameHeight - 250, 250, 80);
        start.addActionListener(e->{
            setLevel(_optionFrame.getLevel());
            heroThemeSound.stop();
            setPlayerName(_optionFrame.getName());
            setSkin(_optionFrame.getSkin());
            this.dispose();
        });
        setting.addActionListener(e->{
            _optionFrame.setVisible(true);
        });
        exit.addActionListener(e->{
            System.exit(1);
        });
        drawpane.add(start);
        drawpane.add(setting);
        drawpane.add(exit);
        heroThemeSound = new MySoundEffect("resources/herotheme.wav");
        heroThemeSound.playLoop();
        contentpane.add(drawpane, BorderLayout.CENTER);
        validate();
    }
}
