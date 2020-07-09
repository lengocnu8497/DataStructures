// Nu Le
// CS 3310
// Final Project 
// 4/28/2020

import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

public class Driver {
    public static final int BOARD_SIZE = 9;
    public static final int INF = Integer.MAX_VALUE;
    public static void main(String args[])
    {
       Scanner userinput = new Scanner(System.in);
       String choice ;
       // A list of selections for required algorithms
       do 
       {
            System.out.println("\nChoose a number associated with the task");
            System.out.println("1. Task #1 - Sudoku Puzzle Solver");
            System.out.println("2. Task #2 - 0/1 Knapsack Problem ");
            System.out.println("3. Task #3 - Traveling Salesman Problem ");
            System.out.println("4. Exit");
            System.out.print("Your choice: ");
            choice = userinput.nextLine();

            switch(choice) 
            {
                case "1":
                    SudokuSolver();
                    break;
                case "2":
                    KnapsackProblem();
                    break;
                case "3":
                    TSP();
                    break;
                case "4":
                    System.exit(0);
            }
        }while(true); 
    }
    
    public static void SudokuSolver()
    {
        int[][] grid = new int[BOARD_SIZE][BOARD_SIZE];
        /////////////////////////////
        // Read from input.txt file.
        /////////////////////////////
        Scanner stream = null;
        try{
            stream = new Scanner(new File("input1.txt")); 
        } 
        // Handles exception if file is either not found or corrupt.
        catch(IOException e) 
        {
            System.err.println(e.getMessage());
        }
        
        while(stream.hasNextLine())
        {
            for(int r = 0; r < BOARD_SIZE; r++)
            {
                String[] line = stream.nextLine().trim().split(" ");
                for(int c = 0; c < line.length; c++)
                    grid[r][c] = Integer.parseInt(line[c]);
            }
        }
        
        if(SudokuSolved(grid))
        {
            // DIsplay grid
            System.out.println("Program is reading from input1.txt file.");
            System.out.println("Grid is solved.");
            for(int r = 0; r < BOARD_SIZE; r++)
            {
               for(int c = 0; c < BOARD_SIZE; c++)
                   System.out.print(grid[r][c] + " ");
               System.out.println();
            }
        }
        else
            System.out.println("The board is npt solvable.");
               
    }
    
    public static boolean SudokuSolved(int[][] grid)
    {
        boolean isSolved = true;
        // Get empty cell
        int r=0 ,row=0, column = 0;
        while(r < BOARD_SIZE && row == 0)
        {
           int c = 0;
           while(c < BOARD_SIZE && column == 0)
           {
               if(grid[r][c] == 0)
               {
                   row = r;
                   column = c;
                   isSolved = false;
               }
               c++;
           }
           r++;
        }
        
        // Base case.
        if(isSolved)
            return true;
        
        // find number from 1 - 9 to solve the grid
        for(int number = 1; number <= BOARD_SIZE; number++)
        {
            // explore
            if(isPromising(grid,row,column,number))
            {
                grid[row][column]=number; // choose
                if(SudokuSolved(grid))
                    return true;
                // pruning
                else
                    grid[row][column]=0; // unchoose
            }
           
        }
               
        return false;
    }
    
    public static boolean isPromising(int[][] grid,int row, int column, int number)
    {
        // Check if number is unique in it's row.
        for(int c = 0; c < BOARD_SIZE; c++ )
            if(grid[row][c] == number)
                return false;
        
        // Check if number is unique in it's column.
        for(int r = 0; r < BOARD_SIZE; r++ )
            if(grid[r][column] == number)
                return false;
        
        // Check if number is unique in it's own sub-matrix.
        int rowStart = row - row%3;
        int columnStart = column - column%3;
        for(int r = rowStart; r < rowStart + 3 ; r++ )
            for(int c = columnStart; c < columnStart + 3; c++ )
                    if(grid[r][c] == number)
                        return false;
        
        return true;
    }
    
    
    
    public static void KnapsackProblem()
    {
        // Get Knapsack capacity.
        Scanner readInt = new Scanner(System.in);
        System.out.print("Enter Knapsack's Capacity: ");
        int W = readInt.nextInt();
        System.out.println();
         
        // Get profits and weights
        Scanner stream = null;
        try{
            stream = new Scanner(new File("input2.txt")); 
        } 
        // Handles exception if file is either not found or corrupt.
        catch(IOException e) 
        {
            System.err.println(e.getMessage());
        }
        
        ArrayList<Integer> profit = new ArrayList<>();
        ArrayList<Integer> weight = new ArrayList<>();
        
        String[] firstLine = stream.nextLine().trim().split(" ");
        for(int i = 0; i < firstLine.length; i++)
            profit.add(Integer.parseInt(firstLine[i]));
        
        String[] secondLine = stream.nextLine().trim().split(" ");
        for(int i = 0; i < secondLine.length; i++)
            weight.add(Integer.parseInt(secondLine[i]));
        
    
        // Display results.
        System.out.println("Program is reading from input2.txt file.");
        int maxProfit = KnapSackTools.knapsack_BFS_BB(profit.size(),profit,weight,W);
        System.out.println("Max profit: " + maxProfit);
       
    }
    
    public static void TSP()
    {
        
        // Get matrix size.
        Scanner readInt = new Scanner(System.in);
        System.out.print("Enter matrix size in input3.txt: ");
        int N = readInt.nextInt();
        int[][] costMatrix = new int[N][N];
        System.out.println();
        
        // Scan in matrix from input3.txt
        Scanner stream = null;
        try{
            stream = new Scanner(new File("input3.txt")); 
        } 
        // Handles exception if file is either not found or corrupt.
        catch(IOException e) 
        {
            System.err.println(e.getMessage());
        }
        // Populate costMatrix.   
        int row = 0;
        while(stream.hasNextLine())
        {
            String[] line = stream.nextLine().trim().split(" ");
            for(int col = 0; col < line.length; col++)
            {
                if(line[col].equalsIgnoreCase("INF"))
                    costMatrix[row][col] = INF;
                else 
                    costMatrix[row][col] = Integer.parseInt(line[col]);
            }
            row++;
        }
        
        // Display original matrix.
        for(int r = 0; r < costMatrix.length; r++)
        {
            for(int col = 0; col < costMatrix.length; col++)
            {
                if(costMatrix[r][col] == INF)
                    System.out.print(" INF" );
                else 
                    System.out.print(" " + costMatrix[r][col]);
            }
            System.out.println();
        }
        
	// Display solution.
        System.out.println("Program is reading from input3.txt file.");
        System.out.println();
        System.out.println("Total cost is " +TSPTools.solve(costMatrix));
        System.out.println();
    }
}