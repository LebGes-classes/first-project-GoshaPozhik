public class Maze {
    private final char[][] grid;
    private final int exitX;
    private final int exitY;
    
    public Maze(int size) {
        size = size % 2 == 0 ? size + 1 : size;
        grid = new char[size][size];
        exitX = size - 2;
        exitY = size - 2;
        generateMaze();
    }
    
    private void generateMaze() {
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                grid[i][j] = '#';
            }
        }
        
        carve(1, 1);
        grid[exitX][exitY] = 'E';
    }
    
    private void carve(int x, int y) {
        grid[x][y] = ' ';
        int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
        shuffle(dirs);
        
        for(int[] dir : dirs) {
            int nx = x + dir[0]*2;
            int ny = y + dir[1]*2;
            
            if(nx > 0 && ny > 0 && nx < grid.length-1 && ny < grid[0].length-1) {
                if(grid[nx][ny] == '#') {
                    grid[x + dir[0]][y + dir[1]] = ' ';
                    carve(nx, ny);
                }
            }
        }
    }
    
    private void shuffle(int[][] array) {
        for(int i = 0; i < array.length; i++) {
            int r = (int)(Math.random() * array.length);
            int[] temp = array[i];
            array[i] = array[r];
            array[r] = temp;
        }
    }
    
    public void print(int px, int py) {
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(i == px && j == py) System.out.print('P');
                else System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }
    
    public boolean canMove(int x, int y) {
        return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length 
            && grid[x][y] != '#';
    }
    
    public boolean isExit(int x, int y) {
        return x == exitX && y == exitY;
    }
}