package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public final class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }
    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEdit() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("1");
        updatedProduct.setProductName("Sampo Cap Usep");
        updatedProduct.setProductQuantity(50);

        productRepository.edit(product.getProductId(), updatedProduct);

        Iterator<Product> productIterator = productRepository.findAll();
        Product firstProduct = productIterator.next();
        assertEquals("1", firstProduct.getProductId());
        assertEquals("Sampo Cap Usep", firstProduct.getProductName());
        assertEquals(50, firstProduct.getProductQuantity());
    }

    @Test
    void testDelete() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        productRepository.delete(product.getProductId());

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditandDelete() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("1");
        updatedProduct.setProductName("Sampo Cap Usep");
        updatedProduct.setProductQuantity(50);

        productRepository.edit(product.getProductId(), updatedProduct);

        Iterator<Product> productIterator = productRepository.findAll();
        Product firstProduct = productIterator.next();
        assertEquals("1", firstProduct.getProductId());
        assertEquals("Sampo Cap Usep", firstProduct.getProductName());
        assertEquals(50, firstProduct.getProductQuantity());

        productRepository.delete("1");

        productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditMultiple() {
        Product product1 = new Product();
        product1.setProductId("1");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("2");
        product2.setProductName("Sampo Cap Bango");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Product updatedProduct1 = new Product();
        updatedProduct1.setProductId("1");
        updatedProduct1.setProductName("Sampo Cap Usep");
        updatedProduct1.setProductQuantity(25);

        Product updatedProduct2 = new Product();
        updatedProduct2.setProductId("2");
        updatedProduct2.setProductName("Sampo Cap Meong");
        updatedProduct2.setProductQuantity(75);

        productRepository.edit(product1.getProductId(), updatedProduct1);
        productRepository.edit(product2.getProductId(), updatedProduct2);

        Iterator<Product> productIterator = productRepository.findAll();
        Product firstProduct = productIterator.next();
        assertEquals("1", firstProduct.getProductId());
        assertEquals("Sampo Cap Usep", firstProduct.getProductName());
        assertEquals(25, firstProduct.getProductQuantity());

        Product secondProduct = productIterator.next();
        assertEquals("2", secondProduct.getProductId());
        assertEquals("Sampo Cap Meong", secondProduct.getProductName());
        assertEquals(75, secondProduct.getProductQuantity());
    }

    @Test
    void testDeleteMultiple() {
        Product product1 = new Product();
        product1.setProductId("1");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("2");
        product2.setProductName("Sampo Cap Bango");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        Product firstProduct = productIterator.next();

        productRepository.delete(product1.getProductId());
        assertFalse(productIterator.hasNext());

        productRepository.delete(product2.getProductId());
        productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
}
