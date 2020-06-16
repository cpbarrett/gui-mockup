package Tests;

import Model.InHouse;
import Model.Inventory;
import Model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    Inventory test = new Inventory();
    InHouse inhouse = new InHouse(1, "a", 1.00, 2, 0, 3, 1);
    Product prod = new Product(1, "prod", 2.00, 1, 0, 1);

    @BeforeEach
    void setup(){
        test.addPart(inhouse);
        test.addProduct(prod);

        inhouse.setName("b"); inhouse.setId(2);
        prod.setId(2); prod.setMax(2);
        test.addPart(inhouse);
        test.addProduct(prod);
    }
    @Test
    void testAddPart() {
        assertTrue(test.getAllParts().contains(inhouse));
    }

    @Test
    void testAddProduct() {
        assertTrue(test.getAllProducts().contains(prod));
    }

    @Test
    void lookupPart() {
        assertTrue(test.lookupPart(0).getName().matches(inhouse.getName()));
    }

    @Test
    void lookupProduct() {
        assertTrue(test.lookupProduct(0).getName().matches(prod.getName()));
    }

    @Test
    void testLookupPart() {
        assertTrue(test.lookupPart(inhouse.getName()).get(0).getId() == 2);
    }

    @Test
    void testLookupProduct() {
        assertTrue(test.lookupProduct(prod.getName()).size() == 2);
        assertTrue(test.lookupProduct(prod.getName()).contains(prod));
        assertTrue(test.lookupProduct(prod.getName()).contains(test.lookupProduct(0)));
    }

    @Test
    void updatePart() {
        inhouse.setName("b");
        test.updatePart(0, inhouse);
        assertTrue(test.lookupPart(0).getName().matches(inhouse.getName()));
    }

    @Test
    void updateProduct() {
        prod.setPrice(20.00);
        test.updateProduct(1, prod);
        assertTrue(test.lookupProduct(1).getPrice() == 20.00);
    }

    @Test
    void deletePart() {
        assertTrue(test.deletePart(inhouse));
    }

    @Test
    void deleteProduct() {
        assertTrue(test.deleteProduct(prod));
    }
}