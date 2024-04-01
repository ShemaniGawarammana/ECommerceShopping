public abstract class Product {
    //Declaring variables
    private String productId;
    private String productName;
    private int availableItems;
    private double price;

    //Default constructor
    public Product(){
        this.productId = "Unknown";
        this.productName = "Unknown";
        this.availableItems = 0;
        this.price = 0.0;
    }

    //Parameterised Constructor
    public Product(String productId, String productName, int availableItems, double price) {
        this.productId = productId;
        this.productName = productName;
        this.availableItems = availableItems;
        this.price = price;
    }

    //Setters
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setAvailableItems(int availableItems) {
        this.availableItems = availableItems;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    // Getters
    public String getProductId() {
        return productId;
    }
    public String getProductName() {
        return productName;
    }
    public int getAvailableItems() {
        return availableItems;
    }
    public double getPrice() {
        return price;
    }


    //To string

    @Override
    public String toString() {
        return "Product Id: " + productId + "\nProduct Name: " + productName + "\nAvailable Items: "
                + availableItems + "\nPrice: " + price;
    }
}
