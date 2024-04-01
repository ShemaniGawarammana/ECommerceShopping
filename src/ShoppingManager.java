// Interface for managing shopping-related operations
public interface ShoppingManager {

    // Method to add a new product to the shopping system
    void addProduct();

    // Method to remove a product from the shopping system based on its product ID
    void removeProduct(String productID);

    // Method to print the list of products in the shopping system
    void printProductList();

    // Method to save the current state of the shopping system to a file
    void saveToFile();

    // Method to restore the shopping system state from a file
    void restoreFromFile();
}
