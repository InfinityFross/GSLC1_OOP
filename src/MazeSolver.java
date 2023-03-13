import java.util.*;
public class MazeSolver {
	public static void main(String[] args) {
        int rows = 10;
        int cols = 10;
        int[][] maze = generateMaze(rows, cols);
        printMaze(maze);
        
        int[] start = {0, 0};
        int[] end = {rows-1, cols-1};
        List<int[]> path = findShortestPath(maze, start, end);
        
        if (path != null) {
            System.out.println("The shortest path from start to end:");
            for (int[] cell : path) {
                System.out.println("(" + cell[0] + ", " + cell[1] + ")");
            }
        } else {
            System.out.println("No path found from start to end.");
        }
    }
    
    private static int[][] generateMaze(int rows, int cols) {
        int[][] maze = new int[rows][cols];
        Random random = new Random();
        
        // Set random obstacles
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (random.nextDouble() < 0.3) {
                    maze[i][j] = 1;
                }
            }
        }
        
        // Set start and end points
        maze[0][0] = 2;
        maze[rows-1][cols-1] = 3;
        
        return maze;
    }
    
    private static void printMaze(int[][] maze) {
        for (int[] row : maze) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
    
    private static List<int[]> findShortestPath(int[][] maze, int[] start, int[] end) {
        int rows = maze.length;
        int cols = maze[0].length;
        int[][] visited = new int[rows][cols];
        Queue<int[]> queue = new LinkedList<>();
        Map<int[], int[]> parentMap = new HashMap<>();
        
        visited[start[0]][start[1]] = 1;
        queue.add(start);
        
        while (!queue.isEmpty()) {
            int[] current = queue.remove();
            if (Arrays.equals(current, end)) {
                // Found the end point, trace back the path
                List<int[]> path = new ArrayList<>();
                while (!Arrays.equals(current, start)) {
                    path.add(current);
                    current = parentMap.get(current);
                }
                path.add(start);
                Collections.reverse(path);
                return path;
            }
            
            // Check neighbors
            int[][] neighbors = {{current[0]-1, current[1]}, {current[0]+1, current[1]},
                                  {current[0], current[1]-1}, {current[0], current[1]+1}};
            for (int[] neighbor : neighbors) {
                if (neighbor[0] < 0 || neighbor[0] >= rows || neighbor[1] < 0 || neighbor[1] >= cols) {
                    // Out of bounds
                    continue;
                }
                if (maze[neighbor[0]][neighbor[1]] == 1 || visited[neighbor[0]][neighbor[1]] == 1) {
                    // Obstacle or already visited
                    continue;
                }
                visited[neighbor[0]][neighbor[1]] = 1;
                queue.add(neighbor);
                parentMap.put(neighbor, current);
            }
        }
        
        // No path found
        return null;
    }
}
