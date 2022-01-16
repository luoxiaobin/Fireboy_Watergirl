
import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

import java.util.*;
import java.io.File; 



public class Game{ 
    JFrame gameFrame; 
    GamePanel gamePanel;    
    MyKeyListener keyListener;  
    //game objects 
    Jumper jumper; 
    Platform[] platforms;
    GreenGoo[] greenGoos;
    Door[] doors;
    GameObject[] gameObjs;



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
        //platformsArray = new PlatformArray[MAX_ROW_LENGTH];
        platforms =new Platform[MAX_ROW_LENGTH];
        greenGoos =new GreenGoo[MAX_ROW_LENGTH];
        doors =new Door[MAX_ROW_LENGTH];
        gameObjs = new GameObject[MAX_ROW_LENGTH];

        int jumperW = 20; 
        int jumperH = 20; 
        int jumperX = Const.WIDTH/2; 
        int jumperY = Const.GROUND - jumperH; 
        jumper = new Jumper(jumperX, jumperY, jumperW, jumperH); 

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

    private java.util.List<String> getRecordFromLine(String line) {
        java.util.List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(COMMA_DELIMITER);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }


    public void SetupGameObjects() {
        try {
            java.util.List<java.util.List<String>> records = new ArrayList<>();

            System.out.println(System.getProperty("user.dir"));
            Scanner scanner = new Scanner(new File(".//PlatformLayout.cfg"));
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }

            int platformRowID=0;
            int greenGoosRowID=0;
            int doorsRowID=0;
            for (int row = 0; row < records.size(); row++) {
                java.util.List<String> record = records.get(row);

                gameObjs[row] = new GameObject (record.get(0).toUpperCase(),
                                                        Integer.parseInt(record.get(1).trim()),
                                                        Integer.parseInt(record.get(2).trim()),
                                                        Integer.parseInt(record.get(3).trim()),
                                                        Integer.parseInt(record.get(4).trim()));
                switch (gameObjs[row].ObjectType()){
                    case "P":
                        platforms[platformRowID++] = new Platform(gameObjs[row].x(),gameObjs[row].y(),gameObjs[row].width(),gameObjs[row].height());
                        break;

                    case "G":
                        greenGoos[greenGoosRowID++] = new GreenGoo(gameObjs[row].x(),gameObjs[row].y(),gameObjs[row].width(),gameObjs[row].height());
                        break;

                    case "D":
                        doors[doorsRowID++] = new Door(gameObjs[row].x(),gameObjs[row].y(),gameObjs[row].width(),gameObjs[row].height());
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
        while (true) { 
            gameFrame.repaint(); 
            try  {Thread.sleep(Const.FRAME_PERIOD);} catch(Exception e){} 
 
            jumper.accellerate(); 
            jumper.moveX(); 
            jumper.moveY(Const.GROUND); 
            //if the object is moving down and collides with the platform
            //for (int i=0; i<MAX_ROW_LENGTH; i++){
            for (int i=0; i<platformCount; i++){
                if (jumper.getVy()>0 && jumper.collides(platforms[i])){
                    jumper.setY(platforms[i].getY() - jumper.getHeight());
                    jumper.setVy(0);
                }
                //if the object is moving up and collides with the platform
                else if (jumper.getVy()<0 && jumper.collides(platforms[i])){
                    jumper.setY(platforms[i].getY() + platforms[i].getHeight());
                    jumper.setVy(0);
                }

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
            } else if (key == KeyEvent.VK_RIGHT){ 
                jumper.setVx(Const.RUN_SPEED); 
            } else if (key == KeyEvent.VK_UP && jumper.isOnLevel(Const.GROUND)){ 
                jumper.setVy(Const.JUMP_SPEED); 
            } else{ 
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
            jumper.draw(g); 
            /*int numRows = layout.length;
            int numColumns = layout[0].length;
            for (int row=0; row<layout.length; row++) { //drawing each row in platforms (an array of platforms)
                for (int column=0; column<numColumns; column++) {
                    layout[row][column].draw(g);
                }
            }*/
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
                //greenGoos[i].draw(g);
                //doors[i].draw(g);
            }


        }     
    }     
}