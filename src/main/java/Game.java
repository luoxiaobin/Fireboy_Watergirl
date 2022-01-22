import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Game{ 
    JFrame gameFrame; 

    MyKeyListener keyListener;  
    //game objects 
    Jumper firegirl; 
    Jumper waterboy;
    String gameStatus;
    public boolean gameActive;
    
    java.util.List<Platform> platformList;
    java.util.List<GreenGoo> greenGooList;
    java.util.List<Door> doorList;
    java.util.List<MovingPlatform> movingPlatformList;

    Background background;

//------------------------------------------------------------------------------ 
    Game(JFrame gameFrame) {

        //gameFrame = new JFrame("Game 'Firegirl and waterboy'");

        this.gameFrame = gameFrame;
        keyListener = new MyKeyListener();
        this.gameStatus = "playing";
        this.gameActive = true;

        String bckgPic = "images/background.png";
        background = new Background(bckgPic);

        platformList = new ArrayList<Platform>();
        greenGooList = new ArrayList<GreenGoo>();
        doorList = new ArrayList<Door>();
        movingPlatformList = new ArrayList<MovingPlatform>();

        //gameObjs = new GameObject[MAX_ROW_LENGTH];

//        int jumperW = 20;
//        int jumperH = 32;
//        int jumperX = Const.WIDTH/2;
//        int jumperY = Const.GROUND - jumperH;

        int jumperX = 50;
        int jumperY = 468; // this is calculated as platform y axis (500) - height of jumper (32)
        firegirl = new Jumper(jumperX, jumperY, ".//images//watergirl_small.png");

        jumperX = 250;
        jumperY = 468; // this is calculated as platform y axis (500) - height of jumper (32)

        waterboy = new Jumper(jumperX, jumperY, ".//images//watergirl_small.png");

        SetupGameObjects();
    }

//------------------------------------------------------------------------------
//set up the game platform 
    public void setUpGamePlatform (){
        gameFrame.setSize(Const.WIDTH,Const.HEIGHT);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
    }

    public void SetupGameObjects() {
        try {
            java.util.List<java.util.List<String>> gameObjectRecords = new ArrayList<>();

            System.out.println(System.getProperty("user.dir"));
            Scanner scanner = new Scanner(new File("./PlatformLayout.cfg"));
            while (scanner.hasNextLine()) {
                String gameObjectRecordString = scanner.nextLine();

                java.util.List<String> gameObjectRecordValues = new ArrayList<>();
                Scanner rowScanner = new Scanner(gameObjectRecordString);
                rowScanner.useDelimiter(Const.COMMA_DELIMITER);
                while (rowScanner.hasNext()) {
                    gameObjectRecordValues.add(rowScanner.next());
                }
                gameObjectRecords.add(gameObjectRecordValues);
            }

            for (int row = 0; row < gameObjectRecords.size(); row++) {
                java.util.List<String> record = gameObjectRecords.get(row);

                String gameObjectType = record.get(0).toUpperCase();
                int posX = Integer.parseInt(record.get(1).trim());
                int posY = Integer.parseInt(record.get(2).trim());

                switch (gameObjectType){
                    case "P":
                        platformList.add(new Platform(posX, posY));
                        break;
                    case "G":
                        greenGooList.add(new GreenGoo(posX, posY));
                        break;
                    case "D":
                        String doorName = record.get(3).trim();
                        doorList.add(new Door(posX, posY, doorName));
                        break;
                    case "M":
                        int movingDistance = Integer.parseInt(record.get(3).trim());
                        movingPlatformList.add(new MovingPlatform(posX, posY, movingDistance));
                        break;
                }
            }
        }
        catch (Exception e) 
        { 
            System.out.println(e); 
        }
    }

//------------------------------------------------------------------------------   
//    main game loop 
    public void runGameLoop(){

        GamePanel gamePanel;
        gamePanel = new GamePanel();
        gamePanel.addKeyListener(keyListener);
        gameFrame.add(gamePanel);
        gameFrame.setVisible(true);

        //while (true) {
        while (gameStatus.equals("playing")) {
            gameFrame.repaint(); 
            try {Thread.sleep(Const.FRAME_PERIOD);} catch(Exception e){}

            for (MovingPlatform movingPlatform: movingPlatformList) {
                movingPlatform.move();
            }
            
            firegirl.accelerate();
            firegirl.moveX();
            firegirl.moveY(Const.GROUND);
            waterboy.accelerate();
            waterboy.moveX();
            waterboy.moveY(Const.GROUND);

            for (MovingPlatform movingPlatform: movingPlatformList) {
                //if the jumper is moving down and collides with a moving platform
                if (firegirl.getVy()>0 && firegirl.collides(movingPlatform)) {
                    firegirl.setY(movingPlatform.getY()-firegirl.getHeight());
                    firegirl.setOnMovingPlatform(movingPlatform);
                }
                //if the jumper is moving up and collides with the platform
                else if (firegirl.getVy()<0 && firegirl.collides(movingPlatform)) {
                    firegirl.setY(movingPlatform.getY()-firegirl.getHeight());
                    firegirl.setOnMovingPlatform(movingPlatform);
                }
                //if the jumper is moving down and collides with a moving platform
                if (waterboy.getVy()>0 && waterboy.collides(movingPlatform)) {
                    waterboy.setY(movingPlatform.getY()-waterboy.getHeight());
                    waterboy.setOnMovingPlatform(movingPlatform);
                }
                //if the jumper is moving up and collides with the platform
                else if (waterboy.getVy()<0 && waterboy.collides(movingPlatform)) {
                    waterboy.setY(movingPlatform.getY()-waterboy.getHeight());
                    waterboy.setOnMovingPlatform(movingPlatform);
                }

            }
            
            for (Platform platform:platformList) {
                //if the object is moving down and collides with the platform
                if (firegirl.getVy()>0 && firegirl.collides(platform)) {
                    firegirl.setY(platform.getY()-firegirl.getHeight());
                    firegirl.setVy(0);
                }
                //if the object is moving up and collides with the platform
                else if (firegirl.getVy()<0 && firegirl.collides(platform)) {
                    firegirl.setY(platform.getY() + platform.getHeight());
                    firegirl.setVy(0);
                }
                //if the object is moving down and collides with the platform
                if (waterboy.getVy()>0 && waterboy.collides(platform)) {
                    waterboy.setY(platform.getY()-waterboy.getHeight());
                    waterboy.setVy(0);
                }
                //if the object is moving up and collides with the platform
                else if (waterboy.getVy()<0 && waterboy.collides(platform)) {
                    waterboy.setY(platform.getY() + platform.getHeight());
                    waterboy.setVy(0);
                }

            }

            //if the object collides with the door
            for (Door door:doorList) {
                if (door.getName() == "red" && firegirl.collides (door)) {
                    gameStatus = "Won";
                    firegirl.setVx (0);
                    firegirl.setVy (0);
                    System.out.println ("firegirl WIN!!!");
//                    gameFrame.dispose();
//                    new Menu();
                }
                
                if (door.getName() == "blue" && waterboy.collides (door)) {
                    gameStatus = "Won";
                    waterboy.setVx (0);
                    waterboy.setVy (0);
                    System.out.println ("waterboy WIN!!!");
                }

            }

            //if the object collides with any of the GreenGoo
            for (GreenGoo greenGoo:greenGooList) {
                if (firegirl.collides (greenGoo)) {
                    gameStatus = "Lost";
                    firegirl.setVx (0);
                    firegirl.setVy (0);
                    System.out.println("You LOST!!!");
                    gameFrame.dispose();
                    //new Menu();
                }
                if (waterboy.collides (greenGoo)) {
                    gameStatus = "Lost";
                    waterboy.setVx (0);
                    waterboy.setVy (0);
                    System.out.println("You LOST!!!");
                    //game.dispose();
                      gameFrame.dispose();
                    //new Menu();
                }               
            }

            //if jumper hits left edge of the screen, it should bounce back
            if (firegirl.getX()<=1) {
                firegirl.setX(2*Math.abs(firegirl.getX()));
            }
            else if (firegirl.getX()>= Const.WIDTH) {
                firegirl.setX(2*Const.WIDTH-firegirl.getX());
            }
            
            if (waterboy.getX()<=1) {
                waterboy.setX(2*Math.abs(waterboy.getX()));
            }
            else if (waterboy.getX()>= Const.WIDTH) {
                waterboy.setX(2*Const.WIDTH-waterboy.getX());
            }
 
        
        }
    }   
//------------------------------------------------------------------------------   
    //act upon key events 
    public class MyKeyListener implements KeyListener{    
        public void keyPressed(KeyEvent e){ 
            int key = e.getKeyCode(); 
            if ((key == KeyEvent.VK_UP) && (firegirl.getVy() == 0 || firegirl.getOnMovingPlatform())) {
                if (firegirl.getOnMovingPlatform()) {
                    firegirl.setVy(Const.JUMP_SPEED + firegirl.getMovingPlatformVy());
                }
                else {
                    firegirl.setVy(Const.JUMP_SPEED);
                }
                System.out.println("up is pressed");
                firegirl.unsetOnMovingPlatform();
            }
            if (key == KeyEvent.VK_LEFT) { 
                firegirl.setVx(-Const.RUN_SPEED);
                System.out.println("left is pressed");
                firegirl.unsetOnMovingPlatform();
            }
            if (key == KeyEvent.VK_RIGHT) { 
                firegirl.setVx(Const.RUN_SPEED); 
                System.out.println("right is pressed");
                firegirl.unsetOnMovingPlatform();
            } 
//            if ((key == KeyEvent.VK_UP) && (firegirl.getVy() == 0)) {
//                    firegirl.setVy(Const.JUMP_SPEED);
//            }
            
// for firegirl,needs to change
                      //int key = e.getKeyCode(); 
            if ((key == KeyEvent.VK_W) && (waterboy.getVy() == 0 || waterboy.getOnMovingPlatform())) {
                waterboy.setVy(Const.JUMP_SPEED);
                System.out.println("up is pressed");
                waterboy.unsetOnMovingPlatform();
            }
            if (key == KeyEvent.VK_A) { 
                waterboy.setVx(-Const.RUN_SPEED);
                System.out.println("left is pressed");
                waterboy.unsetOnMovingPlatform();
            }
            if (key == KeyEvent.VK_D) { 
                waterboy.setVx(Const.RUN_SPEED); 
                System.out.println("right is pressed");
                waterboy.unsetOnMovingPlatform();
            } 
//            if ((key == KeyEvent.VK_UP) && (firegirl.getVy() == 0)) {
//                    firegirl.setVy(Const.JUMP_SPEED);
//            }
            
        } 
        public void keyReleased(KeyEvent e){
            int key = e.getKeyCode();
            if (key != KeyEvent.VK_LEFT){
                firegirl.setVx(0);
            }
            if (key != KeyEvent.VK_RIGHT){
                firegirl.setVx(0);
            }
            if (key != KeyEvent.VK_A){
                waterboy.setVx(0);
            }
            if (key != KeyEvent.VK_D){
                waterboy.setVx(0);
            }
            
            
        }
        public void keyTyped(KeyEvent e){
        }            
    }     
//------------------------------------------------------------------------------ 
    //draw everything 
    public class GamePanel extends JPanel{ 
        GamePanel(){ 
            setFocusable(true); 
            requestFocusInWindow(); 
        } 
         
        @Override 
        public void paintComponent(Graphics g){  
            super.paintComponent(g); //required
            background.draw(g);
            firegirl.draw(g); 
            waterboy.draw(g);

            for (Platform platform:platformList)
                platform.draw(g);

            for (GreenGoo greenGoo:greenGooList)
                greenGoo.draw(g);

            for (Door door:doorList)
                door.draw(g);

            for (MovingPlatform movingPlatform: movingPlatformList)
                movingPlatform.draw(g);
        }
    }

    public static void main(String [] args) throws Exception {
        JFrame gameFrame = new JFrame("Game 'Firegirl and waterboy'");
        Game game = new Game(gameFrame);
        game.setUpGamePlatform();
        game.runGameLoop();
    }

}