import javax.swing.JOptionPane;

/**
 * A class that uses joptions panels to provide a user interface for the client.
 * The client will be able to sign in, change information, and request orders.
 * 
 * @author Ryan Wei
 */
public class ClientUI_A {
    private static ClientBase clientBase = new ClientBase();
    private static Inventory inventory = new Inventory();
    public static void start() {
        System.out.println("Welcome! Please sign up or log in.");
        String name = JOptionPane.showInputDialog("Enter your user name: ");
        if(clientBase.containsClient(name)) {
            loggedIn(name);
        } else {
            int ans;
            System.out.println("Welcome, " + name + "!");
            String address = JOptionPane.showInputDialog("Enter your address (optional): ");
            String phone = JOptionPane.showInputDialog("Enter your phone number (enter phone or email or both): ");
            String email = JOptionPane.showInputDialog("Enter your email (enter phone or email or both): ");
            int age = Integer.parseInt(JOptionPane.showInputDialog("Enter your age(required): "));
            if(clientBase.addClient(name, address, phone, email, age)) {
                ans = JOptionPane.showOptionDialog(null, "You have sucessfully signed up! Would you like to sign in?", "Sign up", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            } else {
                ans = JOptionPane.showOptionDialog(null, "Sign up failed. Would you like to try again?", "Sign up", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            }

            if(ans == JOptionPane.YES_OPTION) {
                start();
            } else {
                System.out.println("Goodbye!");
            }
        }
    }

    public static void loggedIn(String username) {
        System.out.println("Welcome back " + username + "! What would you like to do?");
        int ans = JOptionPane.showOptionDialog(null, "What would you like to do?", "Options", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {"Change information", "View information", "Request order", "Log out"}, null);
        if(ans == JOptionPane.YES_OPTION) {
            changeInfo(username);
        } else if (ans == JOptionPane.NO_OPTION) {
            System.out.println(clientBase.getClient(username));
        } else if (ans == JOptionPane.CANCEL_OPTION) {
            requestOrder(username);
        } else {
            System.out.println("Goodbye!");
        }
    }

    public static void changeInfo(String username) {
        System.out.println("What would you like to change?");
        int ans = JOptionPane.showOptionDialog(null, "What would you like to change?", "Change", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {"Address", "Phone", "Email", "Age", "Cancel"}, null);
        if(ans == JOptionPane.YES_OPTION) {
            String address = JOptionPane.showInputDialog("Enter your new address: ");
            clientBase.getClient(username).setAddress(address);
            System.out.println("Information changed!");
            loggedIn(username);
        } else if(ans == JOptionPane.NO_OPTION) {
            String phone = JOptionPane.showInputDialog("Enter your new phone number: ");
            clientBase.getClient(username).setPhone(phone);
            System.out.println("Information changed!");
            loggedIn(username);
        } else if(ans == JOptionPane.CANCEL_OPTION) {
            String email = JOptionPane.showInputDialog("Enter your new email: ");
            clientBase.getClient(username).setEmail(email);
            System.out.println("Information changed!");
            loggedIn(username);
        } else if(ans == 3) {
            int age = Integer.parseInt(JOptionPane.showInputDialog("Enter your new age: "));
            clientBase.getClient(username).setAge(age);
            System.out.println("Information changed!");
            loggedIn(username);
        } else {
            loggedIn(username);
        }
    }

    public static void requestOrder(String username) {
        System.out.println("What produce would you like to order?");
        String produce = JOptionPane.showInputDialog("available produce: \n" + inventory.toString() + "\nEnter the name of the produce you would like to order: ");
        if(inventory.contains(produce)) {
            System.out.println("How many would you like to order?");
            int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter the quantity you would like to order: "));
            if(inventory.getProduce(produce).getInSeason()) {
                clientBase.addProduce(username, produce, quantity);
                System.out.println("Order placed!");
            } else {
                System.out.println("Sorry, that produce is not in season.");
            }
        } else {
            System.out.println("Sorry, that produce is not in stock.");
        }

        int ans = JOptionPane.showOptionDialog(null, "Would you like to order more?", "Order", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if(ans == JOptionPane.YES_OPTION) {
            requestOrder(username);
        } else {
            loggedIn(username);
        }
    }

}
