package Tests;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    Inventory test = new Inventory();
    InHouse inhouse = new InHouse(0, "a", 1.00, 2, 0, 3, 1);
    Outsourced outsourced = new Outsourced(1, "b", 1.00, 2, 0, 1, "cm");
    Product prod = new Product(0, "prod", 2.00, 1, 0, 1);

    @BeforeEach
    void setup(){
        test.addPart(inhouse);
        test.addProduct(prod);
        test.addPart(outsourced);
    }
    @Test
    void testAddPart() {
        assertTrue(test.getAllParts().contains(inhouse));
        assertTrue(test.getAllParts().contains(outsourced));
    }

    @Test
    void testAddProduct() {
        assertTrue(test.getAllProducts().contains(prod));
    }

    @Test
    void lookupPart() {
        assertTrue(test.lookupPart(0).getName().matches(inhouse.getName()));
        assertTrue(test.lookupPart(1).getName().matches(outsourced.getName()));
        assertTrue(test.lookupPart(inhouse.getName()).contains(inhouse));
    }

    @Test
    void lookupProduct() {
        assertTrue(test.lookupProduct(0).getName().matches(prod.getName()));
        assertTrue(test.lookupProduct(prod.getName()).contains(prod));
    }

    @Test
    void updatePart() {
        inhouse.setName("b");
        test.updatePart(inhouse.getId(), inhouse);
        assertTrue(test.lookupPart(inhouse.getId()).getName().matches(inhouse.getName()));
    }

    @Test
    void updateProduct() {
        prod.setPrice(20.00);
        test.updateProduct(prod.getId(), prod);
        assertTrue(test.lookupProduct(prod.getId()).getPrice() == 20.00);
    }

    @Test
    void deletePart() {
        assertTrue(test.deletePart(inhouse));
        assertFalse(test.getAllParts().contains(inhouse));
        assertFalse(test.deletePart(inhouse));
    }

    @Test
    void deleteProduct() {
        assertTrue(test.deleteProduct(prod));
        assertFalse(test.getAllProducts().contains(prod));
        assertFalse(test.deleteProduct(prod));
    }
}