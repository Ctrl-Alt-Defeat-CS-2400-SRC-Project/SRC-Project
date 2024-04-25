import java.io.IOException;
import java.util.Scanner;

/**
 * A terminal based user interface for the farmer/owner to manage Inventory, Orders, and Clients.
 * @author Ryan Wei
 */
public class FarmerUI {
    private static final String WELCOME_MESSAGE = "Welcome!";
    private static final String MENU = "Please select an option:\n" +
            "1. Manage Inventory\n" +
            "2. Manage Orders\n" +
            "3. Manage Clients\n" +
            "4. Exit";
    private static final String INVENTORY_MENU = "Please select an option:\n" +
            "1. Add Produce to Inventory\n" +
            "2. Remove Produce from Inventory\n" +
            "3. View Inventory\n" +
            "4. Back";
    private static final String ORDERS_MENU = "Please select an option:\n" +
            "1. Add Order to Client\n" +
            "2. Mark Client Order as Done\n" +
            "3. Cancel Client Order\n" +
            "4. View Client Orders\n" +
            "5. View All Orders\n" +
            "6. Back";
    private static final String CLIENTS_MENU = "Please select an option:\n" +
            "1. Add Client\n" +
            "2. Remove Client\n" +
            "3. View All Clients\n" +
            "4. View Specific Client\n" +
            "5. Back";
    private static final String EXIT_MESSAGE = "Thank you for using our software!";

    private static ClientBase clientBase;
    private static Inventory inventory;
    static {
        try {
            clientBase = new ClientBase();
            inventory = new Inventory();
        } catch (IOException e) {
            System.out.println("Error loading client base");
        }
    }
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Starts the user interface
     */
    public static void start() {
        System.out.println(WELCOME_MESSAGE);
        boolean done = false;
        while (!done) {
            System.out.println(MENU);
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    manageInventory();
                    break;
                case 2:
                    manageOrders();
                    break;
                case 3:
                    manageClients();
                    break;
                case 4:
                    done = true;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
        System.out.println(EXIT_MESSAGE);
    }

    private static void manageInventory() {
        boolean done = false;
        while (!done) {
            System.out.println(INVENTORY_MENU);
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter produce name to add to inventory: ");
                    String produceName = scanner.nextLine();
                    if(!Inventory.contains(produceName)) {
                        System.out.println("New produce, enter season: ");
                        String season = scanner.nextLine();
                        if(season.equals("")) {
                            System.out.println("Season required. Please try again. Press enter to continue.");
                            scanner.nextLine();
                            break;
                        }
                        System.out.println("Enter quantity of produce to add to inventory: ");
                        int quantity = scanner.nextInt();
                        scanner.nextLine();
                        if(Inventory.addProduce(new Produce(produceName, season), quantity)) {
                            System.out.println("Produce added successfully. Press enter to continue.");
                            scanner.nextLine();
                        } else {
                            System.out.println("Produce not added. Please try again. Press enter to continue.");
                            scanner.nextLine();
                        }
                    } else {
                        System.out.println("Enter quantity of produce to add to inventory: ");
                        int quantity = scanner.nextInt();
                        scanner.nextLine();
                        if(Inventory.addProduce(Inventory.getProduce(produceName), quantity)) {
                            System.out.println("Produce added successfully. Press enter to continue.");
                            scanner.nextLine();
                        } else {
                            System.out.println("Produce not added. Please try again. Press enter to continue.");
                            scanner.nextLine();
                        }
                    }
                    break;
                case 2:
                    System.out.println("Enter produce name to remove from Inventory: ");
                    String produceNameRemove = scanner.nextLine();
                    System.out.println("Enter quantity of produce to remove from inventory: ");
                    int quantityRemove = scanner.nextInt();
                    scanner.nextLine();
                    if(Inventory.removeProduce(produceNameRemove, quantityRemove) != null) {
                        System.out.println("Produce removed successfully. Press enter to continue.");
                        scanner.nextLine();
                    } else {
                        System.out.println("Produce not removed. Please try again. Press enter to continue.");
                        scanner.nextLine();
                    }
                    break;
                case 3:
                    System.out.println("Inventory: ");
                    System.out.println(inventory.toString());
                    System.out.println("press enter to continue");
                    scanner.nextLine();
                    break;

                case 4:
                    done = true;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void manageOrders() {
        boolean done = false;
        while (!done) {
            System.out.println(ORDERS_MENU);
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:                 
                    System.out.println("Enter client name to add orders to:");
                    String clientName = scanner.nextLine();
                    System.out.println("Enter produce name to add to order:");
                    String produceName = scanner.nextLine();
                    System.out.println("Enter quantity of produce to add to order:");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();
                    if(clientBase.addProduce(clientName, produceName, quantity)) {
                        System.out.println("Order added successfully. press enter to continue.");
                        scanner.nextLine();
                    } else {
                        System.out.println("Order not added. Please try again. Press enter to continue.");
                        scanner.nextLine();
                    }
                    break;
                case 2:
                    System.out.println("Enter client name to mark order as done:");
                    String clientNameDone = scanner.nextLine();
                    System.out.println("Enter produce name to mark as done:");
                    String produceNameDone = scanner.nextLine();
                    System.out.println("Enter quantity of produce to mark as done:");
                    int quantityDone = scanner.nextInt();
                    scanner.nextLine();
                    if(clientBase.removeOrder(clientNameDone, produceNameDone, quantityDone)) {
                        System.out.println("Order marked as done. Press enter to continue.");
                        scanner.nextLine();
                    } else {
                        System.out.println("Order not marked. Please try again. Press enter to continue.");
                        scanner.nextLine();
                    }
                    break;
                case 3:
                    System.out.println("Enter client name to cancel order:");
                    String clientNameCancel = scanner.nextLine();
                    System.out.println("Enter produce name to cancel:");
                    String produceNameCancel = scanner.nextLine();
                    System.out.println("Enter quantity of produce to cancel:");
                    int quantityCancel = scanner.nextInt();
                    scanner.nextLine();
                    if(clientBase.cancelOrder(clientNameCancel, produceNameCancel, quantityCancel)) {
                        System.out.println("Order cancelled. Press enter to continue.");
                        scanner.nextLine();
                    } else {
                        System.out.println("Order not cancelled. Please try again. Press enter to continue.");
                        scanner.nextLine();
                    }
                    break;
                case 4:
                    System.out.println("Enter client name to view orders:\n");
                    String clientNameView = scanner.nextLine();
                    if(clientBase.getOrders(clientNameView) == null) {
                        System.out.println("Please try again\n");
                    } else {
                        printOrders(clientBase.getOrders(clientNameView));
                    }
                    break;
                case 5:
                    Produce[][] allOrders = clientBase.getAllOrders();
                    Client[] allClients = clientBase.getAllClients();
                    int i = 0;
                    for (Produce[] orders : allOrders) {
                        System.out.println(allClients[i].getName() + "'s Orders: ");
                        printOrders(orders);
                        System.out.println("");
                        i++;
                    }
                    System.out.println("press enter to continue");
                    scanner.nextLine();
                    break;
                case 6:
                    done = true;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    public static void manageClients() {
        boolean done = false;
        while (!done) {
            System.out.println(CLIENTS_MENU);
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter client name(required):");
                    String name = scanner.nextLine();
                    System.out.println("Enter client address(not required):");
                    String address = scanner.nextLine();
                    System.out.println("Enter client phone number(either email or phone is required):");
                    String phone = scanner.nextLine();
                    System.out.println("Enter client email(either email or phone is required):");
                    String email = scanner.nextLine();
                    System.out.println("Enter client age:");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    if(clientBase.addClient(name, address, phone, email, age)) {
                        System.out.println("Client added successfully.");
                    } else {
                        System.out.println("Client not added. Please try again.");
                    }
                    break;
                case 2:
                    System.out.println("Enter client name to remove:");
                    String clientName = scanner.nextLine();
                    if(clientBase.removeClient(clientName)) {
                        System.out.println("Client removed successfully.");
                    } else {
                        System.out.println("Client not removed. Please try again.");
                    }
                    break;
                case 3:
                    Client[] clients = clientBase.getAllClients();
                    for (Client client : clients) {
                        System.out.println(client.getName());
                    }
                    System.out.println("press enter to continue");
                    scanner.nextLine();
                    break;
                case 4:
                    System.out.println("Enter client name: ");
                    String clientNameView = scanner.nextLine();
                    System.out.println(clientBase.getClient(clientNameView));
                    System.out.println("press enter to continue");
                    scanner.nextLine();
                    break;
                case 5:
                    done = true;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void printOrders(Produce[] array) {
        int i = 1;
        Produce currentItem = new Produce("placeholder", "none");
        if(currentItem.equals(array[0])) {
            System.out.println("No orders found");
        }
        for (Produce item : array) {
            if(!currentItem.equals(item)) {
                System.out.println(currentItem.getName() + " x" + i);
                i = 1;
            } else {
                i++;
            }
            currentItem = item;
        }
    }

}
