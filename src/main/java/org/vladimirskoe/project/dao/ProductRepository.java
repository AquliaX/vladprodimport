package org.vladimirskoe.project.dao;

import org.vladimirskoe.project.entity.Product;

import java.util.List;

public interface ProductRepository {

    void addProduct(Product product);

    Product getProductById(Integer id);

    List<Product> getAllProducts();

    void updateProduct(Product product);

    void deleteProduct(Integer id);

}
