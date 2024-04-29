import java.util.Scanner;

/**
 * A main class to demo code. 
 * 
 * @author Ryan Wei
 */
public class Driver {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.println("Welcome! Please select your role:");
        System.out.println("1. Farmer");
        System.out.println("2. Client");
        int role = kb.nextInt();
        kb.nextLine();
        if (role == 1) {
            FarmerUI.start();
        } else if (role == 2) {
            ClientUI.start();
        } else {
            System.out.println("Invalid input. Please try again. Exiting...");
        }
        kb.close();
    }
}
