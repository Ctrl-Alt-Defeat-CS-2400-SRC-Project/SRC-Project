/**
 * Uses the linked list data type to create and maintain a list of stock in the inventory.
 * 
 * @author
 */
public class Inventory {
    private LinkedList<Produce> inventory = new LinkedList<Produce>();
    
    public Produce removeProduce(String produce, int quantity) {
        return null; // STUB
    }

    public boolean inStock(String produce, int quantity) {
        return true; // STUB
    }

    public boolean addProduce(Produce produce, int quantity) {
        return true; // STUB
    }

    public Produce getProduce(String produce) {
        return null; // STUB
    }

    public boolean contains(String produce) {
        return true; // STUB
    }

}
