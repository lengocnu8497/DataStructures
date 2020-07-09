// Nu Le
// Final Project
// Knapsack Helper Class
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator; 
public class KnapSackTools {
    
    public static int bound(int n, ArrayList<Integer> profit, ArrayList<Integer> weight,
            int W, Node node)
    {
       int result = 0 ;
       int totalWeight = 0, j = 0, k = 0;
       
       // Return 0 for bound if the node is nonpromising.
       if(node.weight >= W)
           return 0;
       else
       {
           result =  node.profit;
           j = node.level + 1;
           totalWeight = node.weight;
           while( j < n && (totalWeight + weight.get(j) <= W))
           {
               // Grab as many items as possible.
               totalWeight = totalWeight + weight.get(j);
               result = result + profit.get(j);
               j++;
           }
       }
       // Use k for consistency with formula in text.
       k = j;
       // Grab fraction of kth item.
       if( k < n )
           result = result + (W - totalWeight)*(profit.get(k)/weight.get(k));
       return result; 
    }
    
    public static int knapsack_BFS_BB(int n, ArrayList<Integer> profit, ArrayList<Integer> weight, int W)
    {
        // Sort the objects based on profit per unit weight
        FractionItem[] objectArray = sortObject(n, profit,weight);
        profit.clear();
        weight.clear();
        for(int i = 0; i < n; i++)
        {
            profit.add((int)objectArray[i].profit);
            weight.add((int)objectArray[i].weight);
        }
              
        // Modity PQ so that it compares based on node bound values.
        PriorityQueue<Node> PQ = new PriorityQueue<Node>(n, new Comparator<Node>()
        {
            @Override
            public int compare(Node o1, Node o2)
            {
                if(o1.bound < o2.bound)
                    return 1;
                if(o1.bound > o2.bound)
                    return -1;
                return 0;
            }
        });
        
        Node v = new Node();
        
        // Init root node.
        v.level = -1; v.profit = 0; v.weight = 0; 
        // Init maxProfit.
        int maxProfit = 0;
        // Init root bound.
        v.bound = bound(n, profit, weight, W , v);
        // Init PQ with root node
        PQ.add(v);
        // Print PQ for updated node.
        printPQ(PQ);
        
        while (!PQ.isEmpty())
        {
            v = PQ.remove();
            // Check if node is still promising
            if (v.bound > maxProfit)
            { 
                Node u = new Node();
                Node q = new Node();
                // Set u to the child
                u.level = v.level + 1; 
                //that includes the
                u.weight = v.weight + weight.get(u.level); 
                // next item.
                u.profit = v.profit + profit.get(u.level); 
            
                if (u.weight <= W && u.profit > maxProfit)
                    maxProfit = u.profit;
                // Get u bound
                u.bound = bound(n, profit, weight, W ,u);
                // Insert child if promising
                if (u.bound > maxProfit)
                    PQ.add(u);
                updatePQ(PQ);
                if(!PQ.isEmpty())
                    printPQ(PQ);

                // Not getting the child decision
                q.level = v.level + 1;
                q.weight = v.weight; // Set u to the child
                q.profit = v.profit; // that does not include
                q.bound = bound(n, profit, weight, W , q); // the next item.
                // Insert not the child decision
                if (q.bound > maxProfit)
                    PQ.add(q);
                updatePQ(PQ);
                if(!PQ.isEmpty())
                    printPQ(PQ);
            }
        }
        
        printKnapSack(W, weight, profit, n);
        
        return maxProfit;    
    } 
    
    public static FractionItem[] sortObject(int n, ArrayList<Integer> profit, ArrayList<Integer> weight)
    {
         FractionItem[] array = new FractionItem[n];
        
        // init FractionItem Array and x
        for(int i = 0; i < n; i++)
            array[i] = new FractionItem(weight.get(i), profit.get(i), i );
   
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
        for (int i = 0; i < n / 2; i++) { 
            FractionItem t = array[i]; 
            array[i] = array[n - i - 1]; 
            array[n - i - 1] = t; 
        } 

        
        return array;
    }
    
    public static void updatePQ(PriorityQueue<Node> PQ)
    {
        Object[] temp =  PQ.toArray();
        Node[] nodeArray = new Node[temp.length];
        for(int i = 0; i < nodeArray.length; i++)
            nodeArray[i] = (Node) temp[i];
        Arrays.sort(nodeArray, new Comparator<Node>()
        {
            @Override
            public int compare(Node o1, Node o2)
            {
                ///return o1.bound.compareTo(o2.bound);
                if(o1.bound < o2.bound)
                    return 1;
                if(o1.bound > o2.bound)
                    return -1;
                return 0;
            }
        }
        );
        PQ.clear();
        for(int i = 0; i < temp.length; i++)
            PQ.add(nodeArray[i]); 
        
    }
    
    public static void printPQ(PriorityQueue<Node> PQ)
    {
        System.out.println("//////////////////////Updated Priority Queue/////////////////////////////");
        
        Object[] temp =  PQ.toArray();
        Node[] nodeArray = new Node[temp.length];
        for(int i = 0; i < nodeArray.length; i++)
        {
            nodeArray[i] = (Node) temp[i];
            System.out.print(nodeArray[i].toString());
            System.out.println();
        }
    }
    
    public static class FractionItem
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
    
    public static class Node 
    {
        int level,weight, profit;
        int bound;
        
        @Override
        public String toString() 
        {
            return "Node: " +
                    "profit=" + profit +
                    ", weight=" + weight +
                    ", bound=" + bound +
                    "\n";
        }
    }
    
    public static void printKnapSack(int W, ArrayList<Integer> weight, 
            ArrayList<Integer> profit, int n)
    {
        int i, w; 
        int K[][] = new int[n + 1][W + 1]; 
  
        // Build table K[][] in bottom up manner 
        for (i = 0; i <= n; i++) { 
            for (w = 0; w <= W; w++) { 
                if (i == 0 || w == 0) 
                    K[i][w] = 0; 
                else if (weight.get(i-1) <= w) 
                    K[i][w] = Math.max(profit.get(i - 1) +  
                              K[i - 1][w - weight.get(i-1)], K[i - 1][w]); 
                else
                    K[i][w] = K[i - 1][w]; 
            } 
        } 
  
        // stores the result of Knapsack 
        int result = K[n][W]; 
  
        w = W; 
        System.out.println("Optimal set: ");
        for (i = n; i > 0 && result > 0; i--) { 
  
            // either the result comes from the top 
            // (K[i-1][w]) or from (val[i-1] + K[i-1] 
            // [w-wt[i-1]]) as in Knapsack table. If 
            // it comes from the latter one/ it means 
            // the item is included. 
            if (result == K[i - 1][w]) 
                continue; 
            else { 
  
                // This item is included. 
                System.out.print("Object " + i + " "); 
  
                // Since this weight is included its 
                // value is deducted 
                result = result - profit.get(i-1); 
                w = w - weight.get(i-1); 
            } 
        } 
        
        System.out.println();
    }
}
