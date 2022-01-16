import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
 
public class Game{ 
    JFrame gameFrame; 
    GamePanel gamePanel;    
    MyKeyListener keyListener;  
    //game objects 
    Jumper jumper; 
    Platform platform; 
//------------------------------------------------------------------------------ 
    Game(){ 
        gameFrame = new JFrame("Game Window"); 
        gamePanel = new GamePanel(); 
        keyListener = new MyKeyListener(); 
         
        int jumperW = 20; 
        int jumperH = 20; 
        int jumperX = Const.WIDTH/2; 
        int jumperY = Const.GROUND - jumperH; 
        jumper = new Jumper(jumperX, jumperY, jumperW, jumperH); 
         
        int platformW = 300; 
        int platformH = 20; 
        int platformX = 250; 
        int platformY = 400; 
        platform = new Platform(platformX, platformY, platformW, platformH); 
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
        /*
        for (int row=0; row<platforms.length; row++) {
            for (int column=0; column<MAX_LINE_LENGTH; column++) {
                Platform currentPlatform = platforms[row]; 
                int platformWidth = 0;
                while (Character.toString(currentPlatform.getLine()[column]) == "_") {
                    platformWidth = platformWidth + 1;
                }
                Box box = new Box(column, row, platformWidth);
                    if (Character.toString(currentPlatform.getLine()[column]) == "_") {
                    platformWidth = platformWidth + 1;
                }
                else if (Character.toString(currentPlatform.getLine()[column]) == " ") {
                }
            }
        } */         
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
            if (jumper.getVy()>0 && jumper.collides(platform)){ 
                jumper.setY(platform.getY() - jumper.getHeight()); 
                jumper.setVy(0); 
            }  
            //if the object is moving up and collides with the platform 
            else if (jumper.getVy()<0 && jumper.collides(platform)){ 
                jumper.setY(platform.getY() + platform.getHeight()); 
                jumper.setVy(0); 
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
            platform.draw(g); 
        }     
    }     
}