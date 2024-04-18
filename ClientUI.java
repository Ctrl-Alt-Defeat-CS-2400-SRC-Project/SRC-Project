import javax.swing.JOptionPane;

/**
 * A class that uses joptions panels to provide a user interface for the client.
 * The client will be able to sign in, change information, and request orders.
 * 
 * @author Ryan Wei
 */
public class ClientUI {
    private static ClientBase clientBase = new ClientBase();
    public static void start() {
        System.out.println("Welcome! Please sign up or log in.");
        String name = JOptionPane.showInputDialog("Enter your user name: ");
        if(clientBase.containsClient(name)) {
            System.out.println("Welcome back, " + name + "!");
        } else {
            System.out.println("Welcome, " + name + "!");
            String address = JOptionPane.showInputDialog("Enter your address (optional): ");
            String phone = JOptionPane.showInputDialog("Enter your phone number (enter phone or email or both): ");
            String email = JOptionPane.showInputDialog("Enter your email (enter phone or email or both): ");
            int age = Integer.parseInt(JOptionPane.showInputDialog("Enter your age(required): "));
            if(clientBase.addClient(name, address, phone, email, age)) {
                String ans = JOptionPane.showOptionDialog(null, "You have sucessfully signed up! Would you like to sign in?", "Sign up", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            } else {
                System.out.println("There was an error signing up. Please try again.");
            }
        }

    }
}
