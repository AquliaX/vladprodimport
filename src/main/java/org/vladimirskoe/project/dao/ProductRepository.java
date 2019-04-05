package org.vladimirskoe.project.dao;

import org.vladimirskoe.project.entity.Product;

import java.util.List;

public interface ProductRepository {

    Product addProduct(Product product);

    Product getProductById(Integer id);

    List<Product> getAllProducts();

    Product updateProduct(Integer id, Product product);

    void deleteProduct(Integer id);

}
