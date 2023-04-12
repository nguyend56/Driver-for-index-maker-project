package proj5;

/**
 * Model Word datatype, representing the word and its pageList in Index.
 */
public class Word implements Comparable<Word>{
    public static final int MAX_CAPACITY = 4;
    private String key;
    private Queue<Integer> pageList;


    /**
     * non-default constructor with given vale
     * @param value the given value to construct Word object
     */
    public Word(String value){
        key = value;
        pageList = new Queue<>();
    }

    /**
     * compare 2 Word
     * @param theOtherWord other Word Object to compare
     * @return 0 if their keys are the same, -1 if this key is smaller then other key; otherwise return 1
     */
    public int compareTo(Word theOtherWord){
            return this.key.compareTo(theOtherWord.key);

    }

    /**
     * getter for the key of this object
     * @return String key as key to return
     */
    public String getKey(){
        return key;
    }

    /**
     * insert a pageNumber to pageList
     * @param pageNumber integer as page to add
     */
    public void insertPageNum(int pageNumber){
        pageList.insert(pageNumber);
    }

    /**
     * Check whether current pageList has reached the max capacity
     * @return true if the capacity is reached, otherwise return false.
     */
    public boolean fullPageList(){
        return pageList.size() >= MAX_CAPACITY;
    }

    /**

     * @return printable string for Word object
     */
    public String toString() {
        return key + " {" + pageList.toString().substring(2);
    }

    /**
     * Check whether pageList of this Word has the given pageNumber
     * @param pageNumber integer as page given
     * @return true if contains; else false
     */
    public boolean hasPage(int pageNumber){
        Queue<Integer> pageListClone = pageList.clone();
        boolean found = false;

        while (!pageListClone.isEmpty()){
            if (pageListClone.remove() == pageNumber){
                found = true;
            }
        }
        return found;
    }

    /**
     * the method merge another Word object to this Word Object. This word's key is set to lower case. Also, the
     * pages in pageList are merged in order. The changes to the other word object should not affect this current
     * word after merged
     *
     * @param otherWord other Word object to merge
     * @return Word object after the merge
     */
    public void merge(Word otherWord){
        this.key = this.key.toLowerCase();
        Queue<Integer> theNewPageList = new Queue<Integer>();
        Queue<Integer> firstPageList = this.pageList.clone();
        Queue<Integer> secondPageList = otherWord.pageList.clone();

        while (!(firstPageList.isEmpty() && secondPageList.isEmpty())){
            if (firstPageList.isEmpty()){
                theNewPageList.insert(secondPageList.remove());
            }
            else if (secondPageList.isEmpty()){
                theNewPageList.insert(firstPageList.remove());
            }
            else {
                if (firstPageList.peekAtHeadElement()<secondPageList.peekAtHeadElement()){
                    theNewPageList.insert(firstPageList.remove());
                }
                else if (firstPageList.peekAtHeadElement()>secondPageList.peekAtHeadElement()){
                    theNewPageList.insert(secondPageList.remove());
                }
                else {
                    theNewPageList.insert(firstPageList.remove());
                    secondPageList.remove();
                }
            }
        }
        this.pageList = theNewPageList;
    }
}
