///
//Name:      Le, Nu
//Homework:  1
//Due:       12/10/2019
//Course:    cs-2400-01-f19

//Description:  A program that find the cheapest path between airports
import java.util.*;
public interface DigraphInterface<T> {
    public boolean addVertex(T vertexLabel);
    public boolean addEdge(T begin, T end, double edgeWeight);
    public boolean hasEdge(T begin, T end);
    public boolean isEmpty();
    public double getCheapestPath(T begin, T end,Stack<T> path);   
}
