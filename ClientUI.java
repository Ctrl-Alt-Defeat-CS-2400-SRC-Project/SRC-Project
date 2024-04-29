import java.awt.GridLayout;

import javax.swing.BorderFactory;
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
                done = logIn();
            } else {
                done = true;
            }
        }
        JOptionPane.showMessageDialog(null, "Thank you for using our services. Have a great day!");
    }

    private static boolean logIn() {
        username = JOptionPane.showInputDialog("Enter your username: ");
        if(clientBase.containsClient(username)) {
            loggedIn();
            return false;
        } else if(username == null) {
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "Username not found. Please try again.");
            logIn();
        }
        return true;
    }

    private static void loggedIn() {
        int ans = JOptionPane.showOptionDialog(null, "How can we help you today?", "Options", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {"Information", "Orders", "Log out"}, null);
        if(ans == JOptionPane.YES_OPTION) {
            info();
        } else if(ans == JOptionPane.NO_OPTION) {
            orders();
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
        int ans = JOptionPane.showOptionDialog(null, "What would you like to do?", "Options", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {"View Stock", "View orders", "Request order", "Cancel order", "Back"}, null);
        if(ans == JOptionPane.YES_OPTION) {
            viewStock();
        } else if(ans == JOptionPane.NO_OPTION) {
            viewOrders();
        } else if(ans == 2) {
            requestOrder();
            orders();
        } else if(ans == 3) {
            cancelOrder();
            orders();
        } else {
            loggedIn();
        }
    }

    private static void viewStock() {
        String stock = inventory.toString();
        JOptionPane.showMessageDialog(null, stock, "Stock", JOptionPane.INFORMATION_MESSAGE);
        orders();
    }

    private static void viewOrders() {
        JOptionPane.showMessageDialog(null, username + "'s orders: \n" + ordersToString(clientBase.getOrders(username)), "Orders", JOptionPane.INFORMATION_MESSAGE);
        orders();
    }

    private static boolean requestOrder() {
        boolean requestDone = false;
        int quantityInt = -1;

        String produceName = JOptionPane.showInputDialog(null, "Enter the produce you would like to order: ");
        if(produceName == null) {
            JOptionPane.showMessageDialog(null, "Order cancelled. Returning to main menu.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
            return requestDone;
        } else if (Inventory.contains(produceName) && !produceName.equals("")) {
            String quantity = JOptionPane.showInputDialog(null, "Enter the quantity of " + produceName + " you would like to order: ");
            if (quantity == null) {
                JOptionPane.showMessageDialog(null, "Order cancelled. Returning to main menu.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
                return requestDone;
            } else {
                try {
                    quantityInt = Integer.parseInt(quantity);
                    if(quantityInt < 0) {
                        JOptionPane.showMessageDialog(null, "Invalid value. Returning to main menu.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
                        return requestDone;
                    } else {
                        if(Inventory.inStock(produceName, quantityInt)) {
                            clientBase.addProduce(username, produceName, quantityInt);
                            JOptionPane.showMessageDialog(null, "Order successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            return requestDone = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "Order failed. Not enough produce in stock.", "Error", JOptionPane.ERROR_MESSAGE);
                            return requestDone;
                        }
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid quantity.", "Error", JOptionPane.ERROR_MESSAGE);
                    return requestDone;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Produce not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return requestDone;
        }
    }

    private static boolean cancelOrder() {
        boolean cancelDone = false;
        String produceName = JOptionPane.showInputDialog(null, "Enter the produce you would like to cancel: ");
        if(produceName == null) {
            JOptionPane.showMessageDialog(null, "Order cancellation cancelled. Returning to main menu.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
            return cancelDone;
        } else if (Inventory.contains(produceName) && !produceName.equals("")) {
            String quantity = JOptionPane.showInputDialog(null, "Enter the quantity of " + produceName + " you would like to cancel: ");
            if (quantity == null) {
                JOptionPane.showMessageDialog(null, "Order cancellation cancelled. Returning to main menu.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
                return cancelDone;
            } else {
                try {
                    int quantityInt = Integer.parseInt(quantity);
                    if(quantityInt < 0) {
                        JOptionPane.showMessageDialog(null, "Invalid value. Returning to main menu.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
                        return cancelDone;
                    } else {
                        if(clientBase.cancelOrder(username, produceName, quantityInt)) {
                            JOptionPane.showMessageDialog(null, "Order cancellation successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            return cancelDone = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "Order cancellation failed. Not enough produce in stock.", "Error", JOptionPane.ERROR_MESSAGE);
                            return cancelDone;
                        }
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid quantity.", "Error", JOptionPane.ERROR_MESSAGE);
                    return cancelDone;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Produce not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return cancelDone;
        }
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

    private static String ordersToString(Produce[] array) {
        StringBuilder sb = new StringBuilder();
        Produce currentItem = new Produce("placeholder", "none");
        if(currentItem.equals(array[0])) {
            sb.append("No orders found");
        } else {
            Produce previousItem = null;
            int count = 1;
            for (int i = 0; i < array.length; i++) {
                currentItem = array[i];
                if (previousItem != null && currentItem.equals(previousItem)) {
                    count++;
                } else if (previousItem != null) {
                    sb.append("   " + previousItem.getName() + " x" + count + "\n");
                    count = 1;
                }
                previousItem = array[i];
            }
            sb.append("   " + previousItem.getName() + " x" + count + "\n");
        }
        return sb.toString();
    }
  
}
