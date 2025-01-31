public class Player {
    private int x = 1;
    private int y = 1;
    private int score = 100;
    private boolean gameOver;
    
    public void move(char dir, Maze maze) {
        int newX = x, newY = y;
        
        switch(dir) {
            case 'w': newX--; break;
            case 's': newX++; break;
            case 'a': newY--; break;
            case 'd': newY++; break;
        }
        
        if(maze.canMove(newX, newY)) {
            x = newX;
            y = newY;
            score--;
        }
    }
    
    public void addScore(int points) {
        score += points;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getScore() { return score; }
    public boolean isGameOver() { return gameOver; }
    public void setGameOver(boolean state) { gameOver = state; }
}