
// Nu Le
// CS 3310
// Project #2
// 4/24/2020
import java.util.Arrays; 
import java.util.Comparator; 
import java.util.Scanner;

public class KnapsackDriver {
    public static void main(String arg[])
    {
       Scanner userinput = new Scanner(System.in);
       String choice ;
       // A list of selections for required algorithms
       do 
       {
            System.out.println("\nChoose a number associated with the algorithm");
            System.out.println("1. Greedy Approach Fractional Knapsack");
            System.out.println("2. Dynamic Approach 0/1 Knapsack ");
            System.out.println("3. Exit");
            System.out.print("Your choice: ");
            choice = userinput.nextLine();

            switch(choice) 
            {
                case "1":
                    firstSelection();
                    break;
                case "2":
                    secondSelection();
                    break;
                case "3":
                    System.exit(0);
            }
        }while(true); 
        
       
    }
    
    public static void firstSelection()
    {
        Scanner userinput = new Scanner(System.in);
        // init weight and profit and number of objects
        int size = 0;
        int capacity = 0;
        double totalWeight = 0;
        
        
        // ask for user input        
        System.out.print("Knapsack Capacity: ");
        capacity = userinput.nextInt();
        
        System.out.print("Number of objects: ");
        size = userinput.nextInt();
        double[] weight = new double[size];
        double[] profit = new double[size];
        
        
        System.out.println("Enter your weight and profit accordingly.");
        for(int i = 0; i < size; i++)
        {
            System.out.print("Weight " + (i+1) + ": ");
            weight[i]= userinput.nextDouble();
            System.out.print("Profit " + (i+1) + ": ");
            profit[i]= userinput.nextDouble();
            System.out.println();
        }
        
        // profit per unit weight array
        double[] x = new double[size];
        
        FractionItem[] array = new FractionItem[size];
        
        // init FractionItem Array and x
        for(int i = 0; i < size; i++)
        {
            array[i] = new FractionItem(weight[i], profit[i], i );
            x[i] = 0;
        }
        // sort profit per unit weight array in increasing order
        Arrays.sort(array, new Comparator<FractionItem>()
        {
            @Override
            public int compare(FractionItem o1, FractionItem o2)
            {
                // compare profit per unit weight
                return o1.ppuw.compareTo(o2.ppuw);
            }
        }
        );
        // reverse the array
        for (int i = 0; i < size / 2; i++) { 
            FractionItem t = array[i]; 
            array[i] = array[size - i - 1]; 
            array[size - i - 1] = t; 
        } 
        
       
        int i = 0;
        while(totalWeight < capacity)
        {
            // if total weight is still less than capacity, take
            // item as a whole
            if((totalWeight + array[i].weight) <= capacity)
            {
                x[i] = 1;
                totalWeight += array[i].weight;
            }
            // otherwise, take only a fraction of the item
            else
            {
                x[i] = (capacity - totalWeight)/array[i].weight;
                totalWeight = capacity;
            }
            i++;
        }
        
        // Display max profit and objects
        double maxProfit = 0;
        System.out.print("We need ");
        for(int j = 0; j < x.length ; j++)
        {
            System.out.print(" " + x[j] + " object " + (array[j].index + 1) + "," );
            maxProfit += array[j].profit * x[j];
        }
        System.out.println(" Max profit: " + maxProfit);
    }
    
    static class FractionItem
    {
        Double ppuw; // profit per unit weight
        double weight, profit;
        int index;
        
        public FractionItem(double weight, double profit, int index )
        {
            this.weight = weight;
            this.profit = profit;
            this.index = index;
            this.ppuw = new Double(profit/weight);
        }
    }
    
    public static void secondSelection()
    {
        Scanner userinput = new Scanner(System.in);
        int size = 0;
        int capacity = 0;       
        
        // ask for user input        
        System.out.print("Knapsack Capacity: ");
        capacity = userinput.nextInt();
        
        System.out.print("Number of objects: ");
        size = userinput.nextInt();
        int[] weight = new int[++size];
        int[] profit = new int[size];
        
        // init weight and profit
        System.out.println("Enter your weight and profit accordingly.");
        for(int i = 1; i < size; i++)
        {
            System.out.print("Weight " + i + ": ");
            weight[i]= userinput.nextInt();
            System.out.print("Profit " + i + ": ");
            profit[i]= userinput.nextInt();
            System.out.println();
        }
        
        // declare and init our weight and profit 2d array
        int[][] V = new int[size][++capacity];
        
        for(int i = 0; i < size; i++)
            V[i][0] = 0;
        for(int i = 0; i < capacity; i++)
            V[0][i] = 0;
        
        // populate V with appropriate profit 
        for(int object = 1; object < size; object++)
            for(int weightSoFar = 1; weightSoFar < capacity; weightSoFar++)
            {
                // if the object's weight is larger than weight allowed so far
                // V[object][weightSoFar] is assigned with the profit in the
                // previous row
                if(weight[object] > weightSoFar)
                    V[object][weightSoFar] = V[object-1][weightSoFar];
                // otherwise, compare the profit in the previous row with
                // the sum profit of current object and appropriate previous
                // object
                else
                    V[object][weightSoFar] = Math.max(V[object-1][weightSoFar],
                            profit[object] + V[object-1][weightSoFar-weight[object]]);
            }
        
        int maxProfit = V[size-1][capacity-1];
        
        // display profit and optimal solution     
        System.out.println("Maximum Profit: " + maxProfit);
        System.out.print("Optimal solution is combination of object(s) ");
        
        int n = size -1, m = capacity -1;
        // within V, check whether the profit in the same column 
        // of the previous row is the same
        // if that's the case, keep going up one more row
        // otherwise, subtract current object's profit
        // from current max profit
        // repeat the process until maxProfit reaches 0
        while(maxProfit > 0)
        {
            if(V[n][m] != V[n-1][m] )
            {
                maxProfit -= profit[n];
                m -= weight[n]; 
                System.out.print(n + " ");
            }
            --n;
        }
    }
}
