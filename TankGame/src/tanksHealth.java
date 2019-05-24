import java.awt.*;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.Observer;
import java.util.Observable;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class tanksHealth {
    private boolean show;
    public int lifeCount;
    private int damage;
    private int x,y;
    private boolean hit;
    private Color myColor;


    tanksHealth(int x, int y, int life,int damage){
        this.lifeCount = life;
        this.damage = damage;
        this.x = x;
        this.y = y;
        this.hit = false;
        this.show = true;


    }

    public int getLifeCount()
    {
        return this.lifeCount;
    }
    public int getDamage()
    {
        return this.damage;
    }
    public int getX()
    {
        return this.x;
    }
    public int getY()
    {
        return this.y;
    }


    public boolean getHit()
    {
        return this.hit;
    }
    public boolean getShow()
    {
        return this.show;
    }

    public void setShow(boolean s)
    {
        this.show = s;
    }



    public void Hit(int h)
    {
        this.lifeCount -= h;
        if (this.lifeCount < 0) this.lifeCount = 0;

    }
    public boolean isAlive()
    {
        if(lifeCount <= 0)
        {
            return false;
        }

        return  true;
    }

    public void update(int x, int y) {
        if (hit = true) {

            lifeCount = lifeCount - damage;
        }
    }

    public void draw(Graphics g) {
        if(show) {
            //draws the bullets being shot
            g.setColor(Color.pink);
            //size of the bullet
            g.fillRect(x,y,100,30);
            g.setColor(Color.BLUE);
            g.drawString(Integer.toString(lifeCount),x+100/2, y+30/2) ;
        }
    }


}
