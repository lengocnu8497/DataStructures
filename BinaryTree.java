///
//Name:      Le, Nu
//Homework:  1
//Due:       11/28/2019
//Course:    cs-2400-01-f19

//Description:  Implement a word count program that uses the BST. Output the words in alphabetical order
//preceded with their frequency
public class BinaryTree<T> implements BinaryTreeInterface<T>{
    private BinaryNode<T> root;
    protected int numberOfEntries;
    
    public BinaryTree()
    {
        root = null;
        numberOfEntries = 0;
    }
    
    public BinaryTree(T rootData)
    {
        root = new BinaryNode<>(rootData);
        numberOfEntries = 1;
    }
    
    public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree)
    {
        initializeTree(rootData,leftTree,rightTree);
    }
    
    public void initializeTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree)
    {
        root = new BinaryNode<>(rootData);
        if((leftTree != null) && !leftTree.isEmpty())
            root.setLeftChild(leftTree.root.copy());
         if((rightTree != null) && !rightTree.isEmpty())
            root.setRightChild(rightTree.root.copy());
    }
    
    public boolean isEmpty()
    {
        return root == null;
    }
    
    public void setRootData( T rootData)
    {
        root.setData(rootData);
    }
    
    public void setRootNode(BinaryNode<T> rootNode)
    {
        root = rootNode;
    }
    
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
            BinaryTreeInterface<T> rightTree)
    {
         initializeTree(rootData,(BinaryTree<T>) leftTree, (BinaryTree<T>) rightTree);
    }
    
    protected BinaryNode<T> getRootNode()
    {
        return root;
    }   
}
