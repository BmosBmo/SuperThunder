package scene;

import javax.swing.*;

public class Scene extends JFrame {
    private int level;
    private String skin,playername;
    public Scene(){}
    public void setLevel(int _level){level = _level;}
    public int getLevel(){return level;}
    public void setPlayerName(String _playername){playername = _playername;}
    public String getName(){return playername;}
    public void setSkin(String _skin){skin = _skin;}
    public String getSkin(){return skin;}
    public void changScene(Scene _change){
    }
}
