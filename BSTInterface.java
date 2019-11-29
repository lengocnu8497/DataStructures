///
//Name:      Le, Nu
//Homework:  1
//Due:       11/28/2019
//Course:    cs-2400-01-f19

//Description:  Implement a word count program that uses the BST. Output the words in alphabetical order
//preceded with their frequency
import java.util.Iterator;
public interface BSTInterface<T extends Comparable<? super T>> 
extends BinaryTreeInterface<T>
{
    public boolean contains(T anEntry);
    public T getEntry(T anEntry);
    public T findEntry(BinaryNode<T> rootNode , T anEntry);
    public T add(T anEntry);
    public int getNumberOfNodes();
    public Iterator<T> getInorderIterator();   
}