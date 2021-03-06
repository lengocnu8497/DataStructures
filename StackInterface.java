///
//Name:      Le, Nu
//Homework:  1
//Due:       11/28/2019
//Course:    cs-2400-01-f19

//Description:  Implement a word count program that uses the BST. Output the words in alphabetical order
//preceded with their frequency
public interface StackInterface<T>{
    public void push(T item);
    public T pop();
    public T peek();
    public int size();
    public boolean isEmpty();
}