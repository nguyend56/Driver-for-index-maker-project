package proj5;

/**
 * Model Queue, which is a datatype used to hold the elements about to be processed in First In First Out order
 *
 * WRITE UP:
 * I chose Queue for pageList due to the advantage of keeping element in order of this datatype. The pageList insert
 * pages in order, thus, using Queue would facilitate me in the process of constructing pageList.
 * Pros:
 * Insert in order, easy to merge and to store extra pages when needed
 * (can not do the same for array as its size is unchanged).
 * Cons:
 * Requires several restricted precondition, which is bad when dealing with duplicates: its
 * hard to delete the duplicate.
 */
public class Queue<E> {
    public static final int DEFAULT_CAPACITY = 10;
    public static final int DEFAULT_INCREMENT_CAPACITY = 5;

    private int front;
    private int rear;
    private int capacity;
    private int capacityIncrement;
    private int count;

    private Object[] itemArray;

    /**
     * Default constructor
     */
    public Queue(){
        front = 0;
        rear = 0;
        count = 0;
        capacity = DEFAULT_CAPACITY;
        capacityIncrement = DEFAULT_INCREMENT_CAPACITY;
        itemArray = new Object[capacity];
    }

    /**
     * insert new element to the Queue
     * @param newItem new Item to insert
     */
    public void insert(E newItem){
        if (size() == capacity){
            capacity += capacityIncrement;
            Object[] tempArray = new Object[capacity];

            if (front == 0){
                rear = size();
                for (int i = front; i < rear; i++){
                    tempArray[i] = itemArray[i];
                }
            }
            else {
                for (int i = 0; i < rear; i++){
                    tempArray[i] = itemArray[i];
                }
                for (int i = front; i < size(); i++){
                    tempArray[i+capacityIncrement] = itemArray[i];
                }
                front += capacityIncrement;
            }
            itemArray = tempArray;
        }
        itemArray[rear] = newItem;
        rear = (rear+1) % capacity;
        count++;
    }

    /**
     * remove the first element from the Queue
     * @return the new Queue that already deleted the first element
     */
    public E remove(){
        if (isEmpty()){
            return null;
        }
        else{
            E tempItem = (E)itemArray[front];
            front = (front+1) % capacity;
            count--;
            return tempItem;
        }
    }

    /**
     * take a look at the first element in the queue
     * @return the first element in the queue
     */
    public E peekAtHeadElement(){
        if (isEmpty()){
            return null;
        }
        else{
            return (E)itemArray[front];
        }
    }

    /**
     * @return the number of element in the queue
     */
    public int size(){
        return count;
    }

    /**
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty(){
        return (size() == 0);
    }

    /**
     * make a copy of the queue
     * @return a copy of the queue
     */
    public Queue<E> clone(){
        Queue<E> cloneQueue = new Queue<>();
        cloneQueue.front = this.front;
        cloneQueue.rear = this.rear;
        cloneQueue.count = this.count;
        cloneQueue.capacity = this.capacity;
        cloneQueue.capacityIncrement = this.capacityIncrement;
        cloneQueue.itemArray = this.itemArray.clone();

        return cloneQueue;
    }

    /**
     * @return a printable string for queue
     */
    public String toString(){
        String toReturn = "{>";
        Queue<E> cloneQueue = this.clone();

        while (!cloneQueue.isEmpty()){
            if (cloneQueue.size()>1){
                toReturn = toReturn + cloneQueue.remove().toString() +", ";
            }
            else {
                toReturn += cloneQueue.remove().toString();
            }
        }
        toReturn += "}";
        return toReturn;
    }
}
