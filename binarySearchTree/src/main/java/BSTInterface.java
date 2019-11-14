///
//Name:      Le, Nu
//Homework:  1
//Due:       11/14/2019
//Course:    cs-2400-01-f19

//Description:  A program that reads in user's input and build a binary search tree
//based upon these input. Then it displays the tree in pre-,post-,in- order format.
import java.util.Iterator;
public interface BSTInterface<T extends Comparable<? super T>> 
extends BinaryTreeInterface<T>
{
    public boolean contains(T anEntry);
    public T getEntry(T anEntry);
    public T add(T anEntry);
//    public T remove(T anEntry);
//    public Iterator<T> getInorderIterator();
    public void printPreOrder();
    public void printInOrder();
    public void printPostOrder();
    
}
