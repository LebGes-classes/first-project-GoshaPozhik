import java.util.Scanner;

public class GameManager {
    private Player player;
    private Maze maze;
    private int level;
    private long levelStart;
    private final int BASE_TIME = 5;
    
    public void start() {
        player = new Player();
        level = 1;
        
        while(!player.isGameOver()) {
            maze = new Maze(5 + level*2);
            levelStart = System.currentTimeMillis();
            runLevel();
            if(!player.isGameOver()) level++;
        }
        
        System.out.println("GAME OVER! Final score: " + player.getScore());
        Menu.clearConsole();
    }
    
    private void runLevel() {
        while(true) {
            Menu.clearConsole();
            printGameState();
            
            if(checkGameOver()) return;
            
            char input = getPlayerInput();
            if(input == 'q') {
                player.setGameOver(true);
                return;
            }
            
            player.move(input, maze);
            
            if(maze.isExit(player.getX(), player.getY())) {
                player.addScore(50);
                return;
            }
        }
    }
    
    private boolean checkGameOver() {
        int timeSpent = (int)((System.currentTimeMillis() - levelStart)/1000);
        boolean timeOut = timeSpent >= BASE_TIME - level*2;
        boolean noPoints = player.getScore() <= 0;
        
        if(timeOut || noPoints) {
            player.setGameOver(true);
            return true;
        }
        return false;
    }
    
    private void printGameState() {
        maze.print(player.getX(), player.getY());
        int timeLeft = BASE_TIME - (int)((System.currentTimeMillis() - levelStart)/1000);
        System.out.println("Level: " + level);
        System.out.println("Score: " + player.getScore());
        System.out.println("Time left: " + timeLeft + "s");
        System.out.println("Controls: WASD - Move, Q - Quit");
    }
    
    private char getPlayerInput() {
        System.out.print("Your move: ");
        return new Scanner(System.in).nextLine().toLowerCase().charAt(0);
    }
}