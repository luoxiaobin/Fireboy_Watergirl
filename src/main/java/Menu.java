import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//class Menu extends JFrame{
class Menu {
    JFrame gameFrame;
//    JPanel gamePanel;

    JMenuBar gameMenuBar;
    JMenu gameSubmenu;
    JMenuItem gameMenuItemStart, gameMenuItemExit;

    boolean startSignal=false;

    Menu() {
        gameFrame = new JFrame("Firegirl and Waterboy");
        gameFrame.setSize(Const.WIDTH,Const.HEIGHT);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);

        //Create the menu bar.
        gameMenuBar = new JMenuBar();

        //Build the first menu.
        gameSubmenu = new JMenu("Game");
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
        gameSubmenu.add(gameMenuItemExit);
        gameFrame.setJMenuBar(gameMenuBar);

        gameFrame.setVisible(true);
    }


    public static void main( String[] args ) {
        Menu gameMenu = new Menu();

        JFrame gameFrame = new JFrame("Firegirl and Waterboy");

        Game game = new Game(gameFrame, 1);
        game.setUpGamePlatform();
        game.runGameLoop();

        //gameMenu.gameFrame.setVisible(true);
    }

}