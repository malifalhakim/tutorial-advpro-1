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

        // if product found
        if (index >= 0){
            productData.set(index, product);
            return product;
        }
        return null;
    }

    public Product findById(String id){
        for (Product product: productData){
            if (product.getProductId().equals(id)){
                return product;
            }
        }
        return null;
    }
}
