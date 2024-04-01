import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

// GUI class for the user shopping interface
public class UserShoppingInterface extends JFrame {

    private WestminsterShoppingManager westminsterShoppingManager = new WestminsterShoppingManager();
    private ShoppingCart shoppingCart = new ShoppingCart();

    private ArrayList<Product> productList = westminsterShoppingManager.getProductList();
    private JComboBox<String> productTypeComboBox;
    private JTable productTable;
    private DefaultTableModel tableModel;
    private JButton shoppingCartButton = new JButton("Shopping Cart");
    private final String[] columnNames = {"Product ID", "Name", "Category", "Price(£)", "Info"};

    private String[][] data;
    private JTextArea textArea;
    private JButton addToShoppingCartButton = new JButton("Add to Shopping Cart");

    private User user;

    // Listener class for handling actions and mouse events
    private class MyListener implements ActionListener, MouseListener {
        // ActionListener
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == shoppingCartButton) {
                shoppingCart.displayCartGUI(user);
            }
            if (evt.getSource() == productTypeComboBox) {
                updateTable();
            }
            if (evt.getSource() == addToShoppingCartButton) {
                int selectedRow = productTable.getSelectedRow();
                String selectedProductId = (String) productTable.getValueAt(selectedRow, 0);
                for (Product product : productList) {
                    if (product.getProductId().equals(selectedProductId)) {
                        shoppingCart.addProduct(product);
                        break;
                    }
                }
            }
        }

        // MouseListener
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == productTable) {
                int selectedRow = productTable.rowAtPoint(e.getPoint());
                if (selectedRow >= 0) {
                    String selectedProductId = (String) productTable.getValueAt(selectedRow, 0);
                    for (Product product : productList) {
                        if (product.getProductId().equals(selectedProductId)) {
                            if (product instanceof Electronics) {
                                textArea.setText("\n\tSelected Product - Details\n\n"
                                        + "\tProduct Id: " + product.getAvailableItems() + "\n\n"
                                        + "\tCategory: Electronics\n"
                                        + "\tName: " + product.getProductName() + "\n\n"
                                        + "\tBrand: " + ((Electronics) product).getBrand() + "\n\n"
                                        + "\tWarranty Period: " + ((Electronics) product).getWarrantyPeriod() + "\n\n"
                                        + "\tItems Available: " + product.getAvailableItems());
                            } else if (product instanceof Clothing) {
                                textArea.setText("\n\tSelected Product - Details\n\n"
                                        + "\tProduct Id: " + product.getAvailableItems() + "\n\n"
                                        + "\tCategory: Clothing\n\n"
                                        + "\tName: " + product.getProductName() + "\n\n"
                                        + "\tSize: " + ((Clothing) product).getSize() + "\n\n"
                                        + "\tColour: " + ((Clothing) product).getColour() + "\n\n"
                                        + "\tItems Available: " + product.getAvailableItems());
                            }
                            break;
                        }
                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // Implement if needed
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // Implement if needed
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // Implement if needed
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // Implement if needed
        }
    }
    // Constructor for the UserShoppingInterface class
    public UserShoppingInterface(User user) {
        this.user = user;
        westminsterShoppingManager.restoreFromFile();
        shoppingCartButton.setBounds(543, 10, 153, 23);
        shoppingCartButton.setFocusable(false);

        JLabel selectProductLabel = new JLabel("Select Product Category:");
        selectProductLabel.setBounds(200, 50, 190, 23);

        String[] productType = {"All", "Electronics", "Clothing"};
        productTypeComboBox = new JComboBox<>(productType);
        productTypeComboBox.setBounds(350, 50, 113, 23);

        // Initialize the data array
        data = new String[productList.size()][columnNames.length];
        tableModel = new DefaultTableModel(data, columnNames);
        productTable = new JTable(tableModel);
        productTable.setRowHeight(43);

        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBounds(30, 137, 660, 283);

        textArea = new JTextArea();
        textArea.setBounds(0, 423, 730, 238);

        addToShoppingCartButton.setBounds(300, 663, 153, 23);
        addToShoppingCartButton.setFocusable(false);


        // Add Action Listener
        MyListener handler = new MyListener();
        shoppingCartButton.addActionListener(handler);
        productTypeComboBox.addActionListener(handler);
        addToShoppingCartButton.addActionListener(handler);
        productTable.addMouseListener(handler);

        this.add(shoppingCartButton);
        this.add(selectProductLabel);
        this.add(productTypeComboBox);
        this.add(scrollPane);
        this.add(textArea);
        this.add(addToShoppingCartButton);
        this.setTitle("Westminster Shopping Centre");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(730, 730);
        this.setBackground(Color.WHITE);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);

        updateTableData();
        updateTable();
    }
    // Method to update the product table
    private void updateTable() {
        tableModel.setDataVector(updateTableData(), columnNames);
        tableModel.fireTableDataChanged();
    }
    // Method to update the data in the product table
    private String[][] updateTableData() {
        String selectedCategory = (String) productTypeComboBox.getSelectedItem();
        int row = 0;
        data = new String[productList.size()][columnNames.length];

        if ("All".equals(selectedCategory)) {
            for (Product product : productList) {
                data[row][0] = product.getProductId();
                data[row][1] = product.getProductName();
                data[row][2] = (product instanceof Electronics) ? "Electronics" : "Clothing";
                data[row][3] = String.valueOf(product.getPrice());
                data[row][4] = (product instanceof Electronics) ?
                        (((Electronics) product).getBrand() + ", " + ((Electronics) product).getWarrantyPeriod()) :
                        (((Clothing) product).getSize() + ", " + ((Clothing) product).getColour());

                row++;
            }
        } else if ("Electronics".equals(selectedCategory)) {
            for (Product product : productList) {
                if(product instanceof Electronics) {
                    data[row][0] = product.getProductId();
                    data[row][1] = product.getProductName();
                    data[row][2] = "Electronics";
                    data[row][3] = String.valueOf(product.getPrice());
                    data[row][4] = ((Electronics) product).getBrand() + ", " + ((Electronics) product).getWarrantyPeriod();
                    row++;
                }
            }
        } else if ("Clothing".equals(selectedCategory)) {
            for (Product product : productList) {
                if (product instanceof Clothing) {
                    data[row][0] = product.getProductId();
                    data[row][1] = product.getProductName();
                    data[row][2] = "Clothing";
                    data[row][3] = String.valueOf(product.getPrice());
                    data[row][4] = ((Clothing) product).getSize() + ", " + ((Clothing) product).getColour();
                    row++;
                }
            }
        }

        return data;
    }

}
