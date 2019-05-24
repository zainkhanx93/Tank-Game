
import com.sun.xml.internal.bind.v2.model.core.NonElement;

import java.awt.*;


public class tankPowerups extends gameObject {
    final int DAMAGE = 2;
    final int ANGLE1 = 10;
    final int ANGLE2 = 20;

    int type;
    private Color myColor;
    public TRE t;
    Tank t1;


    tankPowerups(int type, int x, int y, TRE t, Tank tc)
    {
        super(x,y,40,40);
        this.x = x;
        this.y = y;
        this.t=t;
        this.t1 = tc;
        this.type=type;
        if(type == 1)
            this.myColor = Color.RED;
        else
            this.myColor = Color.BLUE;
    }


    tankPowerups(int type, int x, int y, TRE t)
    {
        super(x,y,40,40);
        this.x = x;
        this.y = y;
        this.t=t;
        this.type=type;
        if(type == 1)
            this.myColor = Color.RED;
        else
            this.myColor = Color.BLUE;
    }



    public int getType()
    {
        System.out.println("New Type is" +type);
        return type;
    }
    public void setType(int type)
    {
        this.type = type;
    }

    public void firing(TRE t, int angle, int x, int y){
        System.out.println("Firing | " + angle);
        int damage = 1;
        // powerup 1 is double damage

        int bullet_x, bullet_y;
        bullet_x = x +20*(int)(Math.abs(Math.cos(Math.toRadians(angle)))+Math.abs(Math.sin(Math.toRadians(angle))));
        bullet_y = y +20*(int)(Math.abs(Math.cos(Math.toRadians(angle)))+Math.abs(Math.sin(Math.toRadians(angle))));
        //bullet_x = x;
        //bullet_y = y;
        if(1 == (type & 1))
        {
          damage = DAMAGE;
        }

        //power up2 is five bullets firing at multiple angles
        if ( 2 == (type & 2) )
        {
            t.addBullet(new Bullets(bullet_x,bullet_y,damage,angle-ANGLE1,t,t1));
            t.addBullet(new Bullets(bullet_x,bullet_y,damage,angle-ANGLE2,t,t1));
            t.addBullet(new Bullets(bullet_x,bullet_y,damage,angle+ANGLE2,t,t1));
            t.addBullet(new Bullets(bullet_x,bullet_y,damage,angle+ANGLE1,t,t1));
        }
        t.addBullet(new Bullets(bullet_x,bullet_y,damage,angle,t,t1));


    }
    @Override
    public int whoAmI()
    {
        return POWERUP;
    }

    void drawImage(Graphics g2)
    {
        Graphics2D g2d = (Graphics2D) g2;
        if(super.is_visible() == true)
        {
            g2d.setColor(myColor);
            g2.fillOval(x,y,20,20);
        }

    }
}
