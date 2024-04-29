import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A class that uses java swing to provide a user interface for the client.
 * The client will be able to sign in, change information, view stock, and request orders.
 * 
 * @author Medha Swarnachandrabalaji, Alex Auyon, Ryan Wei, Hasti Abbasi Kenarsari
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
            String oldAddress = clientBase.getClient(username).getAddress();
            if(oldAddress.equals("")) {
                oldAddress = "No address on file.";
            }
            String address = JOptionPane.showInputDialog("Enter your new address: ");
            clientBase.changeClientInfo(username, address, null, null, -1);
            JOptionPane.showMessageDialog(null, "Information updated.\n" + displayChange(oldAddress, address));
            changeInfo();
        } else if(ans == JOptionPane.NO_OPTION) {
            String oldPhone = clientBase.getClient(username).getPhone();
            if(oldPhone.equals("")) {
                oldPhone = "No phone number on file.";
            }
            String phone = JOptionPane.showInputDialog("Enter your new phone number: ");
            clientBase.changeClientInfo(username, null, phone, null, -1);
            JOptionPane.showMessageDialog(null, "Information updated.\n" + displayChange(oldPhone, phone));
            changeInfo();
        } else if(ans == JOptionPane.CANCEL_OPTION) {
            String oldEmail = clientBase.getClient(username).getEmail();
            if(oldEmail.equals("")) {
                oldEmail = "No email on file.";
            }
            String email = JOptionPane.showInputDialog("Enter your new email: ");
            clientBase.changeClientInfo(username, null, null, email, -1);
            JOptionPane.showMessageDialog(null, "Information updated.\n" + displayChange(oldEmail, email));
            changeInfo();
        } else if(ans == 3) {
            boolean ageDone = false;
            while(!ageDone) {
                int oldAge = clientBase.getClient(username).getAge();
                int age = -1;
                try {
                    age = Integer.parseInt(JOptionPane.showInputDialog("Enter your new age: "));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid age. Please try again.");
                    break;
                }
                if (age < 120 && age > 0) {
                    clientBase.changeClientInfo(username, null, null, null, age);
                    JOptionPane.showMessageDialog(null, "Information updated.\n" + displayChange(String.valueOf(oldAge), String.valueOf(age)));
                    ageDone = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid age. Please try again.");
                    break;
                }
            }
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
        
        JPanel signUpPanel = new JPanel();
        signUpPanel.setLayout(new GridLayout(6, 2));
        signUpPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        signUpPanel.add(new JLabel("Username: "));
        JTextField nameField = new JTextField(20);
        signUpPanel.add(nameField);

        signUpPanel.add(new JLabel("Email: "));
        JTextField emailField = new JTextField(20);
        signUpPanel.add(emailField);

        signUpPanel.add(new JLabel("Phone Number: "));
        JTextField phoneField = new JTextField(20);
        signUpPanel.add(phoneField);

        signUpPanel.add(new JLabel("Address: "));
        JTextField addressField = new JTextField(20);
        signUpPanel.add(addressField);

        signUpPanel.add(new JLabel("Age: "));
        JTextField ageField = new JTextField(20);
        signUpPanel.add(ageField);

        int option = JOptionPane.showConfirmDialog(null, signUpPanel, "Sign Up", JOptionPane.OK_CANCEL_OPTION);

        if(option == JOptionPane.OK_OPTION) {
            String username = nameField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            int age = Integer.parseInt(ageField.getText());

            try{
                clientBase.addClient(username, address, phone, email, age);

                JOptionPane.showMessageDialog(null, "Sign up successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error occured during sign up. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }
    }

    private static String displayChange(String unchanged, String changed) {
        return unchanged + " -> " + changed;
    }
  
}
