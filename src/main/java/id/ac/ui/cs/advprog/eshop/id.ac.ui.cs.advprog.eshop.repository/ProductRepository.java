package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public void delete(String productId) {
        for (int i = productData.size() - 1; i >= 0; i--) {
            if (productData.get(i).getProductId().equals(productId)) {
                productData.remove(i);
            }
        }
    }

    public void edit(String productId, Product updatedProduct) {
        for (int i = 0; i < productData.size(); i++) {
            if (productData.get(i).getProductId().equals(productId)) {
                Product product = productData.get(i);
                product.setProductName(updatedProduct.getProductName());
                product.setProductQuantity(updatedProduct.getProductQuantity());
            }
        }
    }
}
