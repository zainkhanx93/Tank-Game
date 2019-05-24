import java.awt.*;
import java.lang.reflect.Array;
import java.util.LinkedList;

import java.util.LinkedList.*;

import java.util.Objects;
import java.util.Queue;

public class gameWalls extends gameObject {
    private int direction;
    boolean is_breakable;
    private Color myColor;

    gameWalls(int start_x, int start_y, int length, int direction, boolean is_breakable){
        //  (start_x pos, start_y pos, length of Wall, Direction(ver or hor), is it breakable, is it broken)
        super(start_x,start_y,10,length);
        if(direction != 1)
        {
            super.set_length(length);
            super.set_width(10);
        }
        this.direction=direction;
        this.is_breakable=is_breakable;
        if(is_breakable==true)
        {
            myColor = Color.DARK_GRAY;
        }
        else
        {
            myColor=Color.WHITE;
        }

    }

    public boolean is_breakable(){
        return is_breakable;
    }

    @Override
    public int whoAmI(){
        return WALL;
    }

    @Override
    void drawImage(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        if(!super.is_visible()){
            return;
        }
        g2d.setColor(myColor);
        //super.drawImage(g2d);
        g2d.fillRect(x, y,width,length);

    }


    }
