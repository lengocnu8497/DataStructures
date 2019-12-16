///
//Name:      Le, Nu
//Homework:  1
//Due:       12/10/2019
//Course:    cs-2400-01-f19

//Description:  A program that find the cheapest path between airports
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Stack;
import java.util.PriorityQueue;
public class Digraph<T> implements DigraphInterface<T>
{
    private Dictionary<T ,VertexInterface<T>> vertices;
    private int edgeCount;
    
    public Digraph()
    {
        //vertices = (Dictionary<T ,VertexInterface<T>>) new Object();
        vertices = new Hashtable<T ,VertexInterface<T>>();
    }

    public boolean addVertex(T vertexLabel)
    {
        VertexInterface<T> result = vertices.put(vertexLabel, new Vertex<>(vertexLabel));
        return result == null;
    }
    
    public boolean addEdge(T begin, T end, double edgeWeight)
    {
        boolean result = false;
        VertexInterface<T> beginVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);
        
        if(beginVertex != null && endVertex != null)
            result = beginVertex.connect(endVertex, edgeWeight);
        return result;
    }

    public boolean hasEdge(T begin, T end)
    {
        boolean found = false;
        VertexInterface<T> beginVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);
        
         if(beginVertex != null && endVertex != null)
         {
             Iterator<VertexInterface<T>> neighbors = beginVertex.getNeighborIterator();
             while(!found && neighbors.hasNext())
             {
               VertexInterface<T> nextNeighbor = neighbors.next();
               if(endVertex.equals(nextNeighbor))
                   found = true;
             }
         }
         return found;
    }

    public boolean isEmpty()
    {
        return vertices.isEmpty();
    }
    
    protected void resetVertices(T begin)
    {
        VertexInterface<T> beginVertex = vertices.get(begin);
        Iterator<VertexInterface<T>> vertexIterator = beginVertex.getNeighborIterator();
        while(vertexIterator.hasNext())
        {
            VertexInterface<T> nextVertex = vertexIterator.next();
            nextVertex.unvisit();
            nextVertex.setCost(0);
            nextVertex.setPredecessor(null);
        }
    }

    public double getCheapestPath(T begin, T end,Stack<T> path)
    {
        //resetVertices(begin);
        boolean done = false;
        
        PriorityQueue<EntryPQ<T>> vertexQueue = new PriorityQueue<>();
        
        VertexInterface<T> originVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);
        
        vertexQueue.add(new EntryPQ(originVertex,0,null));
        
        while(!done && !vertexQueue.isEmpty())
        {
            EntryPQ<T> frontEntry = vertexQueue.remove();
            VertexInterface<T> frontVertex = frontEntry.getVertex();
            if(!frontVertex.isVisited())
            {
                frontVertex.visit();
                frontVertex.setCost(frontEntry.getCost());
                frontVertex.setPredecessor(frontEntry.getPredecessor());
                if(frontVertex.equals(endVertex))
                    done = true;
                else
                {
                    Iterator<VertexInterface<T>> vertexIterator = frontVertex.getNeighborIterator();
                    Iterator<Double> weightIterator = frontVertex.getWeightIterator();
                    while(vertexIterator.hasNext() && weightIterator.hasNext())
                    {
                       VertexInterface<T> nextNeighbor = vertexIterator.next();
                       Double weightOfEdgeToNeighbor = weightIterator.next();
                       if(!nextNeighbor.isVisited())
                       {
                           double nextCost = weightOfEdgeToNeighbor + frontVertex.getCost();
                           nextNeighbor.setCost(nextCost);
                           nextNeighbor.setPredecessor(frontVertex);
                           vertexQueue.add(new EntryPQ(nextNeighbor,nextCost,frontVertex));
                       }
                    }
                }
            }
        }
        double pathCost = endVertex.getCost();
        path.push(endVertex.getLabel());
        VertexInterface<T> vertex = endVertex;
        while(vertex.hasPredecessor())
        {
            vertex = vertex.getPredecessor();
            path.push(vertex.getLabel());
        }
        return pathCost;     
    }
}
