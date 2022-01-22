public class FinalProject{ 
     
    public static void main(String [] args) throws Exception{ 
        Menu menu = new Menu();
        menu.active = true;
        menu.setUpMenu();
        while (menu.active) {
            System.out.println(menu.active);
        }
        Game game = new Game(); 
        game.setUpGamePlatform();
        game.runGameLoop();
        while (game.gameActive) {
            System.out.println(game.gameStatus);
            if (game.gameStatus == "Lost") {
                System.out.println("you lose");
                LoseWindow loseWindow = new LoseWindow();
                loseWindow.setUpLoseWindow();
                while (loseWindow.active) {
                    System.out.println(loseWindow.active);
                }
                game = new Game(); 
                game.setUpGamePlatform();
                game.runGameLoop();
            }
        }
        //this is a test
    }  
}