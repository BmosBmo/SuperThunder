package frame;

import editor.MyImageIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class OptionFrame extends JFrame implements ActionListener {
    private int level = 1;
    private String skin;
    private String name;
    private ButtonGroup bg;
    private JRadioButton levelManage1,levelManage2,levelManage3,levelManage4,levelManage5;
    private JButton confirm;
    private JTextField Name;
    private JLabel setNameText,setSkinText;
    private JComboBox Skin;
    private MyImageIcon backGround;
    private int frameWidth = 1200, frameHeight = 800;
    String[] dataSkin = {"Default","One","Two","Three","Four"};

    public OptionFrame(){
        skin = "Default";
        name = "Newbie";
        backGround = new MyImageIcon("resources/setting/setting.jpg").resize(frameWidth, frameHeight);
        setContentPane(new JLabel(backGround));
        Skin = new JComboBox(dataSkin);
        Skin.setBounds(600,100,150,30);
        Skin.setSelectedIndex(0);
        Name = new JTextField();
        Name.setBounds(600,200,150,30);
        setSkinText = new JLabel("Select Character:");
        setSkinText.setForeground(Color.white);
        setSkinText.setBounds(470,100,120,30);
        setNameText = new JLabel("Your Name:");
        setNameText.setForeground(Color.white);
        setNameText.setBounds(500,200,80,30);
        confirm = new JButton("Confirm");
        confirm.setBounds(560,600,80,30);
        levelManage1 = new JRadioButton("");
        levelManage1.setActionCommand("1");
        levelManage1.addActionListener(this);
        levelManage2 = new JRadioButton("");
        levelManage2.setActionCommand("2");
        levelManage2.addActionListener(this);
        levelManage3 = new JRadioButton("");
        levelManage3.setActionCommand("3");
        levelManage3.addActionListener(this);
        levelManage4 = new JRadioButton("");
        levelManage4.setActionCommand("4");
        levelManage4.addActionListener(this);
        levelManage5 = new JRadioButton("");
        levelManage5.setActionCommand("5");
        levelManage5.addActionListener(this);
        levelManage1.setBounds(380,280,50,50);
        levelManage2.setBounds(380,340,50,50);
        levelManage3.setBounds(380,400,50,50);
        levelManage4.setBounds(380,460,50,50);
        levelManage5.setBounds(380,520,50,50);
        getContentPane().setLayout(null);
        bg = new ButtonGroup();
        bg.add(levelManage1);
        bg.add(levelManage2);
        bg.add(levelManage3);
        bg.add(levelManage4);
        bg.add(levelManage5);
        getContentPane().add(Name);
        getContentPane().add(levelManage1);
        getContentPane().add(levelManage2);
        getContentPane().add(levelManage3);
        getContentPane().add(levelManage4);
        getContentPane().add(levelManage5);
        getContentPane().add(confirm);
        getContentPane().add(setNameText);
        getContentPane().add(setSkinText);
        getContentPane().add(Skin);
        setSize(300,300);
        setLayout(null);
        setVisible(true);
        confirm.addActionListener(e->{
            name = Name.getText();
            skin = (String)Skin.getSelectedItem();
            this.dispose();
            this.setVisible(false);
        });
    }
    public String getName(){ return name; }
    public String getSkin(){ return skin;}
    public int getLevel(){ return level; }
    @Override
    public void actionPerformed(ActionEvent e) {
        level = Integer.parseInt(bg.getSelection().getActionCommand());
        System.out.println(level);
    }
}
