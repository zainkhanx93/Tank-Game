
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;


public class Bullets extends gameObject {
    int damage;
    int angle;
    BufferedImage img;
    final int SPEED=10;
    public TRE t;
    Tank t1;

    public Bullets( int x, int y, int damage, int angle, TRE t, Tank tc){
        super(x,y,10,5);
        this.angle = angle;
        this.damage = damage;
        this.t = t;
        this.t1 = tc;

    }
   @Override
    public int whoAmI(){
        return BULLET;

    }

    public int getDamge(){
        return this.damage;
    }

    public boolean getShow(){
        return super.is_visible();
    }

    public void setShow(boolean s){
        super.set_visible(s);
    }

    boolean  checkCollision(int x1, int y1)
    {
        int x_,y_,len_,width_;

        for(Object object : t.Walls) {
            gameObject element = (gameObject) object;
            if(this == element) continue;
            if(!element.is_visible()) continue;
            x_ = element.getx();
            y_ = element.gety();
            len_ = element.getLength();
            width_ = element.getwidth();
            //System.out.println(x_ + " " + y_+" "+len_+" "+width_+ " "  + x +" " + y);
            if(element.whoAmI()==BULLET) continue;;
            if(((x > x_ && x< (x_+width_)) && (y > y_ && y < (y_+len_)))  ||
                    (( (x + 5) > x_ && (x+5)< (x_+width_)) && (y > y_ && y < (y_+len_)))  ||
                    (( x > x_ && x< (x_+width_)) && ((y+5) > y_ && (y+5) < (y_+len_)))  ||
                    (( (x+5) > x_ && (x+5)< (x_+width_)) && ((y+5) > y_ && (y+5) < (y_+len_))) )

            {
              //  System.out.println("Bullet Collision");

                if(((gameObject) object).WALL == element.whoAmI()){
                    super.set_visible(false);
                    gameWalls w = (gameWalls) object;
                    if(w.is_breakable()) {
                        w.set_visible(false);
                        t.delete_list.add(element);
                    }
                }
                if(((gameObject) object).TANK == element.whoAmI()){
                    Tank w = (Tank) object;
                    if(w != t1) {
                        super.set_visible(false);
                        t.delete_list.add(this);
                        w.updateHealth(damage);
                    }
                    else {
                     //   System.out.println("This is my tank");
                    }
                }

                return true;
            }
        }



        return false;

    }

    @Override
    public void update(int w, int h){
        int vx, vy;
        // he direction the bullets will move in
        if(y < h-40 && y > 0 && x > 0 && x < w-40 && super.is_visible()){
            x = x + (int) (SPEED*Math.cos(Math.toRadians(angle)));
            y = y + (int) (SPEED*Math.sin(Math.toRadians(angle)));

        }
        else{
            super.set_visible(false);
            t.delete_list.add(this);
        }
        checkCollision(x,y);
    }

    @Override
    public void drawImage(Graphics g) {
        if(super.is_visible()) {
            //draws the bullets being shot
            //g.drawImage(img, x, y, obs);
            //System.out.println("Drawing Bullet at " + x + " " + y);
            g.setColor(Color.green);
            g.fillRect(x, y, 5, 10);
        }
    }
}