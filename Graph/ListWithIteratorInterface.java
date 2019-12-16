///
//Name:      Le, Nu
//Homework:  1
//Due:       12/10/2019
//Course:    cs-2400-01-f19

//Description:  A program that find the cheapest path between airports
import java.util.Iterator;
public interface ListWithIteratorInterface<T> extends ListInterface<T>, Iterable<T>
{
      public Iterator<T> getIterator();
}
