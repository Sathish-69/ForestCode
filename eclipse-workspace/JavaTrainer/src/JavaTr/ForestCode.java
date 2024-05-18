package JavaTr;
import java.util.Random;
import java.util.Scanner;

public class ForestCode {
    public static void main(String[] args) {
        char[][] forest = generateForest(5, 5);
        
    
        displayForest(forest);
        
        Scanner scanner = new Scanner(System.in);
        char direction;
        
        while (true) {
            System.out.print("Enter move (W=Up, S=Down, A=Left, D=Right, Q=Quit): ");
            direction = scanner.next().charAt(0);
            if (direction == 'Q') {
                System.out.println("Game over.");
                break;
            }
            movePlayer(forest, direction);
            displayForest(forest);
        }
        
        scanner.close();
    }
    public static char[][] generateForest(int rows, int cols) {
        char[][] forest = new char[rows][cols];
        Random rand = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rand.nextDouble() < 0.3) {
                    forest[i][j] = 'T';
                } else {
                    forest[i][j] = '.';
                }
            }
        }
        int playerRow, playerCol;
        do {
            playerRow = rand.nextInt(rows);
            playerCol = rand.nextInt(cols);
        } while (forest[playerRow][playerCol] == 'T');
        
        forest[playerRow][playerCol] = 'P';
        
        return forest;
    }
    public static void displayForest(char[][] forest) {
        for (char[] row : forest) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void movePlayer(char[][] forest, char direction) {
        int[] playerPos = findPlayer(forest);
        int playerRow = playerPos[0];
        int playerCol = playerPos[1];
    
        switch (direction) {
            case 'W':
                if (playerRow > 0 && forest[playerRow - 1][playerCol] != 'T') {
                    forest[playerRow][playerCol] = '.';
                    forest[playerRow - 1][playerCol] = 'P';
                } else {
                    System.out.println("Cannot move up. Invalid move!");
                }
                break;
            case 'S':
                if (playerRow < forest.length - 1 && forest[playerRow + 1][playerCol] != 'T') {
                    forest[playerRow][playerCol] = '.';
                    forest[playerRow + 1][playerCol] = 'P';
                } else {
                    System.out.println("Cannot move down. Invalid move!");
                }
                break;
            case 'A':
                if (playerCol > 0 && forest[playerRow][playerCol - 1] != 'T') {
                    forest[playerRow][playerCol] = '.';
                    forest[playerRow][playerCol - 1] = 'P';
                } else {
                    System.out.println("Cannot move left. Invalid move!");
                }
                break;
            case 'D':
                if (playerCol < forest[0].length - 1 && forest[playerRow][playerCol + 1] != 'T') {
                    forest[playerRow][playerCol] = '.';
                    forest[playerRow][playerCol + 1] = 'P';
                } else {
                    System.out.println("Cannot move right. Invalid move!");
                }
                break;
            default:
                System.out.println("Invalid direction input!");
        }
    }

    public static int[] findPlayer(char[][] forest) {
        int[] playerPos = new int[2];
        for (int i = 0; i < forest.length; i++) {
            for (int j = 0; j < forest[0].length; j++) {
                if (forest[i][j] == 'P') {
                    playerPos[0] = i;
                    playerPos[1] = j;
                    return playerPos;
                }
            }
        }
        return playerPos;
    }
}
