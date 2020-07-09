// Nu Le
// FInal Project
// TSP Helper Class
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
public class TSPTools {
    public static final int INF = Integer.MAX_VALUE;
    // State Space Tree nodes
    public static class Node
    {
        // stores edges of state space tree
        // helps in tracing path when answer is found
        ArrayList<Pair> path = new ArrayList<Pair>();

        // stores the reduced matrix
        int[][] reducedMatrix;

        // stores the lower bound
        int cost;

        //stores current city number
        int vertex;

        // stores number of cities visited so far
        int level;
    }
    
    public static class Pair
    {
        public int first;
        public int second;
        
        public Pair(int first, int second)
        {
            this.first = first;
            this.second = second;
        }

    }
    
    // Function to allocate a new node (i, j) corresponds to visiting
    // city j from city i
    public static Node newNode(int[][] parentMatrix, ArrayList<Pair> path,
                            int level, int i, int j)
    {
        Node node = new Node();

        // copy data from parent node to current node
        // Store a deep copy of the root reduced matrix to trace cost of edges
        // between cities in later branching process.
        int[][] parentReducedMatrix = new 
                int[parentMatrix.length][parentMatrix.length];
        for(int r = 0; r < parentMatrix.length; r++)
            for(int c = 0; c < parentMatrix.length; c++)
                parentReducedMatrix[r][c] = parentMatrix[r][c];
        node.reducedMatrix = parentReducedMatrix;
        
        // stores ancestors edges of state space tree
        for(int c = 0; c < path.size(); c++)
                node.path.add(path.get(c));
      
        // skip for root node
        if (level != 0)
            // add current edge to path
            node.path.add(new Pair(i, j));
        
        // Change all entries of row i and column j to infinity
        // skip for root node
        for (int k = 0; level != 0 && k < parentMatrix.length; k++)
        {
                // set outgoing edges for city i to infinity
                node.reducedMatrix[i][k] = INF;

                // set incoming edges to city j to infinity
                node.reducedMatrix[k][j] = INF;
        }

        // Set (j, 0) to infinity
        // here start node is 0
        node.reducedMatrix[j][0] = INF;

        // set number of cities visited so far
        node.level = level;

        // assign current city number
        node.vertex = j;

        // return node
        return node;
    }
    
    // Get min cost for each row
    public static int[] rowMinCost(int[][] reducedMatrix)
    {
        int[] row = new int[reducedMatrix.length];
        // initialize row array to INF
        for(int i = 0; i < reducedMatrix.length; i++)
            row[i] = INF;

        // row[i] contains minimum in row i
        for (int i = 0; i < reducedMatrix.length; i++)
                for (int j = 0; j < reducedMatrix.length; j++)
                        if (reducedMatrix[i][j] < row[i])
                                row[i] = reducedMatrix[i][j];
        return row;
    }

    // Function to reduce each row in such a way that
    // there must be at least one zero in each row
    public static int[][] rowReduction(int[][] reducedMatrix, int[] row)
    {
      
        // reduce the minimum value from each element in each row
        for (int i = 0; i < reducedMatrix.length; i++)
                for (int j = 0; j < reducedMatrix.length; j++)
                        if (reducedMatrix[i][j] != INF && row[i] != INF)
                                reducedMatrix[i][j] -= row[i];

        return reducedMatrix;
    }

    // Get min cost for each column
    public static int[] colMinCost(int[][] reducedMatrix)
    {
        int[] col = new int[reducedMatrix.length];
        // initialize col array to INF
        for(int i = 0; i < reducedMatrix.length; i++)
            col[i] = INF;

        // col[j] contains minimum in col j
        for (int i = 0; i < reducedMatrix.length; i++)
                for (int j = 0; j < reducedMatrix.length; j++)
                        if (reducedMatrix[i][j] < col[j])
                                col[j] = reducedMatrix[i][j];
        
        return col;
    }
    
    // Function to reduce each column in such a way that
    // there must be at least one zero in each column
    public static int[][] columnReduction(int[][] reducedMatrix, int[] col)
    {
        // reduce the minimum value from each element in each column
        for (int i = 0; i < reducedMatrix.length; i++)
                for (int j = 0; j < reducedMatrix.length; j++)
                        if (reducedMatrix[i][j] != INF && col[j] != INF)
                                reducedMatrix[i][j] -= col[j];

        return reducedMatrix;
    }

    // Function to get the lower bound on
    // on the path starting at current min node
    public static int calculateCost(int[][] reducedMatrix)
    {
            // initialize cost to 0
            int cost = 0;

            // Row Reduction
            int[] row = rowMinCost(reducedMatrix);
            rowReduction(reducedMatrix, row);

            // Column Reduction
            int[] col = colMinCost(reducedMatrix);
            columnReduction(reducedMatrix, col);

            // the total expected cost
            // is the sum of all reductions
            for (int i = 0; i < reducedMatrix.length; i++)
            {
                    cost += (row[i] != INF) ? row[i] : 0;
                    cost += (col[i] != INF) ? col[i] : 0;
            }

            return cost;
    }

    // print list of cities visited following least cost
    public static void printPath(ArrayList<Pair> path)
    {
            for (int i = 0; i < path.size(); i++)
                    System.out.println((path.get(i).first + 1) + " -> " 
                            + (path.get(i).second + 1) ); 
    }


    // Function to solve Traveling Salesman Problem using Branch and Bound
    public static int solve(int[][] costMatrix)
    {
        int minCost = 0;
        // Create a priority queue to store live nodes of search tree;
        PriorityQueue<Node> PQ = new PriorityQueue<Node>(costMatrix.length, new Comparator<Node>()
        {
            @Override
            public int compare(Node o1, Node o2)
            {
                if(o1.cost < o2.cost)
                    return -1;
                if(o2.cost < o1.cost)
                    return 1;
                return 0;
                
            }
        });

        ArrayList<Pair> path = new ArrayList<>();

        // create a root node and calculate its cost
        // The TSP starts from first city i.e. node 0
        Node root = newNode(costMatrix, path, 0, -1, 0);

        // get the lower bound of the path starting at node 0
        root.cost = calculateCost(root.reducedMatrix);
        

        // Add root to list of live nodes;
        PQ.add(root);

        // Finds a live node with least cost, add its children to list of
        // live nodes and finally deletes it from the list
        while (!PQ.isEmpty())
        {
            // Find a live node with least estimated cost
            // The found node is deleted from the list of live nodes
            Node min = PQ.remove();

            // i stores current city number
            int i = min.vertex;

            // if all cities are visited
            if (min.level == min.reducedMatrix.length - 1)
            {
                    // return to starting city
                    min.path.add(new Pair(i,0));
                    // print list of cities visited;
                    printPath(min.path);

                    // return optimal cost
                    minCost = min.cost;
                    return minCost;
            }

            // do for each child of min
            // (i, j) forms an edge in space tree
         
            for (int j = 0; j < min.reducedMatrix.length; j++)
            {
                    if (min.reducedMatrix[i][j] != INF)
                    {
                            // create a child node and calculate its cost
                            Node child = newNode(min.reducedMatrix, min.path,
                                    min.level + 1, i, j);
                            
                            printMatrix(min.reducedMatrix,child.reducedMatrix);
                            /* Cost of the child =
                                    cost of parent node +
                                    cost of the edge(i, j) +
                                    lower bound of the path starting at node j
                            */
                            child.cost = min.cost + min.reducedMatrix[i][j]
                                                    + calculateCost(child.reducedMatrix);
                            
                            System.out.println("Child cost is " + child.cost);
                            System.out.println("//////////////////////////////////////////////");
                            // Add child to list of live nodes
                            PQ.add(child);
                            updatePQ(PQ);}
                    }
            }       
        
        return minCost;
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
                if(o1.cost < o2.cost)
                    return 1;
                if(o1.cost > o2.cost)
                    return -1;
                return 0;
            }
        }
        );
        PQ.clear();
        for(int i = 0; i < temp.length; i++)
            PQ.add(nodeArray[i]); 
        
    }
    
    public static void printMatrix(int[][] parentMatrix, int[][] childMatrix )
    {
        ////////Display matrix for updating purpose////////////
        System.out.println("Parent Matrix ");
        for(int r = 0; r < parentMatrix.length; r++)
        { 
           for(int col = 0; col < parentMatrix.length; col++)
           {
               if(parentMatrix[r][col] == INF)
                   System.out.printf("%4s","INF" );
               else 
                   System.out.printf("%4d" , parentMatrix[r][col]);
           }
           System.out.println();
        }
        System.out.println("Child Matrix ");
        for(int r = 0; r < childMatrix.length; r++)
        {
           for(int col = 0; col < childMatrix.length; col++)
           {
               if(childMatrix[r][col] == INF)
                   System.out.printf("%4s","INF" );
               else 
                   System.out.printf("%4d", childMatrix[r][col]);
           }
           System.out.println();
        }
    }
}
