///
//Name:      Le, Nu
//Homework:  1
//Due:       11/14/2019
//Course:    cs-2400-01-f19

//Description:  A program that reads in user's input and build a binary search tree
//based upon these input. Then it displays the tree in pre-,post-,in- order format.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class BSTApp
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader =
                   new BufferedReader(new InputStreamReader(System.in));
        BSTInterface<Integer> bst = new BinarySearchTree<>();
        
        System.out.println("N. Le's BST");
        System.out.println("Please enter the initial sequence of values: ");
        
        String numbers = reader.readLine();
        String[] numberArray = numbers.trim().split(" ");
        
        for(int i = 0; i < numberArray.length; i++)
            bst.add(Integer.parseInt(numberArray[i]));
        
        display(bst);
       
        System.out.println("A value -- add a value");
        System.out.println("E -- exit the program");
        
       do
       {
            System.out.println("Command? ");
            BufferedReader commandReader =
                   new BufferedReader(new InputStreamReader(System.in));
            String commandLine = commandReader.readLine();
            String[] userCommand = commandLine.trim().split(" ");
            if(userCommand[0].equals("A"))
            {
                if(!bst.contains(Integer.parseInt(userCommand[1])))
                {
                    bst.add(Integer.parseInt(userCommand[1]));
                    bst.printInOrder();
                    System.out.println();
                }
                else
                    System.out.println(userCommand[1] + " exists, ignore.");
            }
            else 
                System.exit(0);
        }while(true);     
    }
    
    public static void display(BSTInterface<Integer> bst)
    {
        bst.printPreOrder();  
        System.out.println();
        bst.printInOrder(); 
        System.out.println();
        bst.printPostOrder(); 
        System.out.println();
    }
}