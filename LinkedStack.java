///
//Name:      Le, Nu
//Homework:  1
//Due:       11/28/2019
//Course:    cs-2400-01-f19

//Description:  Implement a word count program that uses the BST. Output the words in alphabetical order
//preceded with their frequency
import java.util.LinkedList;

public class LinkedStack<T> implements StackInterface<T>{
    private LinkedList<T> list = new LinkedList<T>();

    public void push(T item) {list.addFirst(item);}
    public T pop() {return list.removeFirst();}
    public T peek() {return list.getFirst();}
    public int size() {return list.size();}
    public boolean isEmpty() {return list.isEmpty();}
}
