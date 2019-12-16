///
//Name:      Le, Nu
//Homework:  1
//Due:       12/10/2019
//Course:    cs-2400-01-f19

//Description:  A program that find the cheapest path between airports
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class AirportApp {
    public static void main(String[] args) throws FileNotFoundException {
       //store airport info corresponding to airport's code
       Hashtable<String,String> airPortsTable = new Hashtable<>();
       
       //connect distances between airport
       DigraphInterface<String> digraph = new Digraph<>();
       connectAirports(digraph);
       
       
       Scanner scan = new Scanner(new File("airports.csv"));
       while(scan.hasNextLine())
       {
           String aLine = scan.nextLine();
           String[] words = aLine.split(",");
           airPortsTable.put(words[0],words[1]);
       }

       
       System.out.println("Airports by N.Le");
       
       Scanner userinput = new Scanner(System.in);
       String choice ;

       do 
       {
            System.out.println("\nCommand? ");
            choice = userinput.nextLine();

            switch(choice) 
            {
                case "Q":
                    queryInfo(airPortsTable, userinput);
                    break;
                case "H":
                    displayMenu();
                    break;
                case "D":
                    findDistance(digraph,userinput,airPortsTable);
                    break;
                case "I":
                    insertConnection(digraph,userinput,airPortsTable);
                    break;
                case "R":
                    removeConnection(digraph,userinput,airPortsTable);
                    break;
                case "E":
                    System.exit(0);
            }
        }while(true);      
    }
    
    public static void removeConnection(DigraphInterface<String> digraph,Scanner userinput,
            Hashtable<String,String> airPortsTable)
    {
        System.out.println("Airport codes: ");
        
        String codes = userinput.nextLine(); // airport codes
        //extract each code
        String[] code = codes.split(" ");
        digraph.addEdge(code[0],code[1],0);
        
        System.out.println("The connection from " + airPortsTable.get(code[0])
                + " to " + airPortsTable.get(code[1]) + " removed.");   
    }
    
    public static void insertConnection(DigraphInterface<String> digraph,Scanner userinput,
            Hashtable<String,String> airPortsTable)
    {
        System.out.println("Airport codes and distance: ");
        
        String aLine = userinput.nextLine();
        String[] data = aLine.split("\\s",3);
        digraph.addVertex(data[0]);
        digraph.addEdge(data[0],data[1],Double.parseDouble(data[2]));
        
        System.out.println("You have insert a connection from " + airPortsTable.get(data[0])
                + " to " + airPortsTable.get(data[1]) + " with a distance of " + data[2] +".");   
    }
    
    public static void findDistance(DigraphInterface<String> digraph,Scanner userinput,
            Hashtable<String,String> airPortsTable)
    {
        //stack to hold shortest path
        Stack<String> path = new Stack<>();
        
        System.out.println("Airport codes: ");
        
        String codes = userinput.nextLine(); // airport codes
        //extract each code
        String[] code = codes.split(" ");
        //get path cost
        double pathCost = digraph.getCheapestPath(code[0],code[1], path);
        
        System.out.println("The minimum distance between " + airPortsTable.get(code[0])
                + " and " + airPortsTable.get(code[1]) + " is " + pathCost + " through "
                        + "the route:");
        while(!path.isEmpty())
            System.out.println(path.pop());    
    }
    
    public static void queryInfo(Hashtable<String,String> airPortsTable, Scanner userinput)
    {
        System.out.println("Airport code: ");
        String code = userinput.nextLine(); // airport code
        //display airport info
        System.out.println(airPortsTable.get(code));    
    }
    
    public static void displayMenu()
    {
        System.out.println("Q Query the airport information by entering the airport code.");
        System.out.println("D Find the minimum distance between two airports.");
        System.out.println("I Insert a connection by entering two airport codes and distance.");
        System.out.println("R Remove an existing connection by entering two airport codes.");
        System.out.println("H Display this message.");
    }
    
    public static void connectAirports(DigraphInterface<String> digraph)throws FileNotFoundException
    {
       Scanner scan = new Scanner(new File("distances.csv"));
       while(scan.hasNextLine())
       {
           String aLine = scan.nextLine();
           String[] data = aLine.split(",");
           digraph.addVertex(data[0]);
           digraph.addEdge(data[0],data[1],Double.parseDouble(data[2]));
       }
    }
}

