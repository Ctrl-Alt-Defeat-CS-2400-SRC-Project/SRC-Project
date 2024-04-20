/**
   A Node class that holds references to the data, next node, and count/stock of the item. 
   @author Hasti Abbasi Kenarsari
*/
private class Node {

    // Entry in list
    private T data;
    // The link to the next node
    private Node next; 
    // The quantity of the item in stock 
    private int count; 

    // constructor
    private Node(T dataPortion) {
        this(dataPortion, null);
    }

    private Node(T dataPortion, Node nextNode) {
        data = dataPortion; 
        next = nextNode;
    }

    private Node(T dataPortion, Node nextNode, int stock) {
        data = dataPortion;
        next = nextNode;
        count = stock;
    }

}