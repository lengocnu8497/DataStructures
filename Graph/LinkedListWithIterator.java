///
//Name:      Le, Nu
//Homework:  1
//Due:       12/10/2019
//Course:    cs-2400-01-f19

//Description:  A program that find the cheapest path between airports
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.IndexOutOfBoundsException;
public class LinkedListWithIterator<T> implements ListWithIteratorInterface<T> {
    private Node firstNode;
    private Node lastNode;
    private int numberOfEntries;
    
    public LinkedListWithIterator()
    {
        firstNode = null;
        lastNode = null;
        numberOfEntries = 0;
    }
    
    public void add(T newEntry)
    {
        Node newNode = new Node(newEntry,null);
        
        if(isEmpty())
            firstNode = newNode;
        else
            lastNode.setNextNode(newNode);
        lastNode = newNode;
        numberOfEntries++;
    }
    public void add(int newPosition, T newEntry)
    {
        if(newPosition >= 1 && newPosition <= numberOfEntries +1)
        {
            Node newNode = new Node(newEntry, null);
            if(isEmpty())
            {
                firstNode = newNode;
                lastNode = newNode;
            }
            else if(newPosition == 1)
            {
                newNode.setNextNode(firstNode);
                firstNode = newNode;
            }
            else if (newPosition == numberOfEntries +1)
            {
                lastNode.setNextNode(newNode);
                lastNode = newNode;
            }
            else
            {
                Node nodeBefore = getNodeAt(newPosition -1);
                Node nodeAfter = nodeBefore.getNextNode();
                newNode.setNextNode(nodeAfter);
                nodeBefore.setNextNode(newNode);
            }
                numberOfEntries++;
        }
        else
            throw new IndexOutOfBoundsException();
    }
    
    public Node<T> getNodeAt(int givenPosition)
    {
        Node currentNode = firstNode;
        for(int counter = 1; counter < givenPosition; counter++)
            currentNode = currentNode.getNextNode();
        return currentNode;
    }
    
    public boolean isEmpty()
    {
        return numberOfEntries == 0;
    }
    
    public Iterator<T> iterator()
    {
        return new IteratorForLinkedList();
    }
    
    public Iterator<T> getIterator()
    {
        return iterator();
    }
     
    private class IteratorForLinkedList<T> implements Iterator<T>
    {
        private Node<T> nextNode;
         
        private IteratorForLinkedList()
        {
            nextNode = firstNode;
        }
        
        public T next()
        {
            T result;
            if( hasNext())
            {
                result = nextNode.getData();
                nextNode = nextNode.getNextNode();
            }
            else 
                throw new NoSuchElementException();
            return result;
        }
        
        public boolean hasNext()
        {
            return nextNode != null;
        }
    }
    
    private class Node<T>
    {
        private T data;
        private Node<T> next;
        public Node(T newData, Node<T> newNext)
        {
               data = newData;
               next = newNext;
        }
        
        public T getData()
        {
            return data;
        }
        
        public Node<T> getNextNode()
        {
            return next;
        }
        
        public void setNextNode(Node<T> newNode)
        {
            next = newNode;
        }
    }
}
