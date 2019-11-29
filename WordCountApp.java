///
//Name:      Le, Nu
//Homework:  1
//Due:       11/28/2019
//Course:    cs-2400-01-f19

//Description:  Implement a word count program that uses the BST. Output the words in alphabetical order
//preceded with their frequency
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.util.Iterator;
public class WordCountApp {
    public static void main(String[] args) throws IOException
    {
       Scanner stream = new Scanner(new File("usconst.txt")); 
       BSTInterface<Word> bst = new BinarySearchTree<Word>();
       
       // display result from my hash function
       int totalWordCount = populateTree(stream,bst);
       
      
       System.out.println("Word Count by Nu Le");
       
       System.out.println("Word count: " + totalWordCount);
       System.out.println("Unique word count: " + bst.getNumberOfNodes());
     
       printInorder(bst);
       
    }
    
    public static void printInorder(BSTInterface<Word> bst)
    {
         Iterator<Word> iterator = bst.getInorderIterator();
         System.out.println("Freq  Word");
         while(iterator.hasNext())
         {
             Word word = iterator.next();
             System.out.printf("%3d",word.getCount());
             System.out.print("   ");
             System.out.printf("%3s",word.getWord());
             System.out.println();       
         }
    }
    
    public static int populateTree(Scanner stream, BSTInterface<Word> bst)
    {
        int totalWordCount = 0;
        while(stream.hasNext())
        {
                String word = stream.next();
                totalWordCount++;
                if(word.trim().equals(""))
                    continue;
                String processed = word.toLowerCase();
                Word newWord = new Word(processed);
                if(bst.contains(newWord))
                {
                    int newCount = 1 + bst.getEntry(newWord).getCount();
                    bst.getEntry(newWord).setCount(newCount);
                }
                else bst.add(newWord);
        }       
        return totalWordCount;
    }
}
