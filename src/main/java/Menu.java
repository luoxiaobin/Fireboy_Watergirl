//import javax.swing.*;
//import java.awt.*;
//
//class Menu {
//    JFrame menu;
//    JPanel panel;
//    JButton startButton;
//    JButton exitButton;
//    boolean active;
//    
//    Menu() {
//        menu = new JFrame("Firegirl and Waterboy");
//        menu.setSize(800,600);
//        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        panel = new JPanel();
//        menu.add(panel);
//        startButton = new JButton("Start Game");
//        panel.add(startButton);
//        menu.setVisible(true); 
//        this.active = true;
//    }
//    public void setUpMenu() {
//        menu.setSize(400,200);
//        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        menu.setLayout(new FlowLayout());
//        menu.add(panel);
//        panel.setLayout(new GridLayout(0,1));
//        panel.add(startButton);
//        panel.add(exitButton);
//        startButton.addActionListener(new ButtonListener());
//        exitButton.addActionListener(new ButtonListener());
//        menu.setVisible(true);
//    }
//    public class ButtonListener implements ActionListener{       //this is the required class definition 
//        public void actionPerformed(ActionEvent event){          //this is the only method in this class  
//            // The code below will run automatically when the enterButton is activated 
//            this.active = false;
//        } 
//    }
//}