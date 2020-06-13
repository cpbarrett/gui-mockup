package sample;

import javafx.collections.ObservableList;

public class Inventory {
    private ObservableList<Part> allParts;
    private ObservableList<Product> allProducts;

    public void addPart(Part newPart){
        allParts.add(newPart);
    }
    public void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }
    public Part lookupPart(int partID){
        return allParts.get(partID);
    }
    public Product lookupProduct(int productID){
        return allProducts.get(productID);
    }
    public ObservableList<Part> lookupPart(String partName){
        return null;
    }
    public ObservableList<Product> lookupProduct(String productName){
        return null;
    }
    public void updatePart(int index, Part selectedPart){
        allParts.add(index, selectedPart);
    }
    public void updateProduct(int index, Product selectedProduct){
        allProducts.add(index, selectedProduct);
    }
    public boolean deletePart(Part selectedPart){
        return allParts.remove(selectedPart);
    }
    public boolean deleteProduct(Product selectedProduct){
        return allProducts.remove(selectedProduct);
    }
    public ObservableList<Part> getAllParts() {
        return allParts;
    }
    public ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}
