package editor;

import javax.swing.*;

public class MyLabel extends JLabel {
    private MyImageIcon img;
    private String fname;
    public MyLabel(String filename){
        fname=filename;
    }
    public MyLabel(MyImageIcon image){super(image);}

    public MyLabel setLabel(int x,int y,int _w,int _h){
        img = new MyImageIcon(fname).resize(_w,_h);
        MyLabel Label = new MyLabel(img);
        Label.setBounds(x,y,_w,_h);
        return Label;
    }
}
