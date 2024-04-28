import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * A class that uses java swing to provide a user interface for the client.
 * The client will be able to sign in, change information, view stock, and request orders.
 * 
 * @author Medha Swarnachandrabalaji, Alex Auyon, Ryan Wei
 */
public class ClientUI {
    private static ClientBase clientBase;
    private static Inventory inventory;
    private static String username;
    private static boolean done;

    public static void start() {
        try{
            clientBase = new ClientBase();
            inventory = new Inventory();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        done = false;
        while(!done) {
            int ans = JOptionPane.showOptionDialog(null, "Welcome! please sign up or log in", "Sign up or Log in", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {"Sign up", "Log in"}, null);
            if (ans == JOptionPane.YES_OPTION) {
                signUp();
            } else if (ans == JOptionPane.NO_OPTION) {
                logIn();
                done = true;
            } else {
                JOptionPane.showMessageDialog(null, "Error, Please try again.");
            }
        }
        JOptionPane.showMessageDialog(null, "Thank you for using our services. Have a great day!");
    }

    private static void logIn() {
        username = JOptionPane.showInputDialog("Enter your username: ");
        if(clientBase.containsClient(username)) {
            loggedIn();
        } else {
            JOptionPane.showMessageDialog(null, "Username not found. Please try again.");
            logIn();
        }
    }

    private static void loggedIn() {
        int ans = JOptionPane.showOptionDialog(null, "How can we help you today?", "Options", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {"Information", "Orders", "Log out"}, null);
        if(ans == JOptionPane.YES_OPTION) {
            info();
        } else if(ans == JOptionPane.NO_OPTION) {
            orders();
        } else if(ans == JOptionPane.CANCEL_OPTION) {
            requestOrder();
        } else {
            logIn();
        }
    }

    private static void info() {
        int ans = JOptionPane.showOptionDialog(null, "What would you like to do?", "Options", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {"View information", "Change information", "Back"}, null);
        if(ans == JOptionPane.YES_OPTION) {
            viewInfo();
        } else if(ans == JOptionPane.NO_OPTION) {
            changeInfo();
        } else {
            loggedIn();
        }
    }

    private static void viewInfo() {
        String info = clientBase.getClient(username).toString();
        JOptionPane.showMessageDialog(null, info);
        info();
    }
    

    private static void changeInfo() {
        int ans = JOptionPane.showOptionDialog(null, "What would you like to change?", "Options", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {"Address", "Phone number", "Email", "Age", "Back"}, null);
        if(ans == JOptionPane.YES_OPTION) {
            String address = JOptionPane.showInputDialog("Enter your new address: ");
            clientBase.getClient(username).setAddress(address);
            changeInfo();
        } else if(ans == JOptionPane.NO_OPTION) {
            String phone = JOptionPane.showInputDialog("Enter your new phone number: ");
            clientBase.getClient(username).setPhone(phone);
            changeInfo();
        } else if(ans == JOptionPane.CANCEL_OPTION) {
            String email = JOptionPane.showInputDialog("Enter your new email: ");
            clientBase.getClient(username).setEmail(email);
            changeInfo();
        } else if(ans == 3) {
            int oldAge = clientBase.getClient(username).getAge();
            int age = Integer.parseInt(JOptionPane.showInputDialog("Enter your new age: "));
            clientBase.getClient(username).setAge(age);
            JOptionPane.showMessageDialog(null, "Information updated." + displayChange(parseInt(oldAge), age));
            changeInfo();
        } else {
            info();
        }
    }

    private static void orders() {
        //STUB
    }

    private static void requestOrder() {
        //StUB
    }

    private static void signUp() {
        //STUB
    }

    private static String displayChange(String unchanged, String changed) {
        return unchanged + " -> " + changed;
    }
}

