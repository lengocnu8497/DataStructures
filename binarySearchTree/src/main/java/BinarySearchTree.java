///
//Name:      Le, Nu
//Homework:  1
//Due:       11/14/2019
//Course:    cs-2400-01-f19

//Description:  A program that reads in user's input and build a binary search tree
//based upon these input. Then it displays the tree in pre-,post-,in- order format.
import java.util.Iterator;
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
    public boolean contains(T anEntry)
    {
        return getEntry(anEntry) != null;
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
            if(anEntry.equals(rootEntry))
                result = rootEntry;
            else if(anEntry.compareTo(rootEntry) < 0)
                result = findEntry(rootNode.getLeftChild(),anEntry);
            else
                result = findEntry(rootNode.getRightChild(),anEntry);
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
            else rootNode.setLeftChild(new BinaryNode<>(anEntry));
        }
        else
        {
             if(rootNode.hasRightChild())
                result = addEntry(rootNode.getRightChild(), anEntry);
              else rootNode.setRightChild(new BinaryNode<>(anEntry));
        }
        return result;
    }
    
    public void printPreOrder()
    {
        printPreOrder(getRootNode());
    }
    
    public void printPostOrder()
    {
        printPostOrder(getRootNode());
    }
    
    public void printInOrder()
    {
        printInOrder(getRootNode());
    }
    
    private void printPreOrder(BinaryNode<T> rootNode)
    {
        if(rootNode == null) return;
        System.out.print(rootNode.getData()+":");  
        if(rootNode.isLeaf()) // and is left child
             System.out.print(" ");  
        printPreOrder(rootNode.getLeftChild());
        printPreOrder(rootNode.getRightChild());
        
    }
     
    private void printInOrder(BinaryNode<T> rootNode)
    {
        if(rootNode == null) return;
        printInOrder(rootNode.getLeftChild());
        System.out.print(" "+rootNode.getData());
        if(rootNode.isLeaf()) // and is right child
            System.out.print(" "); 
        printInOrder(rootNode.getRightChild());
        
    }
    
    private void printPostOrder(BinaryNode<T> rootNode)
    {
        if(rootNode == null) return;
        printPostOrder(rootNode.getLeftChild());
        printPostOrder(rootNode.getRightChild());
        System.out.printf(" "+rootNode.getData());  
        if(rootNode.isLeaf()) // and is root
             System.out.print(" "); 
    }
}
