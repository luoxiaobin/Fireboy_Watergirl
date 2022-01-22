import javax.swing.*;

public class FinalProject{
     
    public static void main(String [] args) throws Exception{ 

        Menu gameMenu = new Menu();
        gameMenu.startSignal = false;

        //Game game = new Game(gameMenu.gameFrame);

        while (!gameMenu.startSignal) {
            System.out.println(gameMenu.startSignal);
        }

        System.out.println("game is now started");
        JFrame gameFrame = new JFrame("Game 'Firegirl and waterboy'");
        //Game game = new Game(gameFrame);
        Game game = new Game(gameMenu.gameFrame);
        game.setUpGamePlatform();
        game.runGameLoop();
        while (game.gameActive ) {
            System.out.println(game.gameStatus);
            if (game.gameStatus == "Lost") {
                System.out.println("you lose");
                gameMenu = new Menu();
                gameMenu.startSignal = false;
                while (!gameMenu.startSignal) {
                    System.out.println(gameMenu.startSignal);
                }
                game = new Game(gameMenu.gameFrame);
                game.setUpGamePlatform();
                game.runGameLoop();
            }
        }
        //this is a test
    }  
}