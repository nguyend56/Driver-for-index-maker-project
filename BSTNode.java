package proj5;

public class BSTNode<E> {

    public E key;
    public BSTNode<E> llink;
    public BSTNode<E> rlink;

    /**
     * non-default constructor
     * @param newKey string that node will hold
     */
    public BSTNode(E newKey)
    {
        key = newKey;
        llink = null;
        rlink = null;
    }

    /**
     * returns key as printable string
     */
    public String toString()
    {
        return key.toString();
    }


    /**
     *
     * @return true if this node is a leaf, else false
     */
    public boolean isLeaf() {
        return this.llink == null && this.rlink == null;
    }

    /**
     *
     * @return true if this node has a non-null right subtree
     * and a null left subtree, else false
     */
    public boolean hasRightChildOnly() {
        return this.llink == null && this.rlink != null;
    }

    /**
     *
     * @return true if this node has a non-null left subtree
     * and a null right subtree, else false
     */
    public boolean hasLeftChildOnly() {
        return this.llink != null && this.rlink == null;
    }

}
