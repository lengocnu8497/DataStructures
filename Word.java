///
//Name:      Le, Nu
//Homework:  1
//Due:       11/28/2019
//Course:    cs-2400-01-f19

//Description:  Implement a word count program that uses the BST. Output the words in alphabetical order
//preceded with their frequency
public class Word implements Comparable<Object> {
    private String word;
    private int count;
    
    public Word(String word)
    {
        this.word = word;
        count = 1;
    }
    
    public String getWord()
    {
        return word;
    }
    public int getCount()
    {
        return this.count;
    }
    
    @Override
    public int compareTo (Object obj)
    {
       Word newWord = (Word) obj;
       return this.word.compareTo(newWord.getWord());
       
    }
    
    public void setCount(int count)
    {
        this.count = count;
    }
}
