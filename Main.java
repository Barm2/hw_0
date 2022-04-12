import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner;  // Note: Do not change this line.


    /**
     * Scans into an array the sizes of the board
     * @param broadSize Array for the number of rows and columns
     */
    public static void pregame(int[] broadSize)
    {
        char x;
        System.out.println("Dear president, please enter the board’s size.");
        broadSize[0] = scanner.nextInt();
        x = scanner.next().charAt(0); //to scan the 'X' without the numbers
        broadSize[1] = scanner.nextInt();
        String str = scanner.nextLine();//to scan the exceeded enter that's left in the buffer
    }

    /**
     * Scans desired standard students indexes and enters them to the board
     * @param board An array represents the board
     * @param rowNumber Number of rows on the board
     * @param colNumber Number of columns on the board
     */
    public static void enterStandardToBoard(boolean[][] board, int rowNumber, int colNumber)
    {
        System.out.println("Dear president, please enter the cell's indexes.");
        String str;
        int row, col;
        String[] indexes;
        while(!((str = scanner.nextLine()).equals("Yokra")))
        {
            //splits the scanned string into 2 ints
            indexes = str.split(", ");
            row = Integer.parseInt(indexes[0]);
            col = Integer.parseInt(indexes[1]);
            if(row < 0 || col < 0 || row >= rowNumber || col >= colNumber)
            {
                System.out.println("The cell is not within the board’s boundaries, enter a new cell.");
                continue;
            }
            board[row][col] = !board[row][col];
            System.out.println("Dear president, please enter the cell's indexes.");
        }
    }

    /**
     * receives a certain student index, and board measurements.
     * counts the number of academic standard friends of a student.
     * @param board An array represents the board
     * @param rowNumber Number of rows on the board
     * @param columnNumber Number of cols on the board
     * @param row The row of the student
     * @param column The col of the student
     * @return the number of standard friends a student has
     */
    public static int check_around(boolean[][] board, int rowNumber, int columnNumber, int row, int column)
    {
        int count = 0;
        //to avoid exceeding the board array boundaries
        int startingRow = Math.max(0, row-1);
        int startingCol = Math.max(0, column-1);
        int endingRow = Math.min(rowNumber-1, row+1);
        int endingColumn = Math.min(columnNumber-1, column+1);

        for(int i = startingRow; i<=endingRow; i++)
        {
            for(int j = startingCol; j<=endingColumn; j++)
            {
                if(i==row && j==column)
                {
                    continue;
                }
                if(board[i][j])
                {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     *
     * marks in a new array the indexes of students who needs to change their academic status
     * @param board An array represents the board
     * @param rowNumber Number of rows on the board
     * @param columnNumber Number of columns on the board
     * @param toChange An array represents the student who need to change their academic status
     */
    public static void studentsToChange(boolean[][] board, int rowNumber, int columnNumber,boolean[][] toChange)
    {
        int standardFriendsNumber;
        for(int i = 0; i<rowNumber; i++)
        {
            for(int j = 0; j < columnNumber; j++)
            {
                standardFriendsNumber = check_around(board, rowNumber, columnNumber, i, j);
                if(board[i][j])
                {
                    if((standardFriendsNumber==2) || (standardFriendsNumber==3))
                    {
                        toChange[i][j]= false;
                    }
                    else
                    {
                        toChange[i][j]=true;
                    }
                }
                else
                {
                    if(standardFriendsNumber == 3)
                    {
                        toChange[i][j]=true;
                    }
                    else
                    {
                        toChange[i][j]=false;
                    }
                }
            }
        }
    }

    /**
     *according to toChange array, changes the students' academic status
     * @param board An array represents the board
     * @param toChange An array represents the student who need to change their academic status
     * @param rowNumber Number of rows on the board
     * @param columnNumber Number of columns on the board
     * @return the number of students that changed their status
     */
    public static int semester(boolean[][] board, boolean[][] toChange, int rowNumber, int columnNumber)
    {
        int counter = 0;
        for(int i = 0; i < rowNumber; i++)
        {
            for(int j = 0; j < columnNumber; j++)
            {
                if (toChange[i][j])
                {
                    board[i][j] = !board[i][j];
                    counter++;
                }
            }
        }
        return counter;
    }


    /**counts the number of standard students in current semester */
    public static int countStandard(boolean[][] board, int rowNumber, int columnNumber)
    {
        int counter = 0;
        for(int i = 0; i<rowNumber; i++)
        {
            for(int j=0;j<columnNumber;j++)
            {
                if(board[i][j])
                {
                    counter++;
                }
            }
        }
        return counter;
    }

    /** print the board */
    public static void printBoard(boolean[][] board, int rowNumber, int columnNumber, int n, int s)
    {
        System.out.println("Semester Number " + n + ":");
        for(int i = 0; i<rowNumber; i++)
        {
            for (int j = 0; j < columnNumber; j++)
            {
                if (board[i][j])
                {
                    System.out.print("▮");
                }
                else
                {
                    System.out.print("▯");
                }
            }
            System.out.println();
        }
        System.out.println("Number of students: " + s);
        System.out.println();
    }

    /**
     * runs all semesters until max semester is reached
     * or there are no students who changed their status from previous semester
     * @param toChange An array represents the student who need to change their academic status
     * @param rowNumber Number of rows on the board
     * @param columnNumber Number of columns on the board
     */
    public static void gameplay(boolean[][] board, boolean[][] toChange, int rowNumber, int columnNumber)
    {
        final int MAX_SEMESTERS = 1000;
        int changedStudents = 0;
        int numOfStandards = 0;
        for(int i = 0; i< MAX_SEMESTERS; i++)
        {
            numOfStandards = countStandard(board, rowNumber, columnNumber);
            printBoard(board, rowNumber, columnNumber, i+1, numOfStandards);
            studentsToChange(board, rowNumber, columnNumber, toChange);
            changedStudents = semester(board, toChange, rowNumber, columnNumber);
            if(changedStudents == 0)
            {
                break;
            }
        }
        if(numOfStandards == 0)
        {
            System.out.println("There are no more students.");
        }
        else if (changedStudents == 0)
        {
            System.out.println("The students have stabilized.");
        }
        else
        {
            System.out.println("The semesters limitation is over");
        }
    }

    /**
     * the game
     * initiates the board and the helping array
     */
    public static void theStudentsGame() {

        int[] boardSize = new int[2];
        pregame(boardSize);//
        boolean[][] board = new boolean[boardSize[0]][boardSize[1]];
        enterStandardToBoard(board, boardSize[0], boardSize[1]);
        boolean[][] toChange = new boolean[boardSize[0]][boardSize[1]];
        gameplay(board, toChange, boardSize[0], boardSize[1]);
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






