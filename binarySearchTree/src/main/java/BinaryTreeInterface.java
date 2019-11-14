///
//Name:      Le, Nu
//Homework:  1
//Due:       11/14/2019
//Course:    cs-2400-01-f19

//Description:  A program that reads in user's input and build a binary search tree
//based upon these input. Then it displays the tree in pre-,post-,in- order format.
public interface BinaryTreeInterface<T> {
    public void setRootData( T rootData);
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
            BinaryTreeInterface<T> rightTree);
}
