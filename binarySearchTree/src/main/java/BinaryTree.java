///
//Name:      Le, Nu
//Homework:  1
//Due:       11/14/2019
//Course:    cs-2400-01-f19

//Description:  A program that reads in user's input and build a binary search tree
//based upon these input. Then it displays the tree in pre-,post-,in- order format.
public class BinaryTree<T> implements BinaryTreeInterface<T>{
    private BinaryNode<T> root;
    
    public BinaryTree()
    {
        root = null;
    }
    
    public BinaryTree(T rootData)
    {
        root = new BinaryNode<>(rootData);
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
