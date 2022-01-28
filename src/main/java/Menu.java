import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//class Menu extends JFrame{
public class Menu extends JFrame{
    boolean active = true;
    //JFrame gameFrame;
//    JPanel gamePanel;

    JMenuBar gameMenuBar;
    JMenu gameSubmenu;
    JMenuItem gameMenuItemStart, gameMenuItemExit, gameMenuItemInstructions;

    boolean startSignal = false;
    boolean instructionSignal = false;

    Menu() {
        super.setTitle ( "Firegirl and Waterboy" );
        super.setSize (Const.WIDTH,Const.HEIGHT);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setResizable(false);
        //gameFrame = new JFrame("Firegirl and Waterboy");
        //gameFrame.setSize(Const.WIDTH,Const.HEIGHT);
        //gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //gameFrame.setResizable(false);

        //Create the menu bar.
        gameMenuBar = new JMenuBar();

        //Build the first menu.
        gameSubmenu = new JMenu("Menu Bar");
        gameSubmenu.setMnemonic(KeyEvent.VK_G);
        gameSubmenu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        gameMenuBar.add(gameSubmenu);

        //a group of JMenuItems
        gameMenuItemStart = new JMenuItem("Start", KeyEvent.VK_S );
        gameSubmenu.add(gameMenuItemStart);
        gameMenuItemStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                startSignal = true;
            }
        });

        gameMenuItemExit = new JMenuItem("Exit", KeyEvent.VK_X );
        gameMenuItemExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                System.exit(0);
            }
        });
        
        gameMenuItemInstructions = new JMenuItem("Instructions",  KeyEvent.VK_V);
        gameMenuItemInstructions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                //instructionSignal = true;
                //JOptionPane.showMessageDialog(gameFrame, "Instructions!!!");

                JPanel panel = new JPanel();
                panel.setSize(500,640);
                panel.setBackground(Color.CYAN);
                ImageIcon icon = new ImageIcon("images/instructions.png");
                JLabel label = new JLabel();
                label.setIcon(icon);
                panel.add(label);
                Menu.super.getContentPane ().add(panel);
                Menu.super.setVisible(true);
                //gameFrame.getContentPane().add(panel);
                //gameFrame.setVisible(true);
            }
        });
        
        gameSubmenu.add(gameMenuItemExit);
        super.setJMenuBar (gameMenuBar);
        //gameFrame.setJMenuBar(gameMenuBar);
        gameSubmenu.add(gameMenuItemInstructions);

        super.setVisible(true);
        //gameFrame.setVisible(true);
    }

//    public class InstructionsButtonListener implements ActionListener {
//        JFrame frame;
//        InstructionButtonListener(JFrame frame) {
//            this.frame = frame;
//        }
//        public void actionPerformed(ActionEvent event) {
//            new Instructions();
//        }
//    }

    public static void main(String[] args) {
        Menu gameMenu = new Menu();
        //JFrame gameFrame = new JFrame("Firegirl and Waterboy");
        Game game = new Game();
        game.setUpGamePlatform();
        game.runGameLoop();

        //gameMenu.gameFrame.setVisible(true);
    }

}