import java.util.InputMismatchException;
import java.util.Scanner;

public class TestClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Manager Interface\n2. Customer Interface\nPlease select one option number: ");
        int managerOrCustomer;
        while (true) {
            try {
                managerOrCustomer = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid option: ");
                scanner.nextLine();
            }
        }
        scanner.nextLine();

        if (managerOrCustomer == 1) {
            WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
            shoppingManager.menu();
        } else if (managerOrCustomer == 2) {
            WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
            shoppingManager.restoreFromFile();
            User.restoreUsersFromFile();
            LoginUserInterface loginUserInterface = new LoginUserInterface(User.getUserList());
        } else {
            System.out.println("Invalid option. Please select a valid option.");
        }

    }
}