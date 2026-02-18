public class lifegame {

    public static int length = 15;
    public static int width = 15;
    boolean[][] grid = new boolean[length][width];

    public void game() { // main game loop
        //initailize();
        show display = new show();
        display.init();
        display.display(grid);
        display.init_grid(grid);
        while (true) {
            while (display.getWaiting()) {
                //System.out.println(display.getWaiting());
            }
            shift();
            display.display(grid);
            try {
                Thread.sleep(500); // delay for x ms
            }
            catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        }
    }

    public void initailize() { // initialize the grid
        grid[1][2] = true;
        grid[2][3] = true;
        grid[3][1] = true;
        grid[3][2] = true;
        grid[3][3] = true;
    }

    public void shift() { // shift the grid to the next generation
        int[][] count = new int[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j]) {
                    if (i-1 >= 0 && j-1 >= 0) count[i-1][j-1]++;
                    if (i-1 >= 0) count[i-1][j]++;
                    if (i-1 >= 0 && j+1 < width) count[i-1][j+1]++;
                    if (j-1 >= 0) count[i][j-1]++;
                    if (j+1 < width) count[i][j+1]++;
                    if (i+1 < length && j-1 >= 0) count[i+1][j-1]++;
                    if (i+1 < length) count[i+1][j]++;
                    if (i+1 < length && j+1 < width) count[i+1][j+1]++;
                }
            }
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (count[i][j] == 3) {
                    grid[i][j] = true;
                } else if (count[i][j] < 2 || count[i][j] > 3) {
                    grid[i][j] = false;
                }
            }
        }
    }
}