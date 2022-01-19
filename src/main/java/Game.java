import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;



public class Game{ 
    JFrame gameFrame; 
    GamePanel gamePanel;    
    MyKeyListener keyListener;  
    //game objects 
    Jumper jumper; 
    GameObject[] platforms;
    GreenGoo[] greenGoos;
    Door[] doors;
    //GameObject[] gameObjs;
    Background background;

    final static int MAX_OBJ_NUM = 500;
    final static String COMMA_DELIMITER = ",";

    int platformCount = 0;
    int greenGooCount = 0;
    int doorCount = 0;


//------------------------------------------------------------------------------ 
    Game(){ 
        gameFrame = new JFrame("Game Window"); 
        gamePanel = new GamePanel(); 
        keyListener = new MyKeyListener();

        String bckgPic = "images/background.png";
        background = new Background(bckgPic);

        platforms =new GameObject[MAX_OBJ_NUM];
        greenGoos =new GreenGoo[MAX_OBJ_NUM];
        doors =new Door[MAX_OBJ_NUM];
        //gameObjs = new GameObject[MAX_ROW_LENGTH];

        int jumperW = 20; 
        int jumperH = 32;
//        int jumperX = Const.WIDTH/2;
        int jumperX = 50;
//        int jumperY = Const.GROUND - jumperH;
        int jumperY = 468; // this is calculated as platform y axis (500) - height of jumper (32)
        //jumper = new Jumper(jumperX, jumperY, jumperW, jumperH);
        //jumper = new Jumper(jumperX, jumperY, jumperW, jumperH, ".//images//watergirl_small.png");
        jumper = new Jumper(jumperX, jumperY, ".//images//watergirl_small.png");

        SetupGameObjects();
    }
//------------------------------------------------------------------------------ 
//set up the game platform 
    public void setUp(){ 
        gameFrame.setSize(Const.WIDTH,Const.HEIGHT); 
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        gameFrame.setResizable(false); 
        gamePanel.addKeyListener(keyListener); 
        gameFrame.add(gamePanel);  
        gameFrame.setVisible(true);     
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
                rowScanner.useDelimiter(COMMA_DELIMITER);
                while (rowScanner.hasNext()) {
                    gameObjectRecordValues.add(rowScanner.next());
                }
                gameObjectRecords.add(gameObjectRecordValues);
            }

            int platformRowID=0;
            int greenGoosRowID=0;
            int doorsRowID=0;
            for (int row = 0; row < gameObjectRecords.size(); row++) {
                java.util.List<String> record = gameObjectRecords.get(row);

//                gameObjs[row] = new GameObject (record.get(0).toUpperCase(),  Integer.parseInt(record.get(1).trim()), Integer.parseInt(record.get(2).trim()));

                String gameObjectType = record.get(0).toUpperCase();
                int posX = Integer.parseInt(record.get(1).trim());
                int posY = Integer.parseInt(record.get(2).trim());

                switch (gameObjectType){
                //switch (gameObjs[row].ObjectType()){
                    case "P":
                        platforms[platformRowID++] = new Platform (posX, posY);
                        break;

                    case "G":
                        greenGoos[greenGoosRowID++] = new GreenGoo(posX, posY);
                        break;

                    case "D":
                        doors[doorsRowID++] = new Door(posX, posY);
                        break;

                }

            }
            platformCount = platformRowID;
            greenGooCount = greenGoosRowID;
            doorCount = doorsRowID;

        }
        catch (Exception e) 
        { 
            System.out.println(e); 
        }
        int i = 0;
    }

//------------------------------------------------------------------------------   
//    main game loop 
    public void runGameLoop(){
        String gameStatus = "playing";

        //while (true) {
        while (gameStatus.equals("playing")) {
            gameFrame.repaint(); 
            try {Thread.sleep(Const.FRAME_PERIOD);} catch(Exception e){}

            jumper.accelerate();
            jumper.moveX();
            jumper.moveY(Const.GROUND); 
            //if the object is moving down and collides with the platform
            //for (int i=0; i<MAX_ROW_LENGTH; i++){
            for (int i=0; i<platformCount; i++){
                if (jumper.getVy()>0 && jumper.collides(platforms[i])) {
                    jumper.setY(platforms[i].getY()-jumper.getHeight());
                    jumper.setVy(0);
                }
                //if the object is moving up and collides with the platform
                else if (jumper.getVy()<0 && jumper.collides(platforms[i])) {
                    jumper.setY(platforms[i].getY() + platforms[i].height());
                    jumper.setVy(0);
                }
            }

            //if the object collides with the door
            for (int i=0; i<doorCount; i++){
                if (jumper.collides (doors[i])) {
                    gameStatus = "Won";
                    jumper.setVx (0);
                    jumper.setVy (0);
                    System.out.println ("You WIN!!!");
                }
            }

            //if the object collides with any of the GreenGoo
            for (int i=0; i<greenGooCount; i++){
                if (jumper.collides (greenGoos[i])) {
                    gameStatus = "Lost";
                    jumper.setVx (0);
                    jumper.setVy (0);
                    System.out.println("You LOST!!!");
                }
            }

            //if jumper hits left edge of the screen, it should bounce back
            if (jumper.getX()<=1) {
                //jumper.setVx (Math.abs(jumper.getVx()));
                jumper.setX(2*Math.abs(jumper.getX()));
            }
            else if (jumper.getX()>= Const.WIDTH) {
                //jumper.setVx (-1*Math.abs(jumper.getVx()));
                jumper.setX(2*Const.WIDTH-jumper.getX());
            }
        }
    }   
//------------------------------------------------------------------------------   
    //act upon key events 
    public class MyKeyListener implements KeyListener{    
        public void keyPressed(KeyEvent e){ 
            int key = e.getKeyCode(); 
            if ((key == KeyEvent.VK_UP) && (jumper.getVy() == 0)) {
                jumper.setVy(Const.JUMP_SPEED);
                System.out.println("up is pressed");
            }
            if (key == KeyEvent.VK_LEFT) { 
                jumper.setVx(-Const.RUN_SPEED);
                System.out.println("left is pressed");
            }
            if (key == KeyEvent.VK_RIGHT) { 
                jumper.setVx(Const.RUN_SPEED); 
                System.out.println("right is pressed");
            } 
//            if ((key == KeyEvent.VK_UP) && (jumper.getVy() == 0)) {
//                    jumper.setVy(Const.JUMP_SPEED);
//            }
        } 
        public void keyReleased(KeyEvent e){
            int key = e.getKeyCode(); 
            if (key != KeyEvent.VK_LEFT){ 
                jumper.setVx(0);
            }
            if (key != KeyEvent.VK_RIGHT){ 
                jumper.setVx(0); 
            } 
//            if ((key == KeyEvent.VK_UP) && (jumper.getVy() == 0)) {
//                    jumper.setVy(0);
//            }
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
            background.draw ( g );
            jumper.draw(g); 

            for (int i = 0; i< MAX_OBJ_NUM; i++){
                if (platforms[i] != null) {
                    platforms[i].draw(g);
                }

                if (greenGoos[i] != null) {
                    greenGoos[i].draw(g);
                }

                if (doors[i] != null) {
                    doors[i].draw(g);
                }
            }

        }
    }     
}