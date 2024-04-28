//import javax.swing.JOptionPane;
// JFrame creates windows in GUI
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

/**
 * A class that uses joptions panels to provide a user interface for the client.
 * The client will be able to sign in, change information, and request orders.
 * 
 * @author Medha Swarnachandrabalaji, Alex Auyon, Ryan Wei
 */
public class ClientUI_M extends JFrame implements ActionListener{
    private static ClientBase clientBase = new ClientBase();
    private static Inventory inventory = new Inventory();

    // to show text
    private JLabel nameLabel, addressLabel, phoneLabel, emailLabel, ageLabel;
    // for text input
    private JTextField nameField, addressField, phoneField, emailField, ageField;
    // button for login
    private JButton loginButton, viewOrderButton;

    public ClientUI_M() {
        // uses JFrame constructor 
        // frame is initially invisible w/ title
        super("Client UI");
        // application closes after x-button is pressed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        // each cell of the grid can hold one component
        setLayout(new GridLayout(6, 2));

        nameLabel = new JLabel("User Name:");
        addressLabel = new JLabel("Address:");
        phoneLabel = new JLabel("Phone Number:");
        emailLabel = new JLabel("Email:");
        ageLabel = new JLabel("Age:");

        nameField = new JTextField();
        addressField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();
        ageField = new JTextField();

        loginButton = new JButton("Login");
        // ClientUI is a JFrame so parameter is this
        loginButton.addActionListener(this);
        viewOrderButton = new JButton("View Order");
        viewOrderButton.addActionListener(this);

        // display text in GUI
        // add is defined in Container (one of JFrame's super classes)
        add(nameLabel);
        add(nameField);
        add(addressLabel);
        add(addressField);
        add(phoneLabel);
        add(phoneField);
        add(emailLabel);
        add(emailField);
        add(ageLabel);
        add(ageField);
        add(new JLabel()); // Placeholder to visually separate components
        add(loginButton);
        add(viewOrderButton);

        // makes JFrame visible on screen
        // defined in Component class
        setVisible(true);
    }

    // because ClientUI implements the ActionListener interface,
    // it needs to provide an implementation for the actionPerformed(ActionEvent e) method
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText();
        if (clientBase.containsClient(name)) {
            loggedIn(name);
        } else {
            // Implement the sign-up logic here
            System.out.println("Sign up logic goes here.");
        }
    }

    public void loggedIn(String username) {
        //creates panel for text to be displayed in
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //displays welcome message
        JLabel messageLabel = new JLabel("Welcome back " + username + "! What would you like to do?");
        panel.add(messageLabel);

        //button for changing info
        JButton changeInfoButton = new JButton("Change Information");
        changeInfoButton.addActionListener(e -> changeInfo(username));
        panel.add(changeInfoButton);

        //button for viewing info
        JButton viewInfoButton = new JButton("View Information");
        viewInfoButton.addActionListener(e -> viewInfo(username));
        panel.add(viewInfoButton);

        //button for requesting orders
        JButton requestOrderButton = new JButton("Request Order");
        requestOrderButton.addActionListener(e -> requestOrder(username));
        panel.add(requestOrderButton);

        //button for logging out
        JButton logoutButton = new JButton("Log out");
        logoutButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                messageLabel.setText("Goodbye!");
                logoutButton.setEnabled(false);
            }
        });
        //add button to display
        panel.add(logoutButton);

    }

    public void viewOrder(String username) {
        // Implement viewOrder
    }

    public void cancelOrder(String username) {
        // Implement cancelOrder
    }

    public void changeInfo(String username) {
        // Implement changeInfo
    }

    public void viewInfo(String username) {
        // Implement viewInfo
        // Make sure this displays the output from clientBase.getClient(username) when called
    }

    // Alex
    public void requestOrder(String username) {
        // Implement the request order logic here
        System.out.println("Request order logic goes here.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClientUI::new);
    }
}

    // private JFrame frame;
    // private int width;
    // private int height;

    // public ClientUI_M(int w, int h){
    //     frame = new JFrame();
    //     width = w;
    //     height = h;
    // }

    // public void setUpGUI(){
    //     frame.setSize(width, height);
    //     // set title to JFrame
    //     frame.setTitle("ClientUI");
    //     // application closes after x-button is pressed
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     // show window
    //     frame.setVisible(true);
    // }


    // private static ClientBase clientBase = new ClientBase();
    // private static Inventory inventory = new Inventory();
    // public static void start() {
    //     System.out.println("Welcome! Please sign up or log in.");
    //     String name = JOptionPane.showInputDialog("Enter your user name: ");
    //     if(clientBase.containsClient(name)) {
    //         loggedIn(name);
    //     } else {
    //         int ans;
    //         System.out.println("Welcome, " + name + "!");
    //         String address = JOptionPane.showInputDialog("Enter your address (optional): ");
    //         String phone = JOptionPane.showInputDialog("Enter your phone number (enter phone or email or both): ");
    //         String email = JOptionPane.showInputDialog("Enter your email (enter phone or email or both): ");
    //         int age = Integer.parseInt(JOptionPane.showInputDialog("Enter your age(required): "));
    //         if(clientBase.addClient(name, address, phone, email, age)) {
    //             ans = JOptionPane.showOptionDialog(null, "You have sucessfully signed up! Would you like to sign in?", "Sign up", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
    //         } else {
    //             ans = JOptionPane.showOptionDialog(null, "Sign up failed. Would you like to try again?", "Sign up", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
    //         }

    //         if(ans == JOptionPane.YES_OPTION) {
    //             start();
    //         } else {
    //             System.out.println("Goodbye!");
    //         }
    //     }
    // }

    // public static void loggedIn(String username) {
    //     System.out.println("Welcome back " + username + "! What would you like to do?");
    //     int ans = JOptionPane.showOptionDialog(null, "What would you like to do?", "Options", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {"Change information", "View information", "Request order", "Log out"}, null);
    //     if(ans == JOptionPane.YES_OPTION) {
    //         changeInfo(username);
    //     } else if (ans == JOptionPane.NO_OPTION) {
    //         System.out.println(clientBase.getClient(username));
    //     } else if (ans == JOptionPane.CANCEL_OPTION) {
    //         requestOrder(username);
    //     } else {
    //         System.out.println("Goodbye!");
    //     }
    // }

    // public static void changeInfo(String username) {
    //     System.out.println("What would you like to change?");
    //     int ans = JOptionPane.showOptionDialog(null, "What would you like to change?", "Change", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {"Address", "Phone", "Email", "Age", "Cancel"}, null);
    //     if(ans == JOptionPane.YES_OPTION) {
    //         String address = JOptionPane.showInputDialog("Enter your new address: ");
    //         clientBase.getClient(username).setAddress(address);
    //         System.out.println("Information changed!");
    //         loggedIn(username);
    //     } else if(ans == JOptionPane.NO_OPTION) {
    //         String phone = JOptionPane.showInputDialog("Enter your new phone number: ");
    //         clientBase.getClient(username).setPhone(phone);
    //         System.out.println("Information changed!");
    //         loggedIn(username);
    //     } else if(ans == JOptionPane.CANCEL_OPTION) {
    //         String email = JOptionPane.showInputDialog("Enter your new email: ");
    //         clientBase.getClient(username).setEmail(email);
    //         System.out.println("Information changed!");
    //         loggedIn(username);
    //     } else if(ans == 3) {
    //         int age = Integer.parseInt(JOptionPane.showInputDialog("Enter your new age: "));
    //         clientBase.getClient(username).setAge(age);
    //         System.out.println("Information changed!");
    //         loggedIn(username);
    //     } else {
    //         loggedIn(username);
    //     }
    // }

    // public static void requestOrder(String username) {
    //     System.out.println("What produce would you like to order?");
    //     String produce = JOptionPane.showInputDialog("available produce: \n" + inventory.toString() + "\nEnter the name of the produce you would like to order: ");
    //     if(inventory.contains(produce)) {
    //         System.out.println("How many would you like to order?");
    //         int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter the quantity you would like to order: "));
    //         if(inventory.getProduce(produce).getInSeason()) {
    //             clientBase.addProduce(username, produce, quantity);
    //             System.out.println("Order placed!");
    //         } else {
    //             System.out.println("Sorry, that produce is not in season.");
    //         }
    //     } else {
    //         System.out.println("Sorry, that produce is not in stock.");
    //     }

    //     int ans = JOptionPane.showOptionDialog(null, "Would you like to order more?", "Order", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
    //     if(ans == JOptionPane.YES_OPTION) {
    //         requestOrder(username);
    //     } else {
    //         loggedIn(username);
    //     }
    // }

//}
