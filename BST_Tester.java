package proj5;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * This class test the method insert, size and search in BinarySearchTree class
 */
public class BST_Tester {

    @Test
    public void insertTest1()
    {
        BinarySearchTree<String> bst = new BinarySearchTree<>();
        bst.insert("a");
        assertEquals(bst.size(), 1);
        assertEquals("(  a  )",bst.toString());
    }

    @Test
    public void insertTest2()
    {
        BinarySearchTree<String> bst = new BinarySearchTree<>();
        bst.insert("a");
        bst.insert("c");
        bst.insert("b");
        assertEquals(bst.size(), 3);
        assertEquals("(  a  ((  b  )  c  ))",bst.toString());
    }

    @Test
    public void insertTest3()
    {
        BinarySearchTree<String> bst = new BinarySearchTree<>();
        bst.insert("a");
        bst.insert("d");
        bst.insert("c");
        bst.insert("e");
        bst.insert("b");
        assertEquals(bst.size(), 5);
        assertEquals("(  a  (((  b  )  c  )  d  (  e  )))",bst.toString());
    }

    @Test
    public void testSearchWithEmptyTree()
    {
        BinarySearchTree<String> bst = new BinarySearchTree<>();
        assertFalse(bst.search("a"));
    }

    @Test
    public void testSearchWithNonEmptyTreeTrue()
    {
        BinarySearchTree<String> bst = new BinarySearchTree<>();
        bst.insert("a");
        bst.insert("b");
        assertTrue(bst.search("a"));
        assertTrue(bst.search("b"));
    }

    @Test
    public void testSearchWithNonEmptyTreeFalse()
    {
        BinarySearchTree<String> bst = new BinarySearchTree<>();
        bst.insert("a");
        bst.insert("b");
        assertFalse(bst.search("c"));
        assertFalse(bst.search("d"));

    }

    @Test
    public void testSizeWIthEmptyTree()
    {
        BinarySearchTree<String> bst = new BinarySearchTree<>();
        assertEquals(bst.size(), 0);
    }

    @Test
    public void testSizeWithNonEmptyTree()
    {
        BinarySearchTree<String> bst = new BinarySearchTree<>();
        bst.insert("a");
        bst.insert("b");
        assertEquals(bst.size(), 2);
    }
}
