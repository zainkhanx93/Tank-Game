

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


public class Tank extends gameObject{


    private int vx;
    private int vy;
    private int angle;
    private TRE t;
    private tanksHealth health;

    private final int R = 3;
    private final int ROTATIONSPEED = 3;



    private BufferedImage img;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private tankPowerups powers;




    Tank(int x, int y, int vx, int vy, int angle, BufferedImage img, TRE tre, tanksHealth th){
        super(x,y,70,70);
        this.vx = vx;
        this.vy = vy;
        this.img = img;
        this.angle = angle;
        this.t = tre;
        this.powers=new tankPowerups(0,0,0,t,this);
        this.health = th;

    }


    // for player 1
    void toggleUpPressed() {
        this.UpPressed = true;

    }

    void toggleDownPressed() {
        this.DownPressed = true;
    }

    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    void toggleEnterPressed() {

       // get the queue for bullets from TRE
       // Creates a new bullet object and add it to Q
        //Bullets blt = new Bullets(x,(int)(y+20*Math.cos(Math.toRadians(angle))),10,angle,t,this);

        powers.firing(t,angle,x,y);
    }


    void unToggleUpPressed() {
        this.UpPressed = false;
    }

    void unToggleDownPressed() {
        this.DownPressed = false;
    }

    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }







    public void update() {
        if (this.UpPressed ) {
            this.moveForwards();
        }
        if (this.DownPressed) {
            this.moveBackwards();
        }

        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed) {
            this.rotateRight();
        }


    }

    private void rotateLeft() {
        this.angle -= this.ROTATIONSPEED;
    }

    private void rotateRight() {
        this.angle += this.ROTATIONSPEED;
    }

    private void moveBackwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        if(checkWall(x+vx, y+vy)) {
            x -= vx;
            y -= vy;
        }
        else {
            x += vx;
            y += vy;
        }
        checkBorder();
    }

    private void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        if(checkWall(x+ vx, y+vy)) {
            x += vx;
            y += vy;
        }else {
            x -= vx;
            y -= vy;
        }
        checkBorder();
    }

     boolean  checkWall(int x1, int y1)
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
            System.out.println(x_ + " " + y_+" "+len_+" "+width_+ " "  + x +" " + y);


                if (((x1 > x_ && x1 < (x_ + width_)) && (y1 > y_ && y1 < (y_ + len_))) ||
                        (((x1 + 55) > x_ && (x1 + 55) < (x_ + width_)) && (y1 > y_ && y1 < (y_ + len_))) ||
                        ((x1 > x_ && x1 < (x_ + width_)) && ((y1 + 70) > y_ && (y1 + 55) < (y_ + len_))) ||
                        (((x1 + 55) > x_ && (x1 + 55) < (x_ + width_)) && ((y1 + 55) > y_ && (y1 + 55) < (y_ + len_)))) {
                   // System.out.println("Collision");
                    if (element.whoAmI() == element.BULLET) {
                        updateHealth(((Bullets) element).getDamge());
                    }
                    if (element.whoAmI() == element.POWERUP) {
                       // System.out.println("Powerup Got");
                        getPowerup((tankPowerups) element);
                        element.set_visible(false);
                    }
                    return false;

                }

            }


            return true;

    }

    public void updateHealth(int h){
        System.out.println("Tank Hit");
        this.health.Hit(h);
    }

    public boolean checkAlive()
    {

        return health.isAlive();
    }




    boolean checkBullet(int x, int y)
    {
        return true;
    }




    private void getPowerup(tankPowerups powers)
    {
            this.powers.setType(this.powers.getType() | powers.getType());
            powers.set_visible(false);
            t.delete_list.add(powers);
    }


    private  void checkBorder()
    {
        if (x < 35) {
            x = 35;
        }
        if (x >= TRE.SCREEN_WIDTH - 88) {
            x = TRE.SCREEN_WIDTH - 88;
        }
        if (y < 45) {
            y = 45;
        }
        if (y >= TRE.SCREEN_HEIGHT - 80) {
            y = TRE.SCREEN_HEIGHT - 80;
        }
    }

    public int getScreen_x(){
        if (x  < (TRE.SCREEN_WIDTH)/2)
        {
            return 0;
        }
        return x-(TRE.SCREEN_WIDTH)/2 +100 ;

    }
    public int getScreen_y(){

        return 0  ;

    }

    @Override
    public int whoAmI()
    {
        return TANK;
    }


    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }


    void drawImage(Graphics g) {
        if(!super.is_visible()) return;
        if(!health.isAlive()) return;
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, rotation, null);
    }



}
