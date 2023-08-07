package bce.presentation;
import bce.data.FileProductRepository;
import bce.domain.Product;
import bce.data.InMemoryProductRepository;
import bce.data.ProductRepository;
import java.util.List;

/**
 * Класс Main отвечает за запуск приложения и управление товарами в интернет-магазине.
 */
public class Main {
    public static void main(String[] args) {
        String filePath = "products.dat";
        ProductRepository productRepository = new FileProductRepository(filePath);

        // Добавляем два товара в репозиторий
        Product product1 = new Product("1", "Смартфон", 499.99);
        Product product2 = new Product("2", "Ноутбук", 899.99);
        productRepository.addProduct(product1);
        productRepository.addProduct(product2);

        // Получаем и выводим список всех товаров
        List<Product> products = productRepository.getAllProducts();
        for (Product product : products) {
            System.out.println("Товар: " + product.getName() + ", Цена: $" + product.getPrice());
        }
    }
}