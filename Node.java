/**
   A Node class that holds references to the data, next node, and count/stock of the item. 
   @author Hasti Abbasi Kenarsari
*/
public class Node<T> {
    // Entry in list
    private T data;
    // The link to the next node
    private Node<T> next; 
    // The quantity of the item in stock 
    private int count; 

    // constructor
    public Node(T dataPortion) {
        this(dataPortion, null);
    }

    public Node(T dataPortion, Node<T> nextNode) {
        data = dataPortion; 
        next = nextNode;
        count = 0;
    }

    public Node(T dataPortion, Node<T> nextNode, int stock) {
        data = dataPortion;
        next = nextNode;
        count = stock;
    }

    public T getData() {
        return data;
    }

    public void setData(T newData) {
        data = newData;
    }

    public Node<T> getNextNode() {
        return next;
    }

    public void setNextNode(Node<T> nextNode) {
        next = nextNode;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int stock) {
        count = stock;
    }

    public void incrementCount(int amount) {
        count += amount;
    }

    public void decrementCount(int amount) {
        count -= amount;
    }

    public void clearCount() {
        count = 0;
    }

    public boolean hasCount() {
        return count > 0;
    }

}