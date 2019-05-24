
import java.awt.*;

public class gameObject {
    public int x,y,length,width;
    boolean visible;
    final int NONE=0;
    final int TANK=1;
    final int BULLET=2;
    final int POWERUP=3;
    final int WALL=4;


    gameObject(int start_x, int start_y, int length_, int width_){
        this.x = start_x;
        this.y = start_y;
        this.length = length_;
        this.width = width_;
        this.visible = true;
    }

    public int whoAmI(){
        return NONE;

    }

    boolean is_visible(){
        return visible;
    }

    public int getx(){
        return this.x;
    }

    public int gety(){
        return this.y;
    }

    public int getLength(){
        return this.length;
    }
    public int getwidth(){
        return this.width;
    }

    void set_visible(boolean s){
        visible = s;
    }
    void set_length(int l){
        length = l;
    }
    void set_width(int l){
        width = l;
    }

    void drawImage(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        if(!is_visible()){
            return;
        }
        g2d.fillRect(this.x, this.y,this.width,this.length);

    }

    public void update(int w, int h){
    }
}