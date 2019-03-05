import java.io.*;
import java.util.*;

class Solution {
    public static int[][] gameOfLife(int[][] boardCopy) {
        
        int[][] board = new int[boardCopy.length][boardCopy[0].length];
        for(int i=0; i<boardCopy.length; i++) {
            board[i] = Arrays.copyOf(boardCopy[i], boardCopy[i].length);
        }
        
        int neighLiveCount;
        for(int i=0; i<board.length; i++) {                 // each row
            for(int j=0; j<board[0].length; j++) {          // each column
                neighLiveCount = 0;                         // reset Live neigh = 0
                for(int p=-1; p<=1; p++) {                  // row-offet (-1,0,1)
                    for(int q=-1; q<=1; q++) {              // col-offset (-1,0,1)
                        if((i+p <0) ||                      // if row offset less than UPPER boundary
                           (i+p > board.length-1) ||        // if row offset more than LOWER boundary
                           (j+q <0) ||                      // if column offset less than LEFT boundary
                           (j+q > board[i].length-1))       // if column offset more than RIGHT boundary
                            continue;                       // skip the neighbour (if not, then ArrayIndexOutOfBounds Excp)
                        neighLiveCount+= board[i+p][j+q];   // incremeent live counter for each live neighbour
                    }
                }
                neighLiveCount-= board[i][j];               // remove self count
                
                // System.out.println("\ni: " + i + "\t\tj: " + j + "\t\tneighCount: " + neighLiveCount);
                switch(neighLiveCount) {
                    case 0:
                    case 1:
                        // System.out.println("Less than 2 neigh alive, setting this to dead!");
                        boardCopy[i][j] = 0;
                        break;
                    
                    case 2:
                        // System.out.println("Exactly 2 neigh alive, state stays same!");
                        boardCopy[i][j] = board[i][j];
                        break;
                    
                    case 3:
                        // System.out.println("Exactly 3 neigh alive, setting this to Live!\n");
                        boardCopy[i][j] = 1;
                        break;
                    
                    default:
                        // System.out.println("More than 3 neigh alive, setting this to dead!");
                        boardCopy[i][j] = 0;
                }
            }
        }
        return boardCopy;
    }
    
    public static void main(String[] args) {
        // Intiliazing the grid.
        int[][] grid = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 1, 1, 1, 0, 0, 0, 0 },
            { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
        };
        
        System.out.println("Original Generation");
        printGrid(grid);
        
        grid = gameOfLife(grid);
        
        System.out.println("Next Generation");
        printGrid(grid);
        
    }
    
    private static void printGrid(int[][] grid) {
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[0].length; j++)
            {
                if (grid[i][j] == 0)
                    System.out.print("   ");
                else
                    System.out.print(" X ");
            }
            System.out.println();
        }
        System.out.println();
    }
}