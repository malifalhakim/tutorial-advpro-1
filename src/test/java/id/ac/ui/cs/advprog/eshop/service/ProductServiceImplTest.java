package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @BeforeEach
    void setUp(){
    }

    @Test
    void testCreateProduct(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambangi");
        product.setProductQuantity(100);

        when(productRepository.create(any(Product.class))).thenReturn(product);

        Product savedProduct = productService.create(product);
        assertEquals(product,savedProduct);
        verify(productRepository, times(1)).create(any(Product.class));
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    void testFindAll(){
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());

        Iterator<Product> productIterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(productIterator);

        List<Product> products = productService.findAll();
        assertEquals(productList.size(),products.size());
        verify(productRepository, times(1)).findAll();
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    void testEditProduct(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambangi");
        product.setProductQuantity(100);

        when(productRepository.edit(any(Product.class),any(String.class))).thenReturn(product);

        Product editedProduct = productService.edit(product, product.getProductId());
        assertEquals(product,editedProduct);
        verify(productRepository,times(1)).edit(any(Product.class),any(String.class));
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    void testFindById(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambangi");
        product.setProductQuantity(100);

        when(productRepository.findById(any(String.class))).thenReturn(product);

        Product foundProduct = productService.findById(product.getProductId());
        assertEquals(product,foundProduct);
        verify(productRepository,times(1)).findById(any(String.class));
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    void testDeleteProduct(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambangi");
        product.setProductQuantity(100);

        when(productRepository.delete(any(Product.class))).thenReturn(product);

        Product deletedProduct = productService.delete(product);
        assertEquals(product,deletedProduct);
        verify(productRepository,times(1)).delete(any(Product.class));
        verifyNoMoreInteractions(productRepository);
    }
}
