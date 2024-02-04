package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();
    private static int idCounter = 0;

    public Product create(Product product){
        product.setProductId("P" + idCounter++);
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    public Product edit(Product product, String id){
        product.setProductId(id);
        Product oldProduct = findById(id);
        int index = productData.indexOf(oldProduct);

        // if product found in producData list
        if (index >= 0){
            productData.set(index, product);
            return product;
        }
        throw new IllegalArgumentException("Product with ID " + id + " can't be edited.");
    }

    public Product findById(String id){
        for (Product product: productData){
            if (product.getProductId().equals(id)){
                return product;
            }
        }
        throw new IllegalArgumentException("Product with ID " + id + " not found.");
    }

    public Product delete(Product product){
        productData.remove(product);
        return product;
    }
}
