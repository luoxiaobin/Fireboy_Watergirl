import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class LoseWindow {
    JFrame loseWindow;
    JPanel panel;
    JButton restartButton;
    JButton exitButton;
    boolean active;
    
    LoseWindow() {
        loseWindow = new JFrame("You lost!");
        loseWindow.setSize(800,600);
        loseWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        loseWindow.add(panel);
        restartButton = new JButton("Restart");
        exitButton = new JButton("Exit");
        panel.add(restartButton);
        loseWindow.setVisible(true); 
        this.active = true;
    }
    public void setUpLoseWindow() {
        loseWindow.setSize(400,200);
        loseWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loseWindow.setLayout(new FlowLayout());
        loseWindow.add(panel);
        panel.setLayout(new GridLayout(0,1));
        panel.add(restartButton);
        panel.add(exitButton);
        restartButton.addActionListener(new RestartButtonListener());
        exitButton.addActionListener(new ExitButtonListener());
        loseWindow.setVisible(true);
    }
    public class RestartButtonListener implements ActionListener{       //this is the required class definition 
        public void actionPerformed(ActionEvent event){          //this is the only method in this class  
            // The code below will run automatically when the enterButton is activated 
            active = false;
        } 
    }
   public class ExitButtonListener implements ActionListener{       //this is the required class definition 
        public void actionPerformed(ActionEvent event){          //this is the only method in this class  
            // The code below will run automatically when the enterButton is activated 
            loseWindow.dispose();
        } 
    }

}