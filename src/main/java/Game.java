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
    GameObject[] gameObjs;
    Background background;



    final static int MAX_LINE_LENGTH = 120;
    final static int MAX_ROW_LENGTH = 10;
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

        //platformsArray = new PlatformArray[MAX_ROW_LENGTH];
        platforms =new GameObject[MAX_ROW_LENGTH];
        greenGoos =new GreenGoo[MAX_ROW_LENGTH];
        doors =new Door[MAX_ROW_LENGTH];
        gameObjs = new GameObject[MAX_ROW_LENGTH];

        int jumperW = 20; 
        int jumperH = 32;
        int jumperX = Const.WIDTH/2;
        int jumperY = Const.GROUND - jumperH;
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
            Scanner scanner = new Scanner(new File(".//PlatformLayout.cfg"));
            while (scanner.hasNextLine()) {
                String gameObjectRecordString = scanner.nextLine();

                java.util.List<String> gameObjectRecordValues = new ArrayList<>();
                Scanner rowScanner = new Scanner(gameObjectRecordString);
                rowScanner.useDelimiter(COMMA_DELIMITER);
                while (rowScanner.hasNext()) {
                    gameObjectRecordValues.add(rowScanner.next());
                }
                gameObjectRecords.add ( gameObjectRecordValues );
            }

            int platformRowID=0;
            int greenGoosRowID=0;
            int doorsRowID=0;
            for (int row = 0; row < gameObjectRecords.size(); row++) {
                java.util.List<String> record = gameObjectRecords.get(row);

                gameObjs[row] = new GameObject (record.get(0).toUpperCase(),
                                                        Integer.parseInt(record.get(1).trim()),
                                                        Integer.parseInt(record.get(2).trim()),
                                                        Integer.parseInt(record.get(3).trim()),
                                                        Integer.parseInt(record.get(4).trim()));
                switch (gameObjs[row].ObjectType()){
                    case "P":
                        platforms[platformRowID++] = new Platform ( gameObjs[row].getX ( ) , gameObjs[row].getY ( ));
                        break;

                    case "G":
                        greenGoos[greenGoosRowID++] = new GreenGoo ( gameObjs[row].getX ( ) , gameObjs[row].getY ( ));
                        break;

                    case "D":
                        doors[doorsRowID++] = new Door ( gameObjs[row].getX ( ) , gameObjs[row].getY ( ));
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
        while (gameStatus.equals ( "playing" )) {
            gameFrame.repaint(); 
            try  {Thread.sleep(Const.FRAME_PERIOD);} catch(Exception e){}

            jumper.accelerate ( );
            jumper.moveX ( );
            jumper.moveY(Const.GROUND); 
            //if the object is moving down and collides with the platform
            //for (int i=0; i<MAX_ROW_LENGTH; i++){
            for (int i=0; i<platformCount; i++){
                if (jumper.getVy ( ) > 0 && jumper.collides ( platforms[i] )) {
                    jumper.setY ( platforms[i].getY ( ) - jumper.getHeight ( ) );
                    jumper.setVy ( 0 );
                }
                //if the object is moving up and collides with the platform
                else if (jumper.getVy ( ) < 0 && jumper.collides ( platforms[i] )) {
                    jumper.setY ( platforms[i].getY ( ) + platforms[i].height ( ) );
                    jumper.setVy ( 0 );
                }
            }

            //if the object collides with the door
            for (int i=0; i<doorCount; i++){
                if (jumper.collides ( doors[i] )) {
                    gameStatus = "Won";
                    jumper.setVx ( 0 );
                    jumper.setVy ( 0 );
                    System.out.println ( "You WIN!!!" );
                }
            }

            //if the object collides with any of the GreenGoo
            for (int i=0; i<greenGooCount; i++){
                if (jumper.collides ( greenGoos[i] )) {
                    gameStatus = "Lost";
                    jumper.setVx ( 0 );
                    jumper.setVy ( 0 );
                    System.out.println ( "You LOST!!!" );
                }
            }

            //if jumper hits left edge of the screen, it should bounce back
            if (jumper.getX ( ) <= 0) {
                jumper.setVx ( Math.abs ( jumper.getVx ( ) ) );
            } else if (jumper.getX ( ) >= Const.WIDTH) {
                jumper.setVx ( - 1 * Math.abs ( jumper.getVx ( ) ) );
            }
        }
    }   
//------------------------------------------------------------------------------   
    //act upon key events 
    public class MyKeyListener implements KeyListener{    
        public void keyPressed(KeyEvent e){ 
            int key = e.getKeyCode(); 
            if (key == KeyEvent.VK_LEFT){ 
                jumper.setVx(-Const.RUN_SPEED);
            }
            else if (key == KeyEvent.VK_RIGHT){ 
                jumper.setVx(Const.RUN_SPEED); 
            //} else if (key == KeyEvent.VK_UP && jumper.isOnLevel(Const.GROUND)){
            //    jumper.setVy(Const.JUMP_SPEED);
            } 
            else if ((key == KeyEvent.VK_UP) && (jumper.getVy() == 0)) {
                    jumper.setVy(Const.JUMP_SPEED);
            }
            else {
                jumper.setVx(0); //stop if any other key is pressed 
            }              
        } 
        public void keyReleased(KeyEvent e){  
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

            for (int i=0; i<MAX_ROW_LENGTH; i++){
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