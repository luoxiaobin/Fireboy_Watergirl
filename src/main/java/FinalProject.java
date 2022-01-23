import javax.swing.*;

public class FinalProject{
     
    public static void main(String [] args) throws Exception{ 


        Menu gameMenu = new Menu();
        //JFrame gameFrame = new JFrame("Firegirl and Waterboy");
        Game game = new Game(gameMenu.gameFrame);
        game.setUpGamePlatform();

        while (!gameMenu.startSignal) {
            System.out.println(gameMenu.startSignal);
        }

        System.out.println("game is now started");
        JFrame gameFrame = new JFrame("Game 'Firegirl and waterboy'");
        game = new Game(gameFrame);
        game.setUpGamePlatform();
        game.runGameLoop();
        while (game.gameActive ) {
            System.out.println(game.gameStatus);
            if (game.gameStatus == "Lost") {
                System.out.println("you lose");
                gameMenu = new Menu();
                game = new Game(gameMenu.gameFrame);
                game.setUpGamePlatform();
                gameMenu.startSignal = false;
                while (!gameMenu.startSignal) {
                    System.out.println(gameMenu.startSignal);
                }
                gameFrame = new JFrame("Game 'Firegirl and waterboy'");
                game = new Game(gameFrame);
                game.setUpGamePlatform();
                game.runGameLoop();
            }
        }
        //this is a test
    }  
}