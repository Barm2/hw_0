import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner;  // Note: Do not change this line.

    public static int min(int a, int b)
    {
        if(a>b)
        {
            return b;
        }
        else
        {
            return a;
        }
    }

    public static int max(int a, int b)
    {
        if(a>b)
        {
            return a;
        }
        else
        {
            return b;
        }
    }

    public static void pregame(int[] broadSize)
    {
        char x;
        System.out.println("Dear president, please enter the board’s size.");
        broadSize[0] = scanner.nextInt();
        System.out.println(broadSize[0]);
        x = scanner.next().charAt(0);
        System.out.println(x);
        broadSize[1] = scanner.nextInt();
        System.out.println(broadSize[1]);
    }

    //the function count the number of academic standard friends of a student
    public static int check_around(boolean[][] board, int rowNum, int colNum, int row, int col)
    {
        int count = 0;
        int starting_row = max(0, row-1);
        int starting_col = max(0, col-1);
        int ending_row = min(rowNum, row+1);
        int ending_col = min(colNum, col+1);
        for(int i = starting_row; i<ending_row; i++)
        {
            for(int j = starting_col; j<ending_col; j++)
            {
                if(board[i][j])
                {
                    count++;
                }
            }
        }
        return count;
    }

    public static void studentsToChange(boolean[][] board, int rowNum, int colNum, int row, int col,boolean[][] toChange)
    {
        int standardFriendsNum = check_around(board, rowNum, colNum, row, col);
        if(board[row][col])
        {
            if((standardFriendsNum==2) || (standardFriendsNum==3))
            {
                toChange[row][col]= false;
            }
            else
            {
                toChange[row][col]=true;

            }
        }
    }

    public static void theStudentsGame() {

        int[] broadSize = new int[2];
        pregame(broadSize);
        boolean[][] board = new boolean[broadSize[0]][broadSize[1]];
        for(int i = 0; i< broadSize[0]; i++)
        {
            for(int j=0; j<broadSize[1]; j++)
            {
                board[i][j] = false;
            }
        }

        System.out.println("Dear president, please enter the cell's indexes.");




    }

    public static void main(String[] args) throws IOException {
        String path = args[0];
        scanner = new Scanner(new File(path));
        int numberOfGames = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= numberOfGames; i++) {
            System.out.println("Game number " + i + " starts.");
            theStudentsGame();
            System.out.println("Game number " + i + " ended.");
            System.out.println("-----------------------------------------------");
        }
        System.out.println("All games are ended.");
    }
}
//iiiill
//jjjjj