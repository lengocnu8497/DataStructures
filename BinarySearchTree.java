///
//Name:      Le, Nu
//Homework:  1
//Due:       11/28/2019
//Course:    cs-2400-01-f19

//Description:  Implement a word count program that uses the BST. Output the words in alphabetical order
//preceded with their frequency
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;
public class BinarySearchTree<T extends Comparable<? super T>> extends BinaryTree<T>
        implements BSTInterface<T> {
    public BinarySearchTree()
    {
        super();
    }
    
    public BinarySearchTree(T rootEntry)
    {
        super();
        setRootNode(new BinaryNode<T>(rootEntry));
    }
    
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
            BinaryTreeInterface<T> rightTree)
    {
        throw new UnsupportedOperationException(); 
    }
    
    public int getNumberOfNodes()
    {
        return numberOfEntries;
    }
    
    public boolean contains(T anEntry)
    {
        return findEntry(getRootNode(), anEntry) != null;
    }
    public T getEntry(T anEntry)
    {
        return findEntry(getRootNode(), anEntry);
    }
    public T findEntry(BinaryNode<T> rootNode , T anEntry)
    {
        T result = null;
        if(rootNode != null)
        {
            T rootEntry = rootNode.getData();
            if(anEntry.compareTo(rootEntry) == 0)
                result = rootEntry;
            else if(anEntry.compareTo(rootEntry) < 0 )
                result = findEntry(rootNode.getLeftChild(),anEntry);
            else
                result = findEntry(rootNode.getRightChild(),anEntry );
        }
        return result;
    }
    
    public T add(T anEntry)
    {
        T result = null;
        
        if(isEmpty())
            setRootNode(new BinaryNode<>(anEntry));
        else result = addEntry(getRootNode(), anEntry);
        
        return result;
    }
    
    private T addEntry(BinaryNode<T> rootNode, T anEntry)
    {
        T result = null;
        int comparison = anEntry.compareTo(rootNode.getData());
        
        if (comparison == 0)
        {
            result = rootNode.getData();
            rootNode.setData(anEntry);
        }
        else if (comparison < 0)
        {
            if(rootNode.hasLeftChild())
                result = addEntry(rootNode.getLeftChild(), anEntry);
            else 
            {
                rootNode.setLeftChild(new BinaryNode<>(anEntry));
                numberOfEntries++;
            }
        }
        else
        {
             if(rootNode.hasRightChild())
                result = addEntry(rootNode.getRightChild(), anEntry);
              else 
             {
                 rootNode.setRightChild(new BinaryNode<>(anEntry));
                 numberOfEntries++;
             }
        }
        return result;
    }
    
    public Iterator<T> getInorderIterator()
    {
        return new InorderIterator();
    }
    
    private class InorderIterator implements Iterator<T>
    {
        private StackInterface<BinaryNode<T>> nodeStack;
        private BinaryNode<T> currentNode;
        
        public InorderIterator()
        {
            nodeStack = new LinkedStack<>();
            currentNode = getRootNode();
        }
        
        public boolean hasNext()
        {
            return !nodeStack.isEmpty() || currentNode != null;
        }
        
        public T next()
        {
            BinaryNode<T> nextNode = null;
            
            while(currentNode != null)
            {
                nodeStack.push(currentNode);
                currentNode = currentNode.getLeftChild();
            }
            
            if(!nodeStack.isEmpty())
            {
                nextNode = nodeStack.pop();
                currentNode = nextNode.getRightChild();
            }
            
            else
                throw new NoSuchElementException();
            
            return nextNode.getData();
        }
        
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }
}

