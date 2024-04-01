import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

// Class for shopping cart
public class ShoppingCart {
    private ArrayList<Product> productsInCart;
    private JFrame shoppingCartFrame;
    private JTextArea cartTextArea;
    private JTable productTable;
    private DefaultTableModel tableModel;
    private final String[] columnNames = {"Product", "Quantity", "Price"};
    private String[][] data;

    // Constructor initializing the shopping cart and GUI
    public ShoppingCart() {
        this.productsInCart = new ArrayList<>();
        initializeGUI();
    }

    // Method to add a product to the cart and update the GUI
    public void addProduct(Product product) {
        productsInCart.add(product);
        updateTable();
    }

    // Method to remove a product from the cart and update the GUI
    public void removeProduct(Product product) {
        productsInCart.remove(product);
        updateTable();
    }

    // Method to calculate and return the total price of products in the cart
    double totalCart = 0;
    public double total() {

        for (Product product : productsInCart) {
            totalCart += product.getPrice();
        }
        System.out.println("The total of all products is " + totalCart);
        return totalCart;
    }

    //Method to initialize the shopping cart GUI
    private void initializeGUI() {
        // Instantiate shoppingCartFrame before adding components
        shoppingCartFrame = new JFrame("Shopping Cart");
        shoppingCartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        shoppingCartFrame.setSize(630, 630);
        shoppingCartFrame.setBackground(Color.WHITE);
        shoppingCartFrame.setResizable(false);
        shoppingCartFrame.setLayout(null);

        data = new String[productsInCart.size()][columnNames.length];
        tableModel = new DefaultTableModel(data, columnNames);
        productTable = new JTable(tableModel);
        productTable.setRowHeight(43);

        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBounds(30, 30, 570, 283);

        cartTextArea = new JTextArea();
        cartTextArea.setEditable(false);
        cartTextArea.setBounds(0, 323, 630, 240);

        shoppingCartFrame.add(scrollPane);
        shoppingCartFrame.add(cartTextArea);


        shoppingCartFrame.setVisible(true);
    }

    //method to update the table in the GUI with current cart data
    private void updateTable() {
        tableModel.setDataVector(updateTableData(), columnNames);
        tableModel.fireTableDataChanged();
    }

    //method to update the 2D array with current cart data for the table
    private String[][] updateTableData() {
        // Initialize the data array with the size of productsInCart
        data = new String[productsInCart.size()][columnNames.length];

        int row = 0;
        for (Product product : productsInCart) {
            if (!isProductAlreadyAdded(product)) {
                data[row][0] = product.toString();
                data[row][1] = String.valueOf(getQuantity(product));
                data[row][2] = (product.getPrice() * getQuantity(product)) + "£";
                row++;
            }
        }

        return data;
    }
    private boolean isProductAlreadyAdded(Product product) {
        // Iterate through the existing rows of data and check the first column
        for (int i = 0; i < data.length; i++) {
            if (data[i][0] != null && data[i][0].equals(product.toString())) {
                return true; // Product is already added
            }
        }
        return false; // Product is not added yet
    }

    //method to get the quantity of a specific product in the cart
    private int getQuantity(Product productInQuestion) {
        int count = 0;
        for (Product product : productsInCart) {
            if (product.equals(productInQuestion)) {
                count++;
            }
        }
        return count;
    }

    //method to calculate and return the first-time purchase discount
    double discountFirstTime = 0;
    private double firstTimeDiscount(User user){
        if (user.getHasShopped() == false) {
            discountFirstTime = (totalCart * 10) / 100;
        }
        return discountFirstTime;
    }

    //method to calculate and return the discount for three items in the same category
    double discountThreeItems = 0;
    private double threeItemDiscount() {
        for (int row = 0; row < data.length; row++) {
            String quantityString = data[row][1];
            if (quantityString != null && !quantityString.isEmpty()) {
                try {
                    int quantity = Integer.parseInt(quantityString);
                    if (quantity == 3) {
                        discountThreeItems = (totalCart * 20) / 100;
                    }
                } catch (NumberFormatException e) {
                    // Handle the case where the quantity cannot be parsed as an integer
                    e.printStackTrace(); // You might want to log the exception or display an error message
                }
            }
        }
        return discountThreeItems;
    }

    //method to update the JTextArea with current cart details
    private void updateTextArea(User user){
        cartTextArea.setText("\n\n\t\t\tTotal          \t\t" + total() + "£"
                + "\n\n\t\tFirst Purchase Discount (10%)          \t-" + firstTimeDiscount(user) + "£"
                + "\n\n\tThree Items in same Category Discount (20%)         \t-" + threeItemDiscount()
                + "£" + "\n\n\t\t\tFinal Total        \t \t" +  ((totalCart - firstTimeDiscount(user))- threeItemDiscount())+ "£");
    }

    // Method to display the shopping cart GUI
    public void displayCartGUI(User user) {
        updateTextArea(user);
        updateTableData();
        updateTable();
        user.setHasShopped(true);
        SwingUtilities.invokeLater(() -> shoppingCartFrame.setVisible(true));
    }


}
