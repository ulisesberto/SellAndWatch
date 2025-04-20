package ulises.productApp.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ulises.productApp.Entities.Model.Product;
import ulises.productApp.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    
    public void bulkCreateProduct(List<Product> products) {
        productRepository.saveAll(products);
    }
    
    public Product updateProduct(UUID id, Product updatedProduct) {
        return productRepository.findById(id).map(product -> {
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            product.setQuantity(updatedProduct.getQuantity());
            return productRepository.save(product);
        }).orElseThrow(() -> new RuntimeException("Product not found"));
    }
    

}
