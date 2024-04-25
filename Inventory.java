import java.nio.file.*;
import java.io.*;
import java.util.*;
import java.util.LinkedList;

/**
 * Uses the linked list data type to create and maintain a list of stock in the inventory.
 * 
 * @author Hasti Abbasi Kenarsari
 */
public class Inventory {

    private static LinkedList<Produce> inventory = new LinkedList<Produce>();
    private Path filePath = Paths.get("inventory.txt");

    public Inventory() throws IOException {
        if (Files.exists(filePath)) {
            Scanner fileScanner = new Scanner(filePath);

            while (fileScanner.hasNextLine()) {

                String line = fileScanner.nextLine();
                if (line.isEmpty()) {
                    continue; 
                }

                String[] tokens = line.split(";");
               
                for (int i = 0; i < tokens.length; i += 3) {
                    String productName = tokens[i];
                    String count = Integer.parseInt(tokens[i + 1]);
                    String season = tokens[i + 2];
                    addProduce(produceName, count);
                }

            }

            fileScanner.close();

        } else {
            Files.createFile(filePath);
        }
    }

    /**
     * Removes a specified quantity of produce items from the inventory. 
     * 
     * @param produce the String representation of the produce item 
     * @param quantity the # of produce items desired to be removed
     * @return returns true if the specified quantity has been removed, and false otherwise.  
     */
    public static Produce removeProduce(String produce, int quantity) {
        int count = quantity; 
        Produce temp = null;

        // goes through linked list
        for(int i = 1; i < inventory.getLength() + 1; i++) {
            // gets current produce
            String current = inventory.getEntry(i).getName();

            /*
            * if produce is the same as the one specified in the parameter & all desired instances 
            * haven't been removed, it gets removed & count is decreased
            */
            if(current.equalsIgnoreCase(produce)) {
                temp = inventory.decreaseProduce(i, count);
            }
        }
        return temp;
    }

    /**
     * Checks if a specific quantity of a produce item is in the inventory. 
     * 
     * @param produce the String representation of the produce item 
     * @param quantity the # of produce items desired to be checked 
     * @return returns true if the quantity of items is in stock
     */
    public static boolean inStock(String produce, int quantity) {
        Produce temp = getProduce(produce);
        if (temp == null) {
            return false;
        }
        int pos = inventory.getPosition(temp);
        if (pos == -1) {
            return false;
        } else {
            return inventory.inStock(pos, quantity);
        }
    }

    /**
     * Adds a specified quantity of produce items to the inventory. 
     * 
     * @param produce the String representation of the produce item 
     * @param quantity the # of produce items desired to be checked 
     * @return true if the addition(s) are successful
     */
    public static boolean addProduce(Produce produce, int quantity) {
        Produce temp = null;
        // goes through linked list
        for(int i = 1; i < inventory.getLength() + 1; i++) {
            // gets current produce
            String current = inventory.getEntry(i).getName();

            // if produce is the same as the one specified in the parameter, it gets added
            if(current.equalsIgnoreCase(produce.getName())) {
                temp = inventory.increaseProduce(i, quantity);
                return true;
            }
        }

        if (temp == null) {
            inventory.add(produce);
            for(int i = 1; i < inventory.getLength() + 1; i++) {
                // gets current produce
                String current = inventory.getEntry(i).getName();
    
                // if produce is the same as the one specified in the parameter, it gets added
                if(current.equalsIgnoreCase(produce.getName())) {
                    temp = inventory.increaseProduce(i, quantity);
                    return true;
                }
            }
        }

        return false;
    }

    public static Produce getProduce(String produce) {
        Produce temp = null;
        for (int i = 1; i < inventory.getLength() + 1; i++) {
            if (inventory.getEntry(i).getName().equalsIgnoreCase(produce)) {
                temp = inventory.getEntry(i);
            }
        }
        return temp;
    }

    public static boolean contains(String produce) {
        if(getProduce(produce) == null) {
            return false;
        }
        return inventory.contains(getProduce(produce));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= inventory.getLength(); i++) {
            Produce produce = inventory.getEntry(i);
            sb.append(produce.getName()).append(": ").append(inventory.getStock(i)).append("\n");
        }
        return sb.toString();
    }
}
