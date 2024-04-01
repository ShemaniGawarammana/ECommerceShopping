import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WestminsterShoppingManagerTest {

    @Test
    public void canPrintInOrder() {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        Product electronicProduct = new Electronics("E001", "Radio", 10, 100.0
                , "toshiba", 2);
        shoppingManager.getProductList().add(electronicProduct);

        Product clothingProduct = new Clothing("C001", "shirt", 20, 50.0
                , "L", "Blue");
        shoppingManager.getProductList().add(clothingProduct);

        shoppingManager.printProductList();
        ArrayList<Product> sortedList = shoppingManager.getProductList();
        assertEquals(clothingProduct, sortedList.get(0));
        assertEquals(electronicProduct, sortedList.get(1));
    }

    @Test
    public void canRemoveProduct() {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        Product electronicProduct = new Electronics("E001", "Radio", 10, 100.0
                , "toshiba", 2);
        shoppingManager.getProductList().add(electronicProduct);

        Product clothingProduct = new Clothing("C001", "shirt", 20, 50.0
                , "L", "Blue");
        shoppingManager.getProductList().add(clothingProduct);

        shoppingManager.removeProduct("E001");
        assertEquals(1, shoppingManager.getProductList().size());
        assertFalse(shoppingManager.getProductList().contains(electronicProduct));
        assertTrue(shoppingManager.getProductList().contains(clothingProduct));
        shoppingManager.removeProduct("NonExistingProductId");
        assertEquals(1, shoppingManager.getProductList().size());
        assertTrue(shoppingManager.getProductList().contains(clothingProduct));

    }

    @Test
    void canSaveToFileAndRetrieve(){
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        Product electronicProduct = new Electronics("E001", "Radio", 10, 100.0
                , "toshiba", 2);
        shoppingManager.getProductList().add(electronicProduct);

        Product clothingProduct = new Clothing("C001", "shirt", 20, 50.0
                , "L", "Blue");
        shoppingManager.getProductList().add(clothingProduct);

        shoppingManager.saveToFile();
        shoppingManager.getProductList().clear();
        shoppingManager.restoreFromFile();
        ArrayList<Product> restoredProductsList = shoppingManager.getProductList();
        assertEquals(2, restoredProductsList.size());
        assertTrue(restoredProductsList.get(0) instanceof Electronics);
        assertTrue(restoredProductsList.get(1) instanceof Clothing);

        assertEquals("E001", restoredProductsList.get(0).getProductId());
        assertEquals("Radio", restoredProductsList.get(0).getProductName());
        assertEquals(10, restoredProductsList.get(0).getAvailableItems());
        assertEquals(100.0, restoredProductsList.get(0).getPrice());
        assertEquals("toshiba", ((Electronics) restoredProductsList.get(0)).getBrand());
        assertEquals(2, ((Electronics) restoredProductsList.get(0)).getWarrantyPeriod());

        assertEquals("C001", restoredProductsList.get(1).getProductId());
        assertEquals("shirt", restoredProductsList.get(1).getProductName());
        assertEquals(20, restoredProductsList.get(1).getAvailableItems());
        assertEquals(50.0, restoredProductsList.get(1).getPrice());
        assertEquals("L", ((Clothing) restoredProductsList.get(1)).getSize());
        assertEquals("Blue", ((Clothing) restoredProductsList.get(1)).getColour());

    }


}