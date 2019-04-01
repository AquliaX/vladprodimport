package org.vladimirskoe.project.dao;

import org.vladimirskoe.project.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    private List<Product> productList = new ArrayList<Product>();
    private int index = 0;

    public void addProduct(Product product) {
        product.setId(++index);
        productList.add(product);
    }

    public Product getProductById(Integer id) {
        Product value = null;
        for (Product product : productList) {
            if (product.getId().equals(id - 1)) {
                value = product;
            }
        }
        return value;
    }

    public List<Product> getAllProducts() {
        return productList;
    }

    public void updateProduct(Product product) {

        int id = product.getId();
        Product value = getProductById(id);

        value.setName(product.getName());
        value.setPrice(product.getPrice());
        value.setWeight(product.getWeight());
        value.setType(product.getType());
        value.setProducer(product.getProducer());
        value.setHcp(product.getHcp());
    }

    public void deleteProduct(Integer id) {
        Product value = getProductById(id);
        productList.remove(value);
    }

}
