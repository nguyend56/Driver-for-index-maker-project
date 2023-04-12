package proj5;

/**
 * Driver for the index maker project
 */
public class Client
{
    /**
     * Makes an index out of fileName.
     *
     * @param fileName path to text file that you want to index
     */
    public static void makeIndex(String fileName) {
        Index index = new Index();
        index.convert(fileName);
    }

    /**
     * call the method makeIndex to make index from the text
     */
    public static void main(String[] args)
    {
    	makeIndex("src/proj5/uscons.txt");
    }
}
