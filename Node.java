/**
   A Node class that holds references to the data, next node, and count/stock of the item. 
   @author Hasti Abbasi Kenarsari
*/
public class Node<T> {

    // Entry in list
    private T data;
    // The link to the next node
    private Node next; 
    // The quantity of the item in stock 
    private int count; 

    // constructor
    public Node(T dataPortion) {
        this(dataPortion, null);
    }

    public Node(T dataPortion, Node nextNode) {
        data = dataPortion; 
        next = nextNode;
    }

    public Node(T dataPortion, Node nextNode, int stock) {
        data = dataPortion;
        next = nextNode;
        count = stock;
    }

}