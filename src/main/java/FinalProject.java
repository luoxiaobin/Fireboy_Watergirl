import javax.swing.*;

public class FinalProject{
     
    public static void main(String [] args) throws Exception{ 

        int level = 1;

        Menu gameMenu = new Menu();
        //JFrame gameFrame = new JFrame("Firegirl and Waterboy");
        Game game = new Game(gameMenu.gameFrame, level);
        game.setUpGamePlatform();

        while (!gameMenu.startSignal) {
            System.out.print(gameMenu.startSignal);
        }

        System.out.println("level 1 is now starting");
        JFrame gameFrame = new JFrame("Game 'Firegirl and waterboy'");
        game = new Game(gameFrame, level);
        game.setUpGamePlatform();
        game.runGameLoop();
        while (game.gameActive ) {
            System.out.println(game.gameStatus);
            if (game.gameStatus == "Lost") {
                System.out.println("You lost");
                gameMenu = new Menu();
                game = new Game(gameMenu.gameFrame, level);
                game.setUpGamePlatform();
                game.showGameOverScreen();
                gameMenu.startSignal = false;
                while (!gameMenu.startSignal) {
                    System.out.println(gameMenu.startSignal);
                }
                gameFrame = new JFrame("Game 'Firegirl and Waterboy'");
                game = new Game(gameFrame, level);
                game.setUpGamePlatform();
                game.runGameLoop();
            }
            else if (game.gameStatus == "Won") {
                System.out.println("You won");
                level = level + 1;
                game = new Game(gameMenu.gameFrame, level);
                game.setUpGamePlatform();
                game.showGameOverScreen();
                gameMenu.startSignal = false;
                while (!gameMenu.startSignal) {
                    System.out.println(gameMenu.startSignal);
                }
                gameFrame = new JFrame("Game 'Firegirl and Waterboy'");
                game = new Game(gameFrame, level);
                game.setUpGamePlatform();
                game.runGameLoop();
            }
        }
        //this is a test
    }  
}