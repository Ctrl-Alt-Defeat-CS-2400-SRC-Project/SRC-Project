import java.util.Iterator;
import java.util.Arrays;
import java.nio.file.*;
import java.io.*;
import java.util.Scanner;
import java.util.*;

/**
 * Uses the sorted linked dictionary to create and maintain a list of clients and their orders. 
 * The client base is used to add, remove, and get clients and their orders.
 * 
 * @author Ryan Wei
 */
public class ClientBase {

    private SortedLinkedDictionary<Client, Produce[]> clientBase = new SortedLinkedDictionary<Client, Produce[]>();
    private Produce tempProduce = new Produce("placeholder", "none");
    private Produce[] placeholder = {tempProduce};
    private Path filePath = Paths.get("clients.txt");

    public ClientBase() throws IOException {
        if (Files.exists(filePath)) {
            Scanner fileScanner = new Scanner(filePath);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.isEmpty()) {
                    continue; // Skip empty lines
                }
                String[] tokens = line.split(";");
                String name = tokens[0];
                String address = tokens[1];
                String phone = tokens[2];
                String email = tokens[3];
                int age = Integer.parseInt(tokens[4]);
                addClient(name, address, phone, email, age);
                for (int i = 5; i < tokens.length; i += 2) {
                    String produce = tokens[i];
                    String season = tokens[i + 1];
                    addProduce(name, produce, season);
                }
            }
            fileScanner.close();
        } else {
            Files.createFile(filePath);
        }
    }

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
        if (name == null || age < 0) {
            System.out.println("Invalid input for name or age");
            return success;
        } else if (phone == null && email == null) {
            System.out.println("Phone or email required");
            return success;
        }
        Client newClient = new Client(name, address, phone, email, age);
        clientBase.add(newClient, Arrays.copyOf(placeholder, placeholder.length));
        if (clientBase.contains(newClient)) {
            try {
                saveToFile();
            } catch (IOException e) {
                System.out.println("Error saving to file");
            }
            success = true;
        }
        return success;
    }

    private boolean addProduce(String userName, String produce, String season) {
        boolean success = false;
        Client client = getClient(userName);
        if (client == null) {
            System.out.println("Client not found\n");
            return success;
        }
        Produce[] orders = clientBase.getValue(client);
        if (orders[0].compareTo(placeholder[0]) == 0) {
            Produce[] newOrders = new Produce[1];
            newOrders[0] = new Produce(produce, season);
            clientBase.add(client, newOrders);
            try {
                saveToFile();
            } catch (IOException e) {
                System.out.println("Error saving to file");
            }
            success = true;
        } else {
            Produce[] newOrders = new Produce[orders.length + 1];
            for (int i = 0; i < orders.length; i++) {
                newOrders[i] = orders[i];
            }
            newOrders[orders.length] = new Produce(produce, season);
            clientBase.add(client, newOrders);
            try {
                saveToFile();
            } catch (IOException e) {
                System.out.println("Error saving to file");
            }
            success = true;
        }
        return success;
    }

    /**
     * adds an order to the client's list of orders, removes the same amount from the Inventory
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
        if (!Inventory.inStock(produce, quantity)) { // need to be added to Inventory
            System.out.println("Not enough produce in stock\n");
            return success;
        }

        Produce[] orders = clientBase.getValue(client);
        if (orders[0].compareTo(placeholder[0]) == 0) {
            Produce[] newOrders = new Produce[quantity];
            for (int i = 0; i < quantity; i++)
                newOrders[i] = Inventory.removeProduce(produce, 1); // need to be added to Inventory
            clientBase.add(client, newOrders);
            try {
                saveToFile();
            } catch (IOException e) {
                System.out.println("Error saving to file");
            }
            success = true;
        } else {
            Produce[] newOrders = new Produce[orders.length + quantity];
            for (int i = 0; i < orders.length; i++) {
                newOrders[i] = orders[i];
            }
            for (int i = orders.length; i < newOrders.length; i++)
                newOrders[i] = Inventory.removeProduce(produce, 1); // need to be added to Inventory
            clientBase.add(client, newOrders);
            try {
                saveToFile();
            } catch (IOException e) {
                System.out.println("Error saving to file");
            }
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
        if (orders == placeholder) {
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
            if (currentClient.getName().equalsIgnoreCase(userName)) {
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
            try {
                saveToFile();
            } catch (IOException e) {
                System.out.println("Error saving to file");
            }
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
        if (orders == placeholder) {
            System.out.println("No orders found\n");
            return removed;
        }
        if(checkQuantity(userName, produce) == 0) {
            System.out.println("No orders found of that type found\n");
            return removed;
        } else if (checkQuantity(userName, produce) < quantity && checkQuantity(userName, produce) > 0) {
            System.out.println("Quantity entered exceeds amount ordered by client\n");
            return removed;
        } else if (checkQuantity(userName, produce) < 0 ) {
            System.out.println("Error, either client not found or no orders found\n");
            return removed;
        }

        for (int i = 0; i < orders.length; i++) {
            if (orders[i].getName().equalsIgnoreCase(produce) && count < quantity) {
                Produce[] newOrders = new Produce[orders.length - 1];
                for (int j = 0; j < i; j++) {
                    newOrders[j] = orders[j];
                }
                for (int j = i + 1; j < orders.length; j++) {
                    newOrders[j - 1] = orders[j];
                }
                clientBase.add(client, newOrders);
                orders = clientBase.getValue(client);
                removed = true;
                i--;
                count++;
            }
        }
        if(!removed)
            System.out.println("Order not found\n");
        else
            try {
                saveToFile();
            } catch (IOException e) {
                System.out.println("Error saving to file");
            }
        return removed;
    }

    /**
     * Cancels an order and adds the produce back to the Inventory
     * 
     * @param userName the user name of the client
     * @param produceName the name of the produce
     * @param quantity the quantity of the produce to cancel
     * @return true if the order was cancelled successfully, false otherwise
     */
    public boolean cancelOrder(String userName, String produceName, int quantity) {
        boolean cancelled = removeOrder(userName, produceName, quantity);
        if (cancelled) {
            Inventory.addProduce(getProduce(userName, produceName), quantity);
        }
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
            if (orders[i].getName().equalsIgnoreCase(produceName)) {
                count++;
            }
        }
        return count;
    }

    public boolean containsClient(String userName) {
        return getClient(userName) != null;
    }

    public String toString() {
        String result = "";
        Iterator<Client> clientIterator = clientBase.getKeyIterator();
        while (clientIterator.hasNext()) {
            Client currentClient = clientIterator.next();
            result += currentClient.toString();
            Produce[] orders = clientBase.getValue(currentClient);
            for (int i = 0; i < orders.length; i++) {
                result += orders[i].toString();
            }
        }
        result += "\n";
        return result;
    }

    private void saveToFile() throws IOException {
        PrintWriter writer = new PrintWriter(filePath.toFile());
        Iterator<Client> clientIterator = clientBase.getKeyIterator();
        while (clientIterator.hasNext()) {
            Client currentClient = clientIterator.next();
            writer.print(currentClient.getName() + ";");
            writer.print(currentClient.getAddress() + ";");
            writer.print(currentClient.getPhone() + ";");
            writer.print(currentClient.getEmail() + ";");
            writer.print(currentClient.getAge() + ";");
            Produce[] orders = clientBase.getValue(currentClient);
            for (int i = 0; i < orders.length; i++) {
                writer.print(orders[i].getName() + ";");
                writer.print(orders[i].getSeason() + ";");
            }
            writer.println();
        }
        writer.close();
    }


}
