package bce.data;

// FileProductRepository.java
import bce.domain.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileProductRepository implements ProductRepository {
    private String filePath;

    public FileProductRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            products = (List<Product>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product getProductById(String id) {
        List<Product> products = getAllProducts();
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void addProduct(Product product) {
        List<Product> products = getAllProducts();
        products.add(product);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}