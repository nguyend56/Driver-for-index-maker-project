package proj5;

public class BinarySearchTree<E extends Comparable<E>>
{
    private BSTNode<E> root;

    /**
     * Default Constructor.
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * inserts recursively.  I include this one so you can
     * make your own trees in your own testing class
     *
     * @param startingNode inserts into subtree rooted at staringNode
     * @param newNode node to insert
     * @return startingNode with newNode already inserted
     */
    private BSTNode<E> recursiveInsert(BSTNode<E> startingNode, BSTNode<E> newNode) {
        if (startingNode == null) {
            return newNode;
        }
        else if (startingNode.key.compareTo(newNode.key) < 0) {
            startingNode.rlink = recursiveInsert(startingNode.rlink,newNode);
            return startingNode;
        }
        else {  // startingNode.key bigger than newNode.key, so newNode goes on left
            startingNode.llink = recursiveInsert(startingNode.llink,newNode);
            return startingNode;
        }
    }


    /**
     * return tree as printable string
     * @return tree in string format with form (left subtree) value (right subtree)
     */
    public String toString(){
        return toString(root);
    }

    /**
     * recursive helper method for toString()
     *
     * @param N root of subtree to make into a string
     * @return string version of tree rooted at N
     */
    private String toString(BSTNode<E> N){
        String ret = "";
        if (N != null){
            ret += "(";
            ret += toString(N.llink);
            ret += "  " + N + "  ";
            ret += toString(N.rlink);
            ret += ")";
        }
        return ret;
    }

    /**
     * Insert a new value to the BST.
     *
     * @param value String value to insert
     */
    public void insert(E value) {
        BSTNode<E> newNode = new BSTNode<>(value);
        root = recursiveInsert(root, newNode);
    }

    /**
     * Search whether a value is in BST or not
     *
     * @param target String as target to search
     * @return true if the target string is in BST, false otherwise
     */
    public boolean search(E target){
        if (getElement(target) == null){
            return false;
        } else {
            return true;
        }
    }

    /**
     * Private helper for search. Given a root,
     * search whether target is in BST with that root or not
     * @param target String as target to search
     * @param currentRoot BST Node as root of BST to start searching
     * @return true if the target string is in BST, false otherwise
     */
    private boolean searchRecursive(E target, BSTNode<E> currentRoot) {
        if (currentRoot == null) {   // base case: empty BST always returns false
            return false;
        }
        else {// if BST is not empty...
            if (target.compareTo(currentRoot.key) == 0) {    // checks whether it's the key of currentRoot...
                return true;
            } else if (target.compareTo(currentRoot.key) > 0) { // in smaller BST on the left...
                return searchRecursive(target, currentRoot.rlink);
            } else {
                return searchRecursive(target, currentRoot.llink);  // or in smaller BST on the right
            }
        }
    }

    /**
     * Get element that equals to the given target. If there's no equal element, return null
     * @param target
     * @return
     */
    public E getElement(E target){
        return getElementRecursively(target, root);
    }


    /**
     * Helper method for getElement
     * @param target the element in the bst needed to get
     * @return the target element
     */
    private E getElementRecursively(E target, BSTNode<E> theCurrentRoot){
        if (theCurrentRoot == null) {
            return null;
        }
        else {
            if (target.compareTo(theCurrentRoot.key) == 0) {
                return theCurrentRoot.key;
            } else if (target.compareTo(theCurrentRoot.key) > 0) {
                return getElementRecursively(target, theCurrentRoot.rlink);
            } else {
                return getElementRecursively(target, theCurrentRoot.llink);
            }
        }
    }

    /**
     * find total number of nodes in the BST
     * @return total number of nodes in the BST
     */
    public int size()
    {
        return size(root);
    }

    /**
     * helper method for public size method. find total number of nodes in the BST
     * @param startingNode the node to start counting
     * @return total number of nodes in the BST
     */
    private int size(BSTNode startingNode){
        if (startingNode == null){
            return 0;
        }
        else {
            return (size(startingNode.llink) + size(startingNode.rlink) + 1);
        }
    }

    /**
     * helper method for delete public method
     * @param subRoot  root of tree to be deleted
     * @param value  element to delete
     * @return pointer to tree rooted at sub root that has value removed from it
     */
    private BSTNode<E> delete(BSTNode<E> subRoot, E value) {
        /**
         * in case sub root is an empty tree, the method will return null.
         * in case the victim is on the left of sub root, the sub root's left link have to become what recurse on
         * sub root's left child.
         * in case the victim is on the right of sub root, the sub root's right link have to become what recursion on
         * sub root's right link
         * in other case, the victim is detected. If the victim is a leaf, the method will return null. If the victim
         * has one right subtree, return the pointer to that right subtree. If the victim has 2 sub trees, choose
         * an alternative to move the content in alternative node to the victim's node. Finally, delete the alternative.
         */
        if (subRoot == null){
            return null;
        }
        else if(value.compareTo(subRoot.key) < 0){
            subRoot.llink = delete(subRoot.llink, value);
        }
        else if(value.compareTo(subRoot.key) > 0){
            subRoot.rlink = delete(subRoot.rlink, value);
        }
        else {
            if (subRoot.isLeaf()){
                return null;
            }
            else if(subRoot.hasLeftChildOnly()){
                return subRoot.llink;
            }
            else if(subRoot.hasRightChildOnly()){
                return subRoot.rlink;
            }
            else {
                BSTNode<E> runner = subRoot.rlink;
                while (runner.llink != null){
                    runner = runner.llink;
                }
                subRoot.key = runner.key;
                subRoot.rlink = delete(subRoot.rlink, runner.key);
            }
        }
        return subRoot;
    }

    /**
     * deletes value from tree rooted at sub root, return the deleted value
     * @param value element to delete
     * @return
     */
    public E delete(E value){
        E toReturn = getElement(value);
        if (toReturn != null){
            root = delete(root, value);
        }
        return toReturn;
    }
}


