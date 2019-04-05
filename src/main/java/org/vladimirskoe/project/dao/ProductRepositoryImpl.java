package org.vladimirskoe.project.dao;

import org.springframework.stereotype.Repository;
import org.vladimirskoe.project.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private List<Product> productList = new ArrayList<>();
    private int index = 0;

    public Product addProduct(Product product) {
        product.setId(++index);
        productList.add(product);
        return product;
    }

    public Product getProductById(Integer id) {
        Product value = null;
        for (Product product : productList) {
            if (product.getId().equals(id)) {
                value = product;
            }
        }
        return value;
    }

    public List<Product> getAllProducts() {
        return productList;
    }

    public Product updateProduct(Integer id, Product product) {
       // int id = product.getId();
        Product value = getProductById(id);

        value.setName(product.getName());
        value.setPrice(product.getPrice());
        value.setWeight(product.getWeight());
        value.setType(product.getType());
        value.setProducer(product.getProducer());
        value.setHcp(product.getHcp());

        return value;
    }

    public void deleteProduct(Integer id) {
        Product value = getProductById(id);
        productList.remove(value);
    }

}
