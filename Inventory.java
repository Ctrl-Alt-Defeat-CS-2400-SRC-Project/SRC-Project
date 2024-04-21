/**
 * Uses the linked list data type to create and maintain a list of stock in the inventory.
 * 
 * @author Hasti Abbasi Kenarsari
 */
public class Inventory {
    private LinkedList<Produce> inventory = new LinkedList<Produce>();
    
    /**
     * Removes a specified quantity of produce items from the inventory. 
     * 
     * @param produce the String representation of the produce item 
     * @param quantity the # of produce items desired to be removed
     * @return returns true if the specified quantity has been removed, and false otherwise.  
     */
    public boolean removeProduce(String produce, int quantity) {

        int count = quantity; 

        // goes through linked list
        for(int i = 1; i < inventory.getLength(); i++) {

            // gets current produce
            String current = inventory.getNodeAt(i);

            /*
            * if produce is the same as the one specified in the parameter & all desired instances 
            * haven't been removed, it gets removed & count is decreased
            */
            if(current.equalsIgnoreCase(produce) && count != 0) {

                inventory.remove(i);
                count--;

            }

        }

        return (count == 0);
    }

    /**
     * Checks if a specific quantity of a produce item is in the inventory. 
     * 
     * @param produce the String representation of the produce item 
     * @param quantity the # of produce items desired to be checked 
     * @return returns true if the quantity of items is in stock
     */
    public boolean inStock(String produce, int quantity) {

        int count = 0;
        
        // goes through linked list
        for(int i = 1; i < inventory.getLength(); i++) {

            // gets current produce
            String current = inventory.getNodeAt(i);

            /*
            * if produce is the same as the one specified in the parameter,
            * count is increased
            */
            if(current.equalsIgnoreCase(produce)) {
                count++;
            }

        }

        // returns true (indicating in stock) if there AT LEAST count # of items
        return (count >= quantity);
    }

    /**
     * Adds a specified quantity of produce items to the inventory. 
     * 
     * @param produce the String representation of the produce item 
     * @param quantity the # of produce items desired to be checked 
     * @return true if the addition(s) are successful
     */
    public boolean addProduce(Produce produce, int quantity) {
        
        for(int i = 0; i < quantity; i++) {
            inventory.add(produce);
        }

        return true;
    }

    public Produce getProduce(String produce) {
        return null; // STUB
    }

    public boolean contains(String produce) {
        return true; // STUB
    }

}
