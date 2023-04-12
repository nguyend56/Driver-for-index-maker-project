package proj5;

/**
 * Model a Binary Search Tree that contains String datatype.
 */
public class Dictionary{
    private BinarySearchTree<String> content;

    /**
     * default constructor
     */
    public Dictionary(){
        content = new BinarySearchTree<String>();
    }

    /**
     * search a specific word in the dictionary
     * @param theWord the word to search
     * @return true if the word is found, false otherwise
     */
    public boolean search(String theWord){
        return content.search(theWord);
    }

    /**
     * insert a new item to the dictionary
     * @param newItem the item to insert
     */
    public void insert(String newItem){
        content.insert(newItem);
    }

    /**
     * @return a printable string for the dictionary
     */
    public String toString()
    {
        if (content.size() == 0) { return " "; }

        String theStringWithoutBracket = "";
        String theStringHavingBracket = content.toString();
        int lengthOfTheStringWithBracket = theStringHavingBracket.length();

        for (int i = 0; i < lengthOfTheStringWithBracket; i++) {
            String characterAtIndex = theStringHavingBracket.substring(i, i + 1);
            if (!((characterAtIndex).compareTo("(") == 0 || characterAtIndex.compareTo(")") == 0)) {
                theStringWithoutBracket = theStringWithoutBracket + characterAtIndex;
            }
        }

        theStringWithoutBracket = theStringWithoutBracket.substring(2);
        String[] theArray = theStringWithoutBracket.split("    ");

        String toReturn = "";
        for (String the_word : theArray) {
            toReturn = toReturn + the_word + "\n";
        }
        return toReturn;
    }
}

