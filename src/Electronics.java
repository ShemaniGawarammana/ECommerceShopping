public class Electronics extends Product{
    //Declaring variables
    private String brand;
    private int warrantyPeriod;

    //Default Constructor
    public Electronics() {
        this.brand = "Unknown";
        this.warrantyPeriod = 0;
    }

    //Parameterised Constructor
    public Electronics(String productId, String productName, int availableItems, double price, String brand, int warrantyPeriod) {
        super(productId, productName, availableItems, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    //Setters
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    //Getters
    public String getBrand() {
        return brand;
    }
    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    //To string method
    @Override
    public String toString() {
        return super.toString() + "\nBrand: " + brand + "\nWarranty Period: " + warrantyPeriod;
    }
    // Additional method for detailed product information
    public String productDetails(){
        return "Product Id: " + super.getProductId() + "\nCategory: Electronics" + "\nName: " + getProductName()
                + "\nBrand: " + brand + "\nWarranty Period: " + warrantyPeriod + "\nAvailable Items: "
                + super.getAvailableItems();
    }
}
