package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product){
        if (product.getProductId() == null){
            UUID uuid = UUID.randomUUID();
            product.setProductId(uuid.toString());
        }
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

        productData.set(index, product);
        return product;
    }

    public Product findById(String id){
        return productData.stream()
                .filter(product -> product.getProductId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product with ID " + id + " not found."));
    }

    public Product delete(Product product){
        productData.remove(product);
        return product;
    }
}
