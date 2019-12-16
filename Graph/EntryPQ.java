///
//Name:      Le, Nu
//Homework:  1
//Due:       12/10/2019
//Course:    cs-2400-01-f19

//Description:  A program that find the cheapest path between airports
public class EntryPQ<T> implements Comparable<VertexInterface<T>>{
    private VertexInterface<T> originVertex;
    
    public EntryPQ(VertexInterface<T> newOriginVertex, double newCost,
            VertexInterface<T> newPredessorVertex)
    {
        originVertex = new Vertex<>(newOriginVertex.getLabel());
        originVertex.setCost(newCost);
        originVertex.setPredecessor(newPredessorVertex);
    }
    
    @Override
    public int compareTo(VertexInterface<T> otherVertex)
    {
        return originVertex.getLabel().equals(otherVertex.getLabel())? 1:0;
    }
    
    public VertexInterface<T> getVertex()
    {
        return originVertex;
    }
    public double getCost()
    {
        return originVertex.getCost();
    }
    public VertexInterface<T> getPredecessor()
    {
        return originVertex.getPredecessor();
    }
}
