public class Clothing extends Product{
    //Declaring variables
    private String size;
    private String colour;

    //Default Constructor
    public Clothing() {
        super();
        this.size = "Unknown";
        this.colour = "Unknown";
    }
    //Parameterised Constructor
    public Clothing(String productId, String productName, int availableItems, double price, String size, String colour) {
        super(productId, productName, availableItems, price);
        this.size = size;
        this.colour = colour;
    }

    //Setters
    public void setSize(String size) {
        this.size = size;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }

    //Getters
    public String getSize() {
        return size;
    }
    public String getColour() {
        return colour;
    }

    //To string method
    @Override
    public String toString() {
        return super.toString() + "\nSize: " + size + "\nColour: " + colour;
    }

    // Additional method for detailed product information
    public String productDetails(){
        return "Product Id: " + super.getProductId() + "\nCategory: Clothing" + "\nName: " + getProductName()
                + "\nSize: " + size + "\nColour: " + colour + "\nAvailable Items: " + super.getAvailableItems();
    }
}
