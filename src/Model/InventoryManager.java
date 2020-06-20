package Model;

public class InventoryManager {
    private static Inventory inventory;
    private static int partsId = 0;
    private static int productsId = 0;
    public static Inventory getInventory(){
        return inventory;
    }
    public static void createInventory(){
        inventory = new Inventory();
        inventory.addProduct(new Product(generateProductId(), "sample", 1.00,1,1,1));
        inventory.addPart(new InHouse(generatePartId(), "inHouseSample", 1.00, 1, 1, 1, 1));
        inventory.addPart(new Outsourced(generatePartId(), "OutsourcedSample", 1.00, 1,1,1, "comp"));
        inventory.lookupProduct(0).addAssociatedPart(inventory.lookupPart(0));
    }
    public static int generatePartId(){
        return partsId++;
    }
    public static int generateProductId(){
        return productsId++;
    }
    public static double sumOfParts(int id){
        Double sum = 0.00;
        for (Part part : inventory.lookupProduct(id).getAllAssociatedParts()){
            sum += part.getPrice();
        }
        return sum;
    }
}
