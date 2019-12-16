///
//Name:      Le, Nu
//Homework:  1
//Due:       12/10/2019
//Course:    cs-2400-01-f19

//Description:  A program that find the cheapest path between airports
import java.util.Iterator;
public interface VertexInterface<T> {
    public T getLabel();
    public void visit();
    public void unvisit();
    public boolean isVisited();
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight);
    public boolean connect(VertexInterface<T> endVertex);
    public Iterator<VertexInterface<T>> getNeighborIterator();
    public Iterator<Double> getWeightIterator();
    public boolean hasNeighbor();
    public VertexInterface<T> getUnvisitedNeighbor();
    public double getWeightOfEdgeToNeighbor();
    public void setPredecessor(VertexInterface<T> predecessor);
    public VertexInterface<T> getPredecessor();
    public boolean hasPredecessor();
    public void setCost(double newCost);
    public double getCost();
}