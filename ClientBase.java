import java.util.Iterator;
import java.util.Arrays;

/**
 * Uses the sorted linked dictionary to create and maintain a list of clients and their orders. 
 * The client base is used to add, remove, and get clients and their orders.
 * 
 * @author Ryan Wei
 */
public class ClientBase {
    private SortedLinkedDictionary<Client, Produce[]> clientBase = new SortedLinkedDictionary<Client, Produce[]>();

    /**
     * Adds a new client to the client base with a placeholder for orders
     * 
     * @param name    the name of the client
     * @param address the address of the client, can be null
     * @param phone   the phone number of the client, can be null
     * @param email   the email of the client, can be null
     * @param age     the age of the client
     * @return true if the client was added successfully, false otherwise
     */
    public boolean addClient(String name, String address, String phone, String email, int age) {
        boolean success = false;
        Produce placeholder[] = null;
        Client newClient = new Client(name, address, phone, email, age);
        clientBase.add(newClient, placeholder);
        if (clientBase.contains(newClient)) {
            success = true;
        }
        return success;
    }

    /**
     * adds an order to the client's list of orders, removes the same amount from the inventory
     * 
     * @param userName the user name of the client to add to
     * @param produce the produce to add to the order
     * @param quantity the quantity of the produce to add to the order
     * @return true if the produce was added successfully, false otherwise
     */
    public boolean addProduce(String userName, String produce, int quantity) {
        boolean success = false;
        Client client = getClient(userName);
        if (client == null) {
            System.out.println("Client not found\n");
            return success;
        }
        if (!Inventory.inStock(produce, quantity)) { // need to be added to inventory
            System.out.println("Not enough produce in stock\n");
            return success;
        }

        Produce[] orders = clientBase.getValue(client);
        if (orders == null) {
            Produce[] newOrders = new Produce[quantity];
            for (int i = 0; i < quantity; i++)
                newOrders[i] = Inventory.removeProduce(produce, 1); // need to be added to inventory
            clientBase.add(client, newOrders);
            success = true;
        } else {
            Produce[] newOrders = new Produce[orders.length + quantity];
            for (int i = 0; i < orders.length; i++) {
                newOrders[i] = orders[i];
            }
            for (int i = orders.length; i < newOrders.length; i++)
                newOrders[i] = Inventory.removeProduce(produce, 1); // need to be added to inventory
            clientBase.add(client, newOrders);
            success = true;
        }
        return success;
    }

    /**
     * Gets the produce object from the client's list of orders
     * 
     * @param userName the user name of the client
     * @param produceName the name of the produce
     * @return the produce object if found, null otherwise
     */
    public Produce getProduce(String userName, String produceName) {
        Client client = getClient(userName);
        if (client == null) {
            return null;
        }
        Produce[] orders = clientBase.getValue(client);
        if (orders == null) {
            return null;
        }
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].getName().equals(produceName)) {
                return orders[i];
            }
        }
        return null;
    }

    /**
     * Gets the client object from the client base
     * 
     * @param userName the user name of the client
     * @return the client object if found, null otherwise
     */
    public Client getClient(String userName) {
        Iterator<Client> clientIterator = clientBase.getKeyIterator();
        while (clientIterator.hasNext()) {
            Client currentClient = clientIterator.next();
            if (currentClient.getName().equals(userName)) {
                return currentClient;
            }
        }
        return null;
    }

    public Client[] getAllClients() {
        Iterator<Client> clientIterator = clientBase.getKeyIterator();
        Client[] allClients = new Client[clientBase.getSize()];
        int i = 0;
        while (clientIterator.hasNext()) {
            allClients[i] = clientIterator.next();
            i++;
        }
        return Arrays.copyOf(allClients, i);
    }

    /**
     * Retrieves the orders of a client
     * @param userName the user name of the client
     * @return an array of produce objects if the client is found, null otherwise
     */
    public Produce[] getOrders(String userName) {
        Client client = getClient(userName);
        if (client == null) {
            System.out.println("Client not found\n");
            return null;
        }
        return Arrays.copyOf(clientBase.getValue(client), clientBase.getValue(client).length);
    }

    public Produce[][] getAllOrders() {
        Iterator<Produce[]> ordersIterator = clientBase.getValueIterator();
        Produce[][] allOrders = new Produce[clientBase.getSize()][];
        int i = 0;
        while (ordersIterator.hasNext()) {
            Produce[] currentOrders = ordersIterator.next();
            allOrders[i] = currentOrders;
            i++;
        }
        return Arrays.copyOf(allOrders, i);
    }

    /**
     * Removes a client from the client base
     * 
     * @param userName the user name of the client
     * @return an array of produce objects if the client is found, null otherwise
     */
    public boolean removeClient(String userName) {
        boolean removed = false;
        Client client = getClient(userName);
        if (client == null) {
            System.out.println("Client not found\n");
            return removed;
        } else {
            clientBase.remove(client);
            removed = true;
        }
        return removed;
    }

    /**
     * Removes an order from the client's list of orders
     * 
     * @param userName the user name of the client
     * @param produce the produce to remove from the order
     * @param quantity the quantity of the produce to remove from the order
     * @return true if the produce was removed successfully, false otherwise
     */
    public Boolean removeOrder(String userName, String produce, int quantity) {
        boolean removed = false;
        int count = 0;
        Client client = getClient(userName);
        if (client == null) {
            System.out.println("Client not found\n");
            return removed;
        }
        Produce[] orders = clientBase.getValue(client);
        if (orders == null) {
            System.out.println("No orders found\n");
            return removed;
        }
        if(checkQuantity(userName, produce) == 0) {
            System.out.println("No orders found of that type found\n");
            return removed;
        } else if (checkQuantity(userName, produce) < quantity) {
            System.out.println("Quantity entered exceeds amount ordered by client\n");
            return removed;
        } else if (checkQuantity(userName, produce) < 0 ) {
            System.out.println("Error, either client not found or no orders found\n");
            return removed;
        }

        for (int i = 0; i < orders.length; i++) {
            if (orders[i].equals(getProduce(userName, produce)) && count < quantity) {
                Produce[] newOrders = new Produce[orders.length - 1];
                for (int j = 0; j < i; j++) {
                    newOrders[j] = orders[j];
                }
                for (int j = i + 1; j < orders.length; j++) {
                    newOrders[j - 1] = orders[j];
                }
                clientBase.add(client, newOrders);
                removed = true;
                i--;
                count++;
            }
        }
        if(!removed)
            System.out.println("Order not found\n");
        return removed;
    }

    /**
     * Cancels an order and adds the produce back to the inventory
     * 
     * @param userName the user name of the client
     * @param produceName the name of the produce
     * @param quantity the quantity of the produce to cancel
     * @return true if the order was cancelled successfully, false otherwise
     */
    public boolean cancelOrder(String userName, String produceName, int quantity) {
        boolean cancelled = removeOrder(userName, produceName, quantity);
        if (cancelled)
            Inventory.addProduce(getProduce(userName, produceName), quantity);
        else
            System.out.println("Order not cancelled.\n");
        return cancelled;
    }

    /**
     * Checks the quantity of a produce in a client's order
     * 
     * @param userName the user name of the client
     * @param produceName the name of the produce
     * @return the quantity of the produce in the order, -1 if the client or produce is not found
     */
    public int checkQuantity(String userName, String produceName) {
        Client client = getClient(userName);
        if (client == null) {
            return -1;
        }
        Produce[] orders = clientBase.getValue(client);
        if (orders == null) {
            return -1;
        }
        int count = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].getName().equals(produceName)) {
                count++;
            }
        }
        return count;
    }


}
