package proj5;

/**
 * Model index datatype. It can hold an index for words and the pages in which it appears
 */
public class Index{
    public static final int WORD_LENGTH_THRESHOLD = 3;

    private BinarySearchTree<Word> contents;

    /**
     * default constructor
     */
    public Index(){
        contents = new BinarySearchTree<Word>();
    }

    /**
     * in this Index, get Word datatype which has the key similar to given String and case-insensitive
     * for the first letter of the given String.
     * @param theWord String word as desired key to find the Word Object
     * @return the Word object with key equals to given string. if the word is not found, return null.
     */
    private Word getInsensitiveWord(String theWord){
        Word returnWord = contents.getElement(new Word(theWord));
        if (returnWord == null){
            String firstCharacter = theWord.substring(0,1);
            if (firstCharacter.compareTo(firstCharacter.toLowerCase())==0){
                return contents.getElement(new Word(firstCharacter.toUpperCase() + theWord.substring(1)));
            }
            else if (firstCharacter.compareTo(firstCharacter.toUpperCase())==0){
                return contents.getElement(new Word(firstCharacter.toLowerCase() + theWord.substring(1)));
            }
            else {
                return returnWord;
            }
        }
        else {
            return returnWord;
        }
    }

    /**
     * insert a given string and its pageNumber to the Index. add the new pageNumber to it
     * if the given string is already in Index.
     * @param value String value to add to Index
     * @param pageNumber int pageNumber to add to Index
     */
    public void insert(String value, int pageNumber){
        Word theWord = new Word(value);
        Word theElement = contents.getElement(theWord);
        if(theElement != null){
            theElement.insertPageNum(pageNumber);
        }
        else{
            contents.insert(theWord);
            theWord.insertPageNum(pageNumber);
        }
    }

    /**
     * search for specific word
     * @param word the word looking for
     * @return true if the word is found, false otherwise
     */
    public boolean search(String word){
        return contents.search(new Word(word));
    }

    /**
     * @return printable string for the Index object
     */
    public String toString(){
        String[] message = contents.toString().split(" ");
        String toReturn = "";
        for (String word: message){
            if (!word.contains("(") && !word.contains(")") && !word.isBlank()){
                if (word.contains("}")){
                    toReturn = toReturn + word + "\n";
                }
                else if (word.contains(",")){
                    toReturn += word;
                }
                else {
                    toReturn = toReturn + word + " ";
                }
            }
        }
        return toReturn;
    }

    /**
     * Delete the given Word object from Index and return that deleted Word object
     * @param current Word Object given to deleted
     * @return the deleted Word Object
     */
    private Word delete(Word current){
        return contents.delete(current);
    }

    /**
     * create an index from a given file, then print:
     * > For any word deleted from the index and inserted into the dictionary, print out the word
     * along with its (full) pagelist.
     * > The complete index, with case preserved. All words should be in ASCII alphabetical order
     * which means the words that start with a capital letter will come before the words that are
     * all lowercase
     * > Entire dictionary. All words should be in ASCII alphabetical order. One word per line.
     *
     * @param filename String file name to make index of
     */
    public void convert(String filename){
        FileReader file = new FileReader(filename);
        Dictionary dictionary = new Dictionary();
        String nextWord = file.nextToken();
        Word currentWord;
        int pageNumber = 1;

        while (nextWord!=null){
            if (nextWord.equals("#")){
                pageNumber += 1;
            }
            else {
                if ((nextWord.length()>= WORD_LENGTH_THRESHOLD) && (!dictionary.search(nextWord))){
                    currentWord = getInsensitiveWord(nextWord);
                    if (currentWord!=null){
                        if (!currentWord.hasPage(pageNumber)){
                            if (!currentWord.fullPageList()){
                                if (!((currentWord.getKey()).equals(nextWord))){
                                    Word wordToMerge = new Word(nextWord);
                                    wordToMerge.insertPageNum(pageNumber);
                                    currentWord.merge(wordToMerge);
                                }
                                else{
                                    currentWord.insertPageNum(pageNumber);
                                }
                            }
                            else {
                                System.out.println("Deleting '" + currentWord + "' from index.");
                                delete(currentWord);
                                dictionary.insert(currentWord.getKey());
                            }
                        }
                    }
                    else {
                        this.insert(nextWord, pageNumber);
                    }
                }
            }
            nextWord = file.nextToken();
        }
        System.out.println(this.toString());
        System.out.println(dictionary);
    }
}
