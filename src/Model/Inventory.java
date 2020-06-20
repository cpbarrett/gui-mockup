package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private ObservableList<Part> allParts;
    private ObservableList<Product> allProducts;

    public Inventory() {
        this.allProducts = FXCollections.observableArrayList();
        this.allParts = FXCollections.observableArrayList();
    }

    public void addPart(Part newPart){
        if (newPart != null){
            this.allParts.add(newPart);
        }
    }
    public void addProduct(Product newProduct){
        if (newProduct != null){
            this.allProducts.add(newProduct);
        }
    }
    public Part lookupPart(int partID){
        return allParts.get(partID);
    }
    public Product lookupProduct(int productID){
        return allProducts.get(productID);
    }
    public ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> matchingParts = FXCollections.observableArrayList();
        for(Part part : allParts){
            if(part.getName().contains(partName)){
                matchingParts.add(part);
            }
        }
        return matchingParts;
    }
    public ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> matchingProducts = FXCollections.observableArrayList();
        for(Product product : allProducts){
            if (product.getName().contains(productName)){
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }
    public void updatePart(int index, Part selectedPart){
        if (allParts.get(index) != null){
            allParts.set(index, selectedPart);
        } else {
            allParts.add(index, selectedPart);
        }
    }
    public void updateProduct(int index, Product selectedProduct){
        if (allProducts.contains(selectedProduct)){
            allProducts.set(index,selectedProduct);
        } else {
            allProducts.add(index, selectedProduct);
        }
    }
    public boolean deletePart(Part selectedPart){
        if(allParts.contains(selectedPart)){
            allParts.remove(selectedPart.getId());
            return true;
        } else {
            return false;
        }
    }
    public boolean deleteProduct(Product selectedProduct){
        if (allProducts.contains(selectedProduct)){
            allProducts.remove(selectedProduct.getId());
            return true;
        } else {
            return false;
        }
    }
    public ObservableList<Part> getAllParts() {
        return allParts;
    }
    public ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}
