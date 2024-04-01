import java.io.*;
import java.util.*;

// ShoppingManager interface for managing a list of products
public class WestminsterShoppingManager implements ShoppingManager {
    // Static list to store products
    private static ArrayList<Product> productList;
    // Scanner for user input
    private Scanner scanner = new Scanner(System.in);
    // Constructor initializes the product list
    public WestminsterShoppingManager() {
        productList = new ArrayList<>();
    }
    // Getter for the product list
    public ArrayList<Product> getProductList() {
        return productList;
    }
    // Method to sort the product list based on product IDs
    private void orderList() {
        Collections.sort(productList, Comparator.comparing(Product::getProductId));
    }
    //addProduct method from the ShoppingManager interface
    @Override
    public void addProduct() {
        if (productList.size() <= 50) {
            int electricOrCloth;

            while (true) {
                try {
                    System.out.println("What would you like to add\n 1. Electronic Item\n 2. Clothing Item\n Option Number: ");
                    electricOrCloth = scanner.nextInt();
                    scanner.nextLine();

                    if (electricOrCloth == 1 || electricOrCloth == 2) {
                        System.out.print("Enter product ID: ");
                        String productId = scanner.nextLine();

                        System.out.print("Enter product name: ");
                        String productName = scanner.nextLine();

                        System.out.print("Enter available items: ");
                        int availableItems;
                        while (true) {
                            try {
                                availableItems = scanner.nextInt();
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Please enter a valid number for the number of available items:");
                                scanner.nextLine();
                            }
                        }

                        System.out.print("Enter price: ");
                        double price;
                        while (true) {
                            try {
                                price = scanner.nextDouble();
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Please enter a valid price:");
                                scanner.nextLine();
                            }
                        }

                        scanner.nextLine();

                        if (electricOrCloth == 1) {
                            System.out.print("Enter brand: ");
                            String brand = scanner.nextLine();

                            System.out.print("Enter warranty period: ");
                            int warrantyPeriod;
                            while (true) {
                                try {
                                    warrantyPeriod = scanner.nextInt();
                                    break;
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid number.");
                                    scanner.nextLine();
                                }
                            }
                            Electronics electronicItem = new Electronics(productId, productName, availableItems, price, brand, warrantyPeriod);
                            productList.add(electronicItem);

                        } else if (electricOrCloth == 2) {
                            System.out.print("Enter size: ");
                            String size = scanner.nextLine();

                            System.out.print("Enter color: ");
                            String color = scanner.nextLine();

                            Clothing clothingItem = new Clothing(productId, productName, availableItems, price, size, color);
                            productList.add(clothingItem);
                        }
                        break;
                    } else {
                        System.out.println("Please enter a valid option (1 or 2): ");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid option: ");
                    scanner.nextLine();
                }
            }
            System.out.println("Product has been added successfully.");
        } else {
            System.out.println("Maximum capacity is reached. Unable to add more products");
        }

    }
    //removeProduct method from the ShoppingManager interface
    @Override
    public void removeProduct(String productID) {
        boolean productIsThere = false;
        for (Product product : this.productList) {
            if (product.getProductId().equals(productID)) {
                this.productList.remove(product);
                System.out.println(product + "\nhas been deleted successfully.");
                productIsThere = true;
                break;
            }
        }
        if (!productIsThere){
            System.out.println("Product was not found.");
        }
    }
    //printProductList method from the ShoppingManager interface
    @Override
    public void printProductList() {
        orderList();
        System.out.println("Product List: ");
        for (Product product : this.productList) {
            System.out.println(product.toString());
            System.out.println("");
        }

    }
    //save to file method from the ShoppingManager interface
    @Override
    public void saveToFile() {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("WestMinsterShopProductList.txt", false));
            for (Product product : this.productList) {
                if(product instanceof Electronics){
                    writer.write( "Electronic" +"-" + product.getProductId() + "-" + product.getProductName() + "-"
                            + product.getAvailableItems() + "-" + product.getPrice() + "-"
                            + ((Electronics) product).getBrand() + "-" + ((Electronics) product).getWarrantyPeriod());
                } else if (product instanceof Clothing) {
                    writer.write("Clothing" + "-" + product.getProductId() + "-" + product.getProductName() + "-"
                            + product.getAvailableItems() + "-" + product.getPrice() + "-"
                            + ((Clothing) product).getSize() + "-" + ((Clothing) product).getColour());
                }
                writer.newLine();
            }
            writer.close();

            System.out.println("Products were saved to file.");

        }catch (FileNotFoundException e){
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Unable to read document");
        } catch (NumberFormatException e) {
            System.out.println("Unable to convert to an Integer");
        }
    }
    //restore from file method from the ShoppingManager interface
    @Override
    public void restoreFromFile() {
        productList.clear();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("WestMinsterShopProductList.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineSegments = line.split("-");
                if ("Electronic".equals(lineSegments[0])) {
                    Product product = new Electronics(lineSegments[1], lineSegments[2],
                            Integer.parseInt(lineSegments[3]), Double.parseDouble(lineSegments[4]),
                            lineSegments[5], Integer.parseInt(lineSegments[6]));
                    this.productList.add(product);
                } else if ("Clothing".equals(lineSegments[0])) {
                    Product product = new Clothing(lineSegments[1], lineSegments[2],
                            Integer.parseInt(lineSegments[3]), Double.parseDouble(lineSegments[4]), lineSegments[5],
                            lineSegments[6]);
                    this.productList.add(product);
                } else {
                    System.out.println("Error occurred while reading the file.");
                }
            }
            reader.close();

            System.out.println("File has been read and products have been restored.");
        }catch (FileNotFoundException e){
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Unable to read document");
        } catch (NumberFormatException e) {
            System.out.println("Unable to convert to an integer");
        }
    }
    //Display menu method
    public void menu(){
        try{
            System.out.println("\n\t \t \t Westminster Shopping Manager \t \t \t\n ");
            System.out.println("\t 1. Add a new product");
            System.out.println("\t 2. Delete a product");
            System.out.println("\t 3. Print the list of products");
            System.out.println("\t 4. Save products to a file");
            System.out.println("\t 5. Restore products from file");
            System.out.println("\t 6. Exit\n");
            System.out.print("Enter your choice: ");
            int opt = scanner.nextInt();
            scanner.nextLine();
            switch (opt) {
                case 1:
                    addProduct();
                    menu();
                    break;
                case 2:
                    System.out.println("Enter the products id: ");
                    String productToDelete = scanner.next();
                    removeProduct(productToDelete);
                    System.out.println(productList.size() + " products left in system");
                    menu();
                    break;
                case 3:
                    orderList();
                    printProductList();
                    menu();
                    break;
                case 4:
                    saveToFile();
                    menu();
                    break;
                case 5:
                    restoreFromFile();
                    menu();
                    break;
                case 6:
                    saveToFile();
                    System.out.println("Exiting Westminster Shopping Manager.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
                    menu();
            }

        }catch(InputMismatchException e){
            System.out.println("Please Input a Option Number:");
            menu();
        }
    }
    public static void main(String[] args) {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        shoppingManager.menu();
    }
}
