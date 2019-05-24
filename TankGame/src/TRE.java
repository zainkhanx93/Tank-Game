/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package tankrotationexample;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import  java.io.File;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.LinkedList.*;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import static javax.imageio.ImageIO.read;

public class TRE extends JPanel {

    //size of the screen
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 1080;


    private BufferedImage world;
    private Graphics2D buffer;
    private JFrame jf;
    private Tank t1 ,t2;
    private Bullets blt1;
    public LinkedList<gameObject> delete_list;
    public LinkedList<gameObject> Walls;

    public tankPowerups powerup1, powerup2, powerup2_1, powerup1_1;

    public tanksHealth p1, p2;

    //BufferedImage player1, player2;


    public void addBullet(Bullets bullet){
        this.Walls.add(bullet);
        System.out.println("Added a bullet");
    }




    public static void main(String[] args) {
        Thread x;
        TRE trex = new TRE();
        trex.init();
        try {

            while (true) {
                if(trex.t1 != null)
                trex.t1.update();
                if(trex.t2 != null)
                trex.t2.update();
                //access via new for-loop
                for(Object object : trex.Walls) {
                    gameObject element = (gameObject) object;
                    element.update(TRE.SCREEN_WIDTH,TRE.SCREEN_HEIGHT);
                }


               // trex.blt1.update(TRE.SCREEN_WIDTH,TRE.SCREEN_HEIGHT);
                trex.repaint();

                Thread.sleep(1000 / 70);
            }
        } catch (InterruptedException ignored) {

        }

    }


    private void init() {
        this.jf = new JFrame("Tank Rotation");
        //building the walls
       this.Walls = new LinkedList<gameObject>();
        this.delete_list = new LinkedList<gameObject>();

        //  (start_x pos, start_y pos, length of Wall, Direction(ver or hor), is it breakable)
        Walls.add(new gameWalls(30,30,900,0,false));
        Walls.add(new gameWalls(1220,30,900,0,false));
        Walls.add(new gameWalls(30,30,1200,1,false));
        Walls.add(new gameWalls(30,925,1200,1,false));

        //boarder around tank 1
        Walls.add(new gameWalls(30,200,120,1,false));
        //breakable
        Walls.add(new gameWalls(150,200,130,1,true));
        Walls.add(new gameWalls(280,30,180,0,false));
        Walls.add(new gameWalls(280,210,200,0,false));

        // meeting point(mid map)
        Walls.add(new gameWalls(600,30,350,0,false));
        //breakable
        Walls.add(new gameWalls(600,380,200,0,true));
        Walls.add(new gameWalls(600,580,350,0,false));


        //boarder around tank 2
        Walls.add(new gameWalls(1000,700,220,0,false));
        //breakable
        Walls.add(new gameWalls(1010,700,140,1,true));
        Walls.add(new gameWalls(1000,500,200,0,false));
        Walls.add(new gameWalls(1150,700,80,1,false));

        // extra walls for left side
        Walls.add(new gameWalls(30,700,190,1,false));
        Walls.add(new gameWalls(220,700,100,0,false));
        //breakable
        Walls.add(new gameWalls(220,800,125,0,true));
        Walls.add(new gameWalls(410,180,200,1,false));
        //breakable
        Walls.add(new gameWalls(290,180,120,1,true));

        // extra walls for the right side
        Walls.add(new gameWalls(1000,180,220,1,false));
        //breakable
        Walls.add(new gameWalls(1000,40,140,0,true));
        Walls.add(new gameWalls(600,785,240,1,false));
        //breakable
        Walls.add(new gameWalls(840,785,155,1,true));
       // Walls.add(new gameWalls(900,7855,100,1,false));



        // buffers the world
        this.world = new BufferedImage(TRE.SCREEN_WIDTH, TRE.SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);



        BufferedImage t1img = null, t2img = null;
        try {
            BufferedImage tmp;
            System.out.println(System.getProperty("user.dir"));

            /*
             * note class loaders read files from the out folder (build folder in netbeans) and not the
             * current working directory.
             */


           t1img = read(new File("tank1.png"));



        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        p1 = new tanksHealth(30,940,10,0);
        p2 = new tanksHealth(1130,940,10,0);
        //start postiton for tanks
        t1 = new Tank(70, 70, 0, 0, 0, t1img, this,p1);
        t2 = new Tank(1120, 840, 0, 0, 0, t1img, this,p2);

        Walls.add(t1);
        Walls.add(t2);


        //controls for both players
        tankControls tc2 = new tankControls(t2, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER,this);
        tankControls tc1 = new tankControls(t1, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_B,this);

        powerup1 = new tankPowerups(1,1150,100,this);
        powerup2 = new tankPowerups(2, 670,860,this);
        powerup1_1 = new tankPowerups(1,90,860,this);
        powerup2_1 = new tankPowerups(2, 520,100,this);
        Walls.add(powerup1);
        Walls.add(powerup2);
        Walls.add(powerup1_1);
        Walls.add(powerup2_1);







        this.jf.setLayout(new BorderLayout());
        this.jf.add(this);


        this.jf.addKeyListener(tc1);
        this.jf.addKeyListener(tc2);


        this.jf.setSize(TRE.SCREEN_WIDTH, TRE.SCREEN_HEIGHT+30);
        //this.jf.setBackground(Color.BLACK);
        this.jf.getContentPane().setBackground( Color.black );
        this.jf.setResizable(false);
        jf.setLocationRelativeTo(null);

        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jf.setVisible(true);



    }


    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        this.world = new BufferedImage(TRE.SCREEN_WIDTH, TRE.SCREEN_HEIGHT+30, BufferedImage.TYPE_INT_RGB);
        buffer = world.createGraphics();
        super.paintComponent(g2);
        //super.paintComponent(buffer);

        //g2.drawImage(world,0,0,null);
        //displays the tank on the game

        if(t1.checkAlive())
            if(t1 != null)
            this.t1.drawImage(buffer);
        else {
            t1.set_visible(false);
            //delete_list.add(t1);
            //t1 = null;
        }
        if(t2 != null)
        if((t2.checkAlive()))
            this.t2.drawImage(buffer);
        else {
            t2.set_visible(false);
            //delete_list.add(t2);
            //t2 = null;
        }





       for(Object object : delete_list) {
            gameObject element = (gameObject) object;
            if(!element.is_visible()) {
                System.out.println("Deleted");
                Walls.remove(object);
            }
        }
       delete_list.clear();

        for(Object object : Walls) {
            gameObject element = (gameObject) object;
            element.drawImage(buffer);
        }

        BufferedImage left= world.getSubimage(t1.getScreen_x(),t1.getScreen_y(),TRE.SCREEN_WIDTH/2,TRE.SCREEN_HEIGHT  );
        BufferedImage right= world.getSubimage(t2.getScreen_x(),t2.getScreen_y(),TRE.SCREEN_WIDTH/2,TRE.SCREEN_HEIGHT );
        //BufferedImage LH = world.getSubimage(p1.getX(),p1.getY(),100,30);
        //BufferedImage RH = world.getSubimage(p2.getX(),p2.getY(),100,30);

        g2.drawImage(left,0,0,null);
        g2.drawImage(right,TRE.SCREEN_WIDTH/2,0,null);

        BufferedImage mm = world.getSubimage(0,0,TRE.SCREEN_WIDTH,TRE.SCREEN_HEIGHT);
       // g2.drawImage(LH,p1.getX(),p1.getY()-200,null);
       // g2.drawImage(RH,p2.getX()-200,p2.getY()-200,null);
        g2.scale(.2,.2);
        g2.drawImage(mm,TRE.SCREEN_WIDTH+1100,TRE.SCREEN_HEIGHT+3200,null);
        g2.scale(5,5);
        this.p1.draw(g2);
        this.p2.draw(g2);


    }





    }





